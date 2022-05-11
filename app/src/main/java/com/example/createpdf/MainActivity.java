package com.example.createpdf;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            createPdf();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createPdf() throws IOException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "myPDF.pdf");
        OutputStream outputstream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        /*Drawable tickDrawable = getDrawable(R.drawable.tick);
        Bitmap tickBitmap = ((BitmapDrawable)tickDrawable).getBitmap();
        ByteArrayOutputStream tickStream = new ByteArrayOutputStream();
        tickBitmap.compress(Bitmap.CompressFormat.PNG, 100, tickStream);
        byte[] tickBitmapData = tickStream.toByteArray();

        ImageData tickImageData = ImageDataFactory.create(tickBitmapData);
        Image tick = new Image(tickImageData);
        tick.setHeight(8f);
        tick.setWidth(8f);
        tick.setHorizontalAlignment(HorizontalAlignment.CENTER);*/


        document.add(createHeader(1));
        document.add(new Paragraph("\nElinstallation - Vertifikation af mindre elinstallation").setFontSize(18f));

        float[] columnWidth1 = {150, 200, 200};
        Table personalInfo = new Table(columnWidth1);
        personalInfo.setFontSize(10f);
        personalInfo.setPadding(0);

        personalInfo.addCell(new Cell(1, 3).add(new Paragraph("Kundenavn: ")));
        personalInfo.addCell(new Cell(1, 3).add(new Paragraph("Adresse: ")));
        personalInfo.addCell(new Cell().add(new Paragraph("Post nr: ")));
        personalInfo.addCell(new Cell().add(new Paragraph("By: ")));
        personalInfo.addCell(new Cell().add(new Paragraph("Ordernummer: ")));
        personalInfo.addCell(new Cell(1, 3).add(new Paragraph("Identifikation af installationen: ")));
        personalInfo.addCell(new Cell(1, 3).add(new Paragraph("Installationen er udført af: ")));
        personalInfo.addCell(new Cell(1, 2).add(new Paragraph("Verifikation af installationen er udført af: ")));
        personalInfo.addCell(new Cell().add(new Paragraph("Dato: ")));

        document.add(personalInfo);

        float[] columnWidth2 = {450, 30, 30, 30};
        Table questions = new Table(columnWidth2);
        questions.setFontSize(10f);
        questions.setPadding(0);

        /*Table checkbox = new Table(1);
        checkbox.addCell(new Cell().add(tick).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));*/

        Table checkboxOff = new Table(1);
        checkboxOff.setHeight(13f);
        checkboxOff.setWidth(13f);
        checkboxOff.addCell(new Cell());

        ArrayList<String> questionsGenerelt = new ArrayList<>();
        questionsGenerelt.add("Er der taget hensyn til ydre påvirkninger og anvendt korrekt kapslingsklasse?");
        questionsGenerelt.add("Er der brandtætnet ved gennemføringer?");
        questionsGenerelt.add("Er installationen isolationsprøvet?");
        questionsGenerelt.add("Er der foretaget polaritetsprøve og kontrol af fasefølgen?");
        questionsGenerelt.add("Er der foretaget funktionsprøver af installationen?");
        questionsGenerelt.add("Er nul- og beskyttelsesledere korrekt identificeret?");
        questionsGenerelt.add("Er ledere korrekt overstrømsbeskyttet og valgt efter strømværdi?");
        questionsGenerelt.add("Er SPD’er (overspændingsbeskyttelsesudstyr) korrekt valgt og installeret?");
        questionsGenerelt.add("Er permanent tilsluttede brugsgenstande egnet til den pågældende anvendelse?");
        questionsGenerelt.add("Er nødvendig dokumentation til stede?");
        questionsGenerelt.add("Er spændingsfald kontrolleret?");
        questionsGenerelt.add("Er der foretaget foranstaltninger mod elektromagnetiske påvirkninger?");
        questionsGenerelt.add("Er ejer/bruger informeret om funktion og betjening?");

        ArrayList<String> questionsTavlen = new ArrayList<>();
        questionsTavlen.add("Er der tilstrækkelig plads til at arbejde på/adgang til tavlen?");
        questionsTavlen.add("Er overstrømsbeskyttelsesudstyr korrekt valgt og evt. indstillet?");
        questionsTavlen.add("Er der en entydig mærkning af beskyttelsesudstyr med tilhørsforhold?");
        questionsTavlen.add("Er der mærkning om max. mærke-/indstillingsstrøm?");
        questionsTavlen.add("Er mærkning med oplysninger om tekniske data for tavlen foretaget?");
        questionsTavlen.add("Er udgående beskyttelsesledere anbragt i separate klemmer i tavlen?");
        questionsTavlen.add("Er afdækning og dækplader monteret?");
        questionsTavlen.add("Er indføringer tilpasset/tætnet, så tavlens kapslingsklasse er som mærket?");

        ArrayList<String> questionsInstallation = new ArrayList<>();
        questionsInstallation.add("Er udstyr til adskillelse og afbrydelse korrekt valgt, placeret og installeret?");
        questionsInstallation.add("Er stikkontakter og udtag m.m. installeret i henhold til gældende bestemmelser?");
        questionsInstallation.add("Er kabler/ledninger korrekt oplagt, afsluttet og forbundet?");
        questionsInstallation.add("Er kabler beskyttet mod mekanisk overlast ved opføringer fra gulv/jord?");
        questionsInstallation.add("Er tilledninger aflastet for træk og vridning ved tilslutning til installationen?");
        questionsInstallation.add("Er alle dæksler og afdækninger monteret, så der ikke er berøringsfare?");
        questionsInstallation.add("Er alle samlinger let tilgængelige?");

        ArrayList<String> questionsIndbygningsarmaturer = new ArrayList<>();
        questionsIndbygningsarmaturer.add("Er indbygningsarmaturer korrekt valgt og monteret?");
        questionsIndbygningsarmaturer.add("Er indbygningsarmaturer installeret således, at overophedning undgås?");

        ArrayList<String> questionsBeskyttelsesledere = new ArrayList<>();
        questionsBeskyttelsesledere.add("Er jordingslederen korrekt valgt (minimum 6 mm\u00B2)?");
        questionsBeskyttelsesledere.add("Er der etableret beskyttende potentialudligning?");
        questionsBeskyttelsesledere.add("Er supplerende beskyttende potentialudligning etableret?");
        questionsBeskyttelsesledere.add("Er den gennemgående forbindelse i udligningsforbindelser kontrolleret?");
        questionsBeskyttelsesledere.add("Er den gennemgående forbindelse i beskyttelsesledere kontrolleret?");
        questionsBeskyttelsesledere.add("Er overgangsmodstand for jordelektroden kontrolleret?");

        ArrayList<String> questionsFejlbeskyttelse = new ArrayList<>();
        questionsFejlbeskyttelse.add("Er beskyttelsesmetode korrekt valgt i forhold til installationstype og systemjording?");
        questionsFejlbeskyttelse.add("Er RCD’er (fejlstrømsafbrydere) kontrolleret og afprøvet?");
        questionsFejlbeskyttelse.add("Er klasse I brugsgenstande tilsluttet til beskyttelseslederen?");


        questions.addCell(new Cell(2, 1).add(new Paragraph("")).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        questions.addCell(new Cell(2, 1).add(new Paragraph("Ja")).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        questions.addCell(new Cell(2, 1).add(new Paragraph("Nej")).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        questions.addCell(new Cell(2, 1).add(new Paragraph("Ikke relevant")).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        questions.addCell(new Cell(1, 4).add(new Paragraph("1. Generelt:").setBold()).setBorder(Border.NO_BORDER));

        for (int i = 0; i < questionsGenerelt.size(); i++) {
            questions.addCell(new Cell().add(new Paragraph(questionsGenerelt.get(i))).setBorder(Border.NO_BORDER));
            questions.addCell(new Cell().add(createCheckboxOn()));
            questions.addCell(new Cell().add(checkboxOff.setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
            questions.addCell(new Cell().add(checkboxOff.setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
        }

        questions.addCell(new Cell(1, 4).add(new Paragraph("\n2. Tavlen:").setBold()).setBorder(Border.NO_BORDER));
        for (int i = 0; i < questionsTavlen.size(); i++) {
            questions.addCell(new Cell().add(new Paragraph(questionsTavlen.get(i))).setBorder(Border.NO_BORDER));
            questions.addCell(new Cell().add(createCheckboxOn()));
            questions.addCell(new Cell().add(checkboxOff.setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
            questions.addCell(new Cell().add(checkboxOff.setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
        }

        document.add(questions);

        document.add(new AreaBreak());
        document.add(createHeader(2));

        Table questionsPage2 = new Table(columnWidth2);
        questionsPage2.setFontSize(10f);
        questionsPage2.setPadding(0);

        questionsPage2.addCell(new Cell(1, 4).add(new Paragraph("\n3. Installation:").setBold()).setBorder(Border.NO_BORDER));
        for (int i = 0; i < questionsInstallation.size(); i++) {
            questionsPage2.addCell(new Cell().add(new Paragraph(questionsInstallation.get(i))).setBorder(Border.NO_BORDER));
            questionsPage2.addCell(new Cell().add((createCheckboxOn()).setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
            questionsPage2.addCell(new Cell().add(checkboxOff.setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
            questionsPage2.addCell(new Cell().add(checkboxOff.setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
        }

        questionsPage2.addCell(new Cell(1, 4).add(new Paragraph("\n4. Indbygningsarmaturer:").setBold()).setBorder(Border.NO_BORDER));
        for (int i = 0; i < questionsIndbygningsarmaturer.size(); i++) {
            questionsPage2.addCell(new Cell().add(new Paragraph(questionsIndbygningsarmaturer.get(i))).setBorder(Border.NO_BORDER));
            questionsPage2.addCell(new Cell().add((createCheckboxOn()).setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
            questionsPage2.addCell(new Cell().add(checkboxOff.setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
            questionsPage2.addCell(new Cell().add(checkboxOff.setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
        }

        questionsPage2.addCell(new Cell(1, 4).add(new Paragraph("\n5. Beskyttelsesledere og udligningsforbindelser:").setBold()).setBorder(Border.NO_BORDER));
        for (int i = 0; i < questionsBeskyttelsesledere.size(); i++) {
            questionsPage2.addCell(new Cell().add(new Paragraph(questionsBeskyttelsesledere.get(i))).setBorder(Border.NO_BORDER));
            questionsPage2.addCell(new Cell().add((createCheckboxOn()).setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
            questionsPage2.addCell(new Cell().add(checkboxOff.setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
            questionsPage2.addCell(new Cell().add(checkboxOff.setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
        }

        questionsPage2.addCell(new Cell(1, 4).add(new Paragraph("\n6. Fejlbeskyttelse/supplerende beskyttelse:\n").setBold()).setBorder(Border.NO_BORDER));
        for (int i = 0; i < questionsFejlbeskyttelse.size(); i++) {
            questionsPage2.addCell(new Cell().add(new Paragraph(questionsFejlbeskyttelse.get(i))).setBorder(Border.NO_BORDER));
            questionsPage2.addCell(new Cell().add((createCheckboxOn()).setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
            questionsPage2.addCell(new Cell().add(checkboxOff.setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
            questionsPage2.addCell(new Cell().add(checkboxOff.setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER));
        }

        document.add(questionsPage2);

        document.add(new AreaBreak());
        document.add(createHeader(3));

        document.close();
        Toast.makeText(this, "Pdf Created", Toast.LENGTH_LONG).show();
    }

    public Table createHeader(int page) {
        Drawable drawable = getDrawable(R.drawable.zealand);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bitmapData = stream.toByteArray();

        ImageData imageData = ImageDataFactory.create(bitmapData);
        Image logo = new Image(imageData);
        logo.setHorizontalAlignment(HorizontalAlignment.CENTER);
        logo.setHeight(54f);


        float[] columnWidth = {2000f, 2000f, 2000f};
        Table header = new Table(columnWidth);
        header.setHorizontalAlignment(HorizontalAlignment.CENTER);
        header.setTextAlignment(TextAlignment.CENTER);
        header.setPadding(0);

        header.addCell(new Cell(4,1).add(logo).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        header.addCell(new Cell(4,1).add(new Paragraph("Tjekliste").setFontSize(36f).setFontColor(ColorConstants.ORANGE).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.BOTTOM)));
        header.addCell(new Cell().add(new Paragraph("").setHeight(14f)));
        header.addCell(new Cell().add(new Paragraph("").setHeight(14f)));
        header.addCell(new Cell().add(new Paragraph("Side " + page + " af 3")));
        header.addCell(new Cell().add(new Paragraph("Elinstallation")));

        return header;
    }

    public Cell createCheckboxOn() {
        Table checkbox = new Table(1);
        checkbox.addCell(new Cell().add(createTick()).setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));

        return new Cell().add(checkbox.setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER)).setBorder(Border.NO_BORDER);
    }

    public Cell createCheckboxOff() {
        return new Cell();
    }

    public Image createTick() {
        Drawable tickDrawable = getDrawable(R.drawable.tick);
        Bitmap tickBitmap = ((BitmapDrawable)tickDrawable).getBitmap();
        ByteArrayOutputStream tickStream = new ByteArrayOutputStream();
        tickBitmap.compress(Bitmap.CompressFormat.PNG, 100, tickStream);
        byte[] tickBitmapData = tickStream.toByteArray();

        ImageData tickImageData = ImageDataFactory.create(tickBitmapData);
        Image tick = new Image(tickImageData);
        tick.setHeight(8f);
        tick.setWidth(8f);
        tick.setHorizontalAlignment(HorizontalAlignment.CENTER);

        return tick;
    }


}