import sys
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer
from reportlab.lib.styles import getSampleStyleSheet
import markdown2

if len(sys.argv) != 3:
    print("Uso: convert_to_pdf.py input.md output.pdf")
    sys.exit(1)

md_path, pdf_path = sys.argv[1], sys.argv[2]
md = open(md_path, 'r', encoding='utf8').read()
html = markdown2.markdown(md)

styles = getSampleStyleSheet()
doc = SimpleDocTemplate(pdf_path)
story = []
for line in html.split('\n'):
    story.append(Paragraph(line, styles["BodyText"]))
    story.append(Spacer(1, 6))

doc.build(story)
