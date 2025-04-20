import os, json, base64, argparse
from io import BytesIO
from datetime import datetime

import openai
import markdown2
from reportlab.platypus import (
    SimpleDocTemplate, Paragraph, Spacer, Image, Table, TableStyle
)
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib import colors


def parse_args():
    parser = argparse.ArgumentParser(description="Genera un RCA automático con PDF estilizado")
    parser.add_argument('--cucumber', required=True, help="Ruta al JSON de Cucumber")
    parser.add_argument('--output-pdf', default='rca.pdf', help="Archivo PDF de salida")
    return parser.parse_args()


def extract_failures(data):
    failures = []
    for feat in data:
        feature_name = feat.get('uri', 'Unknown feature')
        for scen in feat.get('elements', []):
            steps_info = []
            for step in scen.get('steps', []):
                status = step['result']['status']
                steps_info.append(f"{step['keyword']}{step['name']} → {status}")
                if status == 'failed':
                    img = None
                    for emb in step.get('embeddings', []):
                        if emb.get('mime_type', '').startswith('image/'):
                            img = emb.get('data')
                            break
                    failures.append({
                        'feature': feature_name,
                        'scenario': scen.get('name'),
                        'steps': steps_info,
                        'failed_step': f"{step['keyword']}{step['name']}",
                        'error': step['result'].get('error_message', ''),
                        'screenshot': img
                    })
    return failures


def build_prompt(failures):
    lines = ["Eres un ingeniero QA Senior especializado en Appium. Analiza estos fallos:\n"]
    for f in failures:
        lines.append(f"- Feature: {f['feature']}")
        lines.append(f"  Scenario: {f['scenario']}")
        lines.append("  Steps:")
        for s in f['steps']:
            lines.append(f"    • {s}")
        lines.append(f"  Error en step ‘{f['failed_step']}’: {f['error']}\n")
    lines.append(
        "Genera:\n"
        "1. Resumen ejecutivo (2 líneas).\n"
        "2. Tabla Step | Estado | Error.\n"
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


def generate_pdf(analysis, failures, output_pdf):
    styles = getSampleStyleSheet()
    styles.add(ParagraphStyle(
        name="HeaderTitle", fontSize=20, leading=24,
        alignment=1, textColor=colors.HexColor("#0B5394")
    ))
    styles.add(ParagraphStyle(
        name="SectionHeader", fontSize=14, leading=18,
        textColor=colors.HexColor("#0B5394"), spaceAfter=6
    ))

    doc = SimpleDocTemplate(output_pdf, title="RCA Automatizado")
    story = [
        Paragraph("Informe de Análisis de Fallos Automatizado", styles["HeaderTitle"]),
        Spacer(1, 6),
        Paragraph(f"Fecha: {datetime.now().strftime('%d/%m/%Y')}", styles["Normal"]),
        Spacer(1, 12),
        Paragraph("Resumen Ejecutivo", styles["SectionHeader"]),
        Paragraph(analysis.split('\n')[0], styles["BodyText"]),
        Spacer(1, 12),
        Paragraph("Detalle de Fallos", styles["SectionHeader"])
    ]

    table_data = [["Step", "Estado", "Error"]]
    for f in failures:
        table_data.append([f['failed_step'], "Failed", f['error']])

    table = Table(table_data, colWidths=[200, 80, 260])
    table.setStyle(TableStyle([
        ("BACKGROUND", (0, 0), (-1, 0), colors.HexColor("#D9E1F2")),
        ("TEXTCOLOR", (0, 0), (-1, 0), colors.black),
        ("ALIGN", (1, 1), (-1, -1), "CENTER"),
        ("GRID", (0, 0), (-1, -1), 0.5, colors.grey),
        ("BACKGROUND", (0, 1), (-1, -1), colors.whitesmoke)
    ]))
    story.append(table)
    story.append(Spacer(1, 12))

    story.append(Paragraph("Causa Raíz Probable", styles["SectionHeader"]))
    story.append(Paragraph(analysis, styles["BodyText"]))
    story.append(Spacer(1, 12))

    story.append(Paragraph("Recomendaciones", styles["SectionHeader"]))
    # Opcional: parsear recomendaciones desde analysis si están estructuradas

    for idx, f in enumerate(failures):
        if f.get('screenshot'):
            img_data = base64.b64decode(f['screenshot'])
            img_buf = BytesIO(img_data)
            story.append(Paragraph(f"Captura en paso: {f['failed_step']}", styles['SectionHeader']))
            story.append(Image(img_buf, width=400, height=300))
            story.append(Spacer(1, 12))

    doc.build(story)


def main():
    args = parse_args()
    data = json.load(open(args.cucumber, encoding='utf8'))
    failures = extract_failures(data)
    if not failures:
        print("No se detectaron fallos en el reporte de Cucumber.")
        return
    prompt = build_prompt(failures)
    analysis = call_llm(prompt)
    generate_pdf(analysis, failures, args.output_pdf)
    print(f"PDF generado: {args.output_pdf}")


if __name__ == "__main__":
    main()