import os, json, base64, argparse
from io import BytesIO
from datetime import datetime

import openai
import markdown2
from reportlab.platypus import (
    SimpleDocTemplate, Paragraph, Spacer, Image, Table, TableStyle, Preformatted
)
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib import colors


def parse_args():
    parser = argparse.ArgumentParser(description="Genera un RCA automático con PDF estilizado y log completo")
    parser.add_argument('--cucumber', required=True, help="Ruta al JSON de Cucumber")
    parser.add_argument('--logs', required=True, help="Ruta al directorio de logs")
    parser.add_argument('--output-pdf', default='rca.pdf', help="Archivo PDF de salida")
    return parser.parse_args()


def extract_failures(data):
    failures = []
    for feat in data:
        feature_name = os.path.basename(feat.get('uri', 'Unknown feature'))
        for scen in feat.get('elements', []):
            for step in scen.get('steps', []):
                if step['result']['status'] == 'failed':
                    # collect step details
                    img = None
                    for emb in step.get('embeddings', []):
                        if emb.get('mime_type', '').startswith('image/'):
                            img = emb.get('data')
                            break
                    failures.append({
                        'feature': feature_name,
                        'scenario': scen.get('name'),
                        'step': f"{step['keyword']}{step['name']}",
                        'error': step['result'].get('error_message', ''),
                        'screenshot': img
                    })
    return failures


def build_prompt(failures):
    lines = ["Eres un ingeniero QA Senior especializado en Appium. Analiza estos fallos:\n"]
    for f in failures:
        lines.append(f"- Feature: {f['feature']}")
        lines.append(f"  Scenario: {f['scenario']}")
        lines.append(f"  Step: {f['step']}")
        lines.append(f"  Error: {f['error']}\n")
    lines.append(
        "Por favor, genera:\n"
        "1. Resumen ejecutivo (2 líneas).\n"
        "2. Tabla con columnas: Feature, Scenario, Step, Error.\n"
        "3. Causa raíz probable basada en Appium.\n"
        "4. Recomendaciones con responsable y prioridad.\n"
    )
    return "\n".join(lines)


def call_llm(prompt):
    openai.api_key = os.getenv("OPENAI_API_KEY")
    resp = openai.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role":"system","content":"Analiza fallos de Appium."},
            {"role":"user","content": prompt}
        ],
        temperature=0.2
    )
    return resp.choices[0].message.content


def read_logs(log_dir):
    logs = []
    if os.path.isdir(log_dir):
        for fname in sorted(os.listdir(log_dir)):
            path = os.path.join(log_dir, fname)
            if os.path.isfile(path):
                try:
                    text = open(path, 'r', encoding='utf8', errors='ignore').read()
                except:
                    text = ''
                logs.append((fname, text))
    return logs


def generate_pdf(analysis, failures, logs, output_pdf):
    styles = getSampleStyleSheet()
    # Custom styles
    styles.add(ParagraphStyle(name="HeaderTitle", fontSize=20, leading=24, alignment=1, textColor=colors.HexColor("#0B5394")))
    styles.add(ParagraphStyle(name="SectionHeader", fontSize=14, leading=18, textColor=colors.HexColor("#0B5394"), spaceAfter=6))
    styles.add(ParagraphStyle(name="CodeBlock", fontName="Courier", fontSize=8, leading=10))

    doc = SimpleDocTemplate(output_pdf, title="RCA Automatizado")
    story = []
    # Header
    story.append(Paragraph("Informe de Análisis de Fallos Automatizado", styles["HeaderTitle"]))
    story.append(Spacer(1, 4))
    story.append(Paragraph(f"Fecha: {datetime.now().strftime('%d/%m/%Y')}", styles["Normal"]))
    story.append(Spacer(1, 12))

    # Prompt analysis summary
    story.append(Paragraph("Resumen Ejecutivo", styles["SectionHeader"]))
    for line in analysis.split('\n')[:2]:  # first two lines
        story.append(Paragraph(line, styles["BodyText"]))
    story.append(Spacer(1, 12))

    # Summary table
    story.append(Paragraph("Resumen de Fallos", styles["SectionHeader"]))
    table_data = [["Feature", "Scenario", "Step", "Error"]]
    for f in failures:
        table_data.append([f['feature'], f['scenario'], f['step'], f['error']])
    table = Table(table_data, colWidths=[100, 100, 150, 150])
    table.setStyle(TableStyle([
        ("BACKGROUND", (0, 0), (-1, 0), colors.HexColor("#D9E1F2")),
        ("TEXTCOLOR", (0, 0), (-1, 0), colors.black),
        ("GRID", (0, 0), (-1, -1), 0.5, colors.grey),
        ("BACKGROUND", (0, 1), (-1, -1), colors.whitesmoke)
    ]))
    story.append(table)
    story.append(Spacer(1, 12))

    # Detailed logs section
    story.append(Paragraph("Registro Completo de Logs", styles["SectionHeader"]))
    for fname, content in logs:
        story.append(Paragraph(f"Archivo: {fname}", styles["BodyText"]))
        story.append(Preformatted(content, styles["CodeBlock"]))
        story.append(Spacer(1, 6))

    doc.build(story)


def main():
    args = parse_args()
    data = json.load(open(args.cucumber, encoding='utf8'))
    failures = extract_failures(data)
    analysis = ''
    if failures:
        prompt = build_prompt(failures)
        analysis = call_llm(prompt)
    logs = read_logs(args.logs)
    generate_pdf(analysis, failures, logs, args.output_pdf)
    print(f"PDF generado: {args.output_pdf}")

if __name__ == "__main__":
    main()