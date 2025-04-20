import os, json, base64, argparse, openai, markdown2
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, Image, Table, TableStyle
from reportlab.lib.styles import getSampleStyleSheet
from reportlab.lib import colors

# 1) Cargar y parsear JSON
parser = argparse.ArgumentParser()
parser.add_argument('--cucumber', required=True)
parser.add_argument('--output-md', default='rca.md')
parser.add_argument('--output-pdf', default='rca.pdf')
args = parser.parse_args()

data = json.load(open(args.cucumber, encoding='utf8'))

# 2) Extraer fallos con pasos y capturas
failures = []
for feat in data:
    for scen in feat.get('elements', []):
        # buscar cualquier step failed
        steps_info = []
        for step in scen.get('steps', []):
            status = step['result']['status']
            steps_info.append(f"{step['keyword']}{step['name']} → {status}")
            if status == 'failed':
                # extraer primera imagen si existe
                img = None
                for emb in step.get('embeddings', []):
                    if emb['mime_type'].startswith('image/'):
                        img = emb['data']
                        break
                failures.append({
                    'feature': feat['uri'],
                    'scenario': scen['name'],
                    'tags': [t['name'] for t in scen.get('tags', [])],
                    'steps': steps_info,
                    'failed_step': f"{step['keyword']}{step['name']}",
                    'error': step['result']['error_message'],
                    'screenshot': img
                })

# 3) Construir prompt dinámico
prompt_lines = ["Eres un ingeniero QA Senior especializado en Appium. Analiza estos fallos:\n"]
for f in failures:
    prompt_lines.append(f"- Feature: {f['feature']}")
    prompt_lines.append(f"  Scenario: {f['scenario']}")
    prompt_lines.append(f"  Tags: {', '.join(f['tags'])}")
    prompt_lines.append("  Steps:")
    for s in f['steps']:
        prompt_lines.append(f"    • {s}")
    prompt_lines.append(f"  Error en step «{f['failed_step']}»: {f['error']}\n")

prompt_lines.append(
    "Por favor, genera:\n"
    "1. Resumen ejecutivo (2 líneas).\n"
    "2. Tabla Step | Estado | Error.\n"
    "3. Causa raíz probable basada en Appium.\n"
    "4. Recomendaciones con responsable y prioridad.\n"
)
prompt = "\n".join(prompt_lines)

# 4) Llamar al LLM
openai.api_key = os.getenv("OPENAI_API_KEY")
resp = openai.chat.completions.create(
    model="gpt-3.5-turbo",
    messages=[
        {"role":"system","content":"Analiza fallos de Appium."},
        {"role":"user","content": prompt}
    ],
    temperature=0.2
)
analysis = resp.choices[0].message.content

# 5) Guardar Markdown
with open(args.output_md, 'w', encoding='utf8') as f:
    f.write("# RCA Automatizado\n\n")
    f.write(analysis)

# 6) Generar PDF con ReportLab, incluyendo capturas
styles = getSampleStyleSheet()
doc = SimpleDocTemplate(args.output_pdf)
story = [Paragraph("RCA Automatizado", styles['Heading1']), Spacer(1,12),
         Paragraph(analysis.replace('\n','<br/>'), styles['BodyText']), Spacer(1,12)]

# Incrustar capturas
for idx, f in enumerate(failures):
    if f['screenshot']:
        data_bytes = base64.b64decode(f['screenshot'])
        img_path = f"cap_{idx}.png"
        with open(img_path, 'wb') as imgf:
            imgf.write(data_bytes)
        story.append(Paragraph(f"Captura en {f['failed_step']}", styles['Heading3']))
        story.append(Image(img_path, width=400, height=300))
        story.append(Spacer(1,12))

doc.build(story)