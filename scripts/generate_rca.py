import os, json, base64, argparse
from io import BytesIO
from datetime import datetime

import openai
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, Image, Table, TableStyle
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib import colors


def parse_args():
    parser = argparse.ArgumentParser(description="Genera un RCA automático en PDF atractivo para gerentes")
    parser.add_argument('--cucumber', required=True, help="Ruta al JSON de Cucumber")
    parser.add_argument('--output-pdf', default='rca.pdf', help="Archivo PDF de salida")
    return parser.parse_args()


def extract_failures(data):
    failures = []
    feature_name = None
    # Determinar nombre de feature (.feature) a usar como encabezado
    if data:
        uri = data[0].get('uri', '')
        feature_name = os.path.splitext(os.path.basename(uri))[0]
    for feat in data:
        for scen in feat.get('elements', []):
            for step in scen.get('steps', []):
                if step['result']['status'] == 'failed':
                    img = next(
                        (emb['data'] for emb in step.get('embeddings', [])
                         if emb.get('mime_type', '').startswith('image/')),
                        None
                    )
                    failures.append({
                        'feature': feature_name,
                        'scenario': scen.get('name'),
                        'step': f"{step['keyword']}{step['name']}",
                        'error': step['result'].get('error_message', ''),
                        'screenshot': img
                    })
    return feature_name, failures


def build_prompt(failures):
    lines = ["Eres un ingeniero QA Senior especializado en Appium. Analiza estos fallos:\n"]
    for f in failures:
        lines.append(f"- Scenario: {f['scenario']}")
        lines.append(f"  Step: {f['step']}")
        lines.append(f"  Error: {f['error']}\n")
    lines.append(
        "Por favor, genera:\n"
        "1. Resumen ejecutivo (2 líneas).\n"
        "2. Tabla con columnas: Scenario, Step, Error.\n"
        "3. Causa raíz probable basada en Appium.\n"
        "4. Recomendaciones con responsable y prioridad.\n"
    )
    return "\n".join(lines)


def call_llm(prompt):
    openai.api_key = os.getenv("OPENAI_API_KEY")
    resp = openai.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "system", "content": "Analiza fallos de Appium."},
            {"role": "user", "content": prompt}
        ],
        temperature=0.2
    )
    return resp.choices[0].message.content


def generate_pdf(feature_name, analysis, failures, output_pdf):
    styles = getSampleStyleSheet()
    # Encabezados y estilos personalizados
    styles.add(ParagraphStyle(
        name="Title", fontSize=24, leading=28, alignment=1, textColor=colors.HexColor("#0B5394")
    ))
    styles.add(ParagraphStyle(
        name="Section", fontSize=14, leading=18, textColor=colors.HexColor("#0B5394"), spaceAfter=6
    ))

    doc = SimpleDocTemplate(output_pdf, title="RCA Automatizado")
    story = []

    # Título y fecha
    story.append(Paragraph("Informe de Análisis de Fallos Automatizado", styles["Title"]))
    story.append(Spacer(1, 6))
    story.append(Paragraph(f"Feature: {feature_name}", styles["Section"]))
    story.append(Paragraph(f"Fecha: {datetime.now().strftime('%d/%m/%Y')}", styles["Normal"]))
    story.append(Spacer(1, 12))

    # Resumen ejecutivo
    story.append(Paragraph("Resumen Ejecutivo", styles["Section"]))
    for line in analysis.split('\n')[:2]:
        story.append(Paragraph(line, styles["BodyText"]))
    story.append(Spacer(1, 12))

    # Tabla de fallos
    story.append(Paragraph("Resumen de Fallos", styles["Section"]))
    table_data = [["Scenario", "Step", "Error"]]
    for f in failures:
        table_data.append([f['scenario'], f['step'], f['error']])
    table = Table(table_data, colWidths=[150, 200, 200])
    table.setStyle(TableStyle([
        ("BACKGROUND", (0, 0), (-1, 0), colors.HexColor("#D9E1F2")),
        ("TEXTCOLOR", (0, 0), (-1, 0), colors.black),
        ("ALIGN", (0, 0), (-1, -1), "LEFT"),
        ("GRID", (0, 0), (-1, -1), 0.5, colors.grey),
        ("BACKGROUND", (0, 1), (-1, -1), colors.whitesmoke)
    ]))
    story.append(table)
    story.append(Spacer(1, 12))

    # Causa raíz y recomendaciones (LLM analysis completo)
    story.append(Paragraph("Análisis Detallado", styles["Section"]))
    for paragraph in analysis.split('\n\n'):
        story.append(Paragraph(paragraph, styles["BodyText"]))
        story.append(Spacer(1, 6))
    story.append(Spacer(1, 12))

    # Imágenes embebidas
    for idx, f in enumerate(failures):
        if f.get('screenshot'):
            img_data = base64.b64decode(f['screenshot'])
            img_buf = BytesIO(img_data)
            story.append(Paragraph(f"Captura de error en step: {f['step']}", styles["Section"]))
            story.append(Image(img_buf, width=400, height=300))
            story.append(Spacer(1, 12))

    doc.build(story)


def main():
    args = parse_args()
    data = json.load(open(args.cucumber, encoding='utf8'))
    feature_name, failures = extract_failures(data)
    if not failures:
        print("No se detectaron fallos en el reporte de Cucumber.")
        return
    prompt = build_prompt(failures)
    analysis = call_llm(prompt)
    generate_pdf(feature_name, analysis, failures, args.output_pdf)
    print(f"PDF generado: {args.output_pdf}")


if __name__ == "__main__":
    main()