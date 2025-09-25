package service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.colors.ColorConstants;

import entity.CentreRadio;
import entity.Patient;
import entity.Rapport;
import service.CentreRadioServ;
import ihm.Output;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public  class RadiologyReportPDF {
    private  CentreRadio centreRadio;

    public RadiologyReportPDF() {
        CentreRadioServ centreRadioServ = new CentreRadioServ();
        Output output = centreRadioServ.viewCentreRadio();

        if (output.istrue()) {
            this.centreRadio = (CentreRadio) output.getObj();
        } else {
            this.centreRadio = new CentreRadio("Centre de Radiologie", "Adresse Inconnue", 0, "contact@centre.com");
            System.out.println(output.getMessage());
        }
    }

    public  String genererRapport(Patient patient, Rapport rapport) throws IOException {
        String horodatage = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String nomFichier = "rapport_" + patient.getNom() + "_" + patient.getCIN() + "_" + horodatage + ".pdf";

        PdfWriter writer = new PdfWriter(nomFichier);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        PdfFont font = PdfFontFactory.createFont();

        document.add(new Paragraph(centreRadio.getNom())
                .setFont(font)
                .setFontSize(14)
                .setFontColor(ColorConstants.DARK_GRAY));
        document.add(new Paragraph(centreRadio.getAdresse())
                .setFont(font)
                .setFontSize(10));
        document.add(new Paragraph("Tel: " + centreRadio.getNum())
                .setFont(font)
                .setFontSize(10));
        document.add(new Paragraph("Email: " + centreRadio.getMail())
                .setFont(font)
                .setFontSize(10));

        document.add(new Paragraph("\n\n"));

        document.add(new Paragraph("Rapport pour: " + patient.getNom() + " " + patient.getPrenom())
                .setFont(font)
                .setFontSize(16)
                .setFontColor(ColorConstants.BLUE));

        document.add(new Paragraph("CIN: " + patient.getCIN())
                .setFont(font)
                .setFontSize(12));
        document.add(new Paragraph("Date de Naissance: " + patient.getDateDeNaissance())
                .setFont(font)
                .setFontSize(12));

        document.add(new Paragraph("\n"));

        document.add(new Paragraph("Contenu du Rapport:")
                .setFont(font)
                .setFontSize(14)
                .setFontColor(ColorConstants.DARK_GRAY));
        document.add(new Paragraph(rapport.toString())
                .setFont(font)
                .setFontSize(12));

        document.add(new Paragraph("\n\n"));

        document.add(new Paragraph("Cette analyse médicale représente un instantané professionnel. " +
                "La santé est un parcours dynamique nécessitant une approche holistique.")
                .setFont(font)
                .setFontSize(10)
                .setFontColor(ColorConstants.GRAY));

        document.close();

        return nomFichier;
    }
}