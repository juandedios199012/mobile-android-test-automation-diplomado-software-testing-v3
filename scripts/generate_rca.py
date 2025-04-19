import json, os, openai, argparse, textwrap

parser = argparse.ArgumentParser()
parser.add_argument('--cucumber')
parser.add_argument('--logs')
parser.add_argument('--output')
args = parser.parse_args()

# 1. Extraer fallos relevantes
with open(args.cucumber, encoding='utf‑8') as f:
    cucumber = json.load(f)

failed = []
for feature in cucumber:
    for element in feature['elements']:
        for step in element['steps']:
            if step['result']['status'] == 'failed':
                failed.append({
                    "feature": feature['name'],
                    "scenario": element['name'],
                    "error": step['result']['error_message'][:400]  # recorta
                })

# 2. Construir prompt
summary = "\n".join(f"- **{f['scenario']}** ({f['feature']}): {f['error']}"
                    for f in failed)

prompt = textwrap.dedent(f"""
Eres un ingeniero QA senior.
Analiza estos fallos y proporciona:
1. Causa raíz probable
2. Impacto de negocio (alto/medio/bajo) explicado a gerentes
3. Acciones correctivas y responsable sugerido
4. Riesgo de recurrencia
5. Breve TL;DR (máx 3 líneas)

Fallos:
{summary}
""")

# 3. Llamada a GPT‑4o
openai.api_key = os.getenv("OPENAI_API_KEY")
resp = openai.chat.completions.create(
    model="gpt-4o-mini",
    messages=[{"role": "user", "content": prompt}],
    temperature=0.2
)
analysis = resp.choices[0].message.content

# 4. Guardar markdown
with open(args.output, "w", encoding="utf‑8") as f:
    f.write(f"# Reporte RCA de Pruebas\n\n{analysis}")
