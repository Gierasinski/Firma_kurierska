package org.example.parcel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.example.client.ShipperInfo;
import org.example.global.Address;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    PDDocument invc;
    int n;
    Integer total = 0;
    Integer price;
    String CustName;
    String CustPh;
    List<String> ProductName = new ArrayList<String>();
    List<Integer> ProductPrice = new ArrayList<Integer>();
    List<Integer> ProductQty = new ArrayList<Integer>();
    String InvoiceTitle = new String("Polish delivery company");
    String SubTitle = new String("Invoice");
    String filePath = "";

    //Using the constructor to create a PDF with a blank page
    Invoice() {
        //Create Document
        invc = new PDDocument();
        //Create Blank Page
        PDPage newpage = new PDPage();
        //Add the blank page
        invc.addPage(newpage);
    }
    void writeInvoice(long parcelNumber, ShipperInfo shipper, Payment payment) {
        PDPage myPage = invc.getPage(0);
        try {
            //Prepare Content Stream
            PDPageContentStream cs = new PDPageContentStream(invc, myPage);

            //Writing Single Line text
            //Writing the Invoice title
            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 20);
            cs.newLineAtOffset(140, 750);
            cs.showText(InvoiceTitle);
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 18);
            cs.newLineAtOffset(270, 690);
            cs.showText(SubTitle);
            cs.endText();

            //Writing Multiple Lines
            //writing the customer details
            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(60, 610);
            cs.showText("Customer Name: ");
            cs.newLine();
            cs.showText("Phone Number: ");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(170, 610);
            cs.showText(shipper.getName() +" "+shipper.getSurname());
            cs.newLine();
            cs.showText(shipper.getPhoneNumber());
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(80, 540);
            cs.showText("Product Name");
            cs.endText();


            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(410, 540);
            cs.showText("Price");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(80, 520);
            cs.showText(String.valueOf(parcelNumber));
            cs.newLine();
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(410, 520);
            cs.showText(String.valueOf(payment.getPrice()));
            cs.newLine();
            cs.endText();



            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(310, (500-(20*n)));
            cs.showText("Total: ");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            //Calculating where total is to be written using number of products
            cs.newLineAtOffset(410, (500-(20*n)));
            cs.showText(String.valueOf(payment.getPrice()));
            cs.endText();

            //Close the content stream
            cs.close();
            //Save the PDF
            invc.save(filePath + String.valueOf(parcelNumber) +".pdf");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
