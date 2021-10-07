package org.lekitech.gafalag;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

import static org.apache.pdfbox.pdmodel.PDDocument.load;

/**
 * Date: 05.10.2021
 * Project: GafalagParser
 * Class: PdfParser
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class PdfParser {

    public static void main(String[] args) {
        final File file = new File("lezgi-rus2.pdf");
        try (PDDocument document = load(file)) {
            System.out.println("Number of pages in the pdf :" + document.getNumberOfPages());
            PDFTextStripper textStripper = new PDFTextStripper();
            textStripper.setStartPage(2);
            textStripper.setEndPage(2);
            System.out.println("Text in the pdf >>> " + textStripper.getText(document));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}
