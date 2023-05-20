package com.example.baitaplonoop;

import com.example.baitaplonoop.sql.DBConnect;
import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

public class test {
    public static void main(String[] args) {
////        try{
////            FileInputStream fis = new FileInputStream("C:/Users/HOANGPHUC/Desktop/thuPOI.docx");
////            XWPFDocument doc = new XWPFDocument(fis);
////            List<XWPFParagraph> paragraphs = doc.getParagraphs();
//////            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
//////            String paragraph = extractor.getText();
//////            String part[] = paragraph.split("\n");
//////            for(int i = 0; i< part.length; i++){
//////                System.out.println(part[i] + " " + paragraphs.get(i).getText());
//////            }
////            for(XWPFParagraph p : paragraphs){
////                List<XWPFRun> runs = p.getRuns();
////                if(runs != null){
////                    for(XWPFRun r : runs){
////                        List<XWPFPicture> listPic = r.getEmbeddedPictures();
////                        if(listPic != null && listPic.size() != 0){
////                            for(XWPFPicture pic : listPic){
////                                XWPFPictureData data = pic.getPictureData();
////                                byte[] bytepic = data.getData();
////                                BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytepic));
////                                ImageIO.write(imag, "png", new File("D:/Image/" + data.getFileName()));
////                            }
////                        }
////                    }
////                }
////            }
////        } catch (FileNotFoundException e) {
////            throw new RuntimeException(e);
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////        DBConnect db = new DBConnect();
////        try {
////            ResultSet rs = db.getData("Select questionImage from Question");
////            while(rs.next()){
////                InputStream is = rs.getBinaryStream(1);
////                if(is == null){
////                    continue;
////                }
////                FileOutputStream os = new FileOutputStream(new File("D:/Image/photo.png"));
////                byte[] contents = new byte[1024];
////                int size = 0;
////                while((size = is.read(contents)) != -1){
////                    os.write(contents,0, size);
////                }
////            }
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        } catch (FileNotFoundException e) {
////            throw new RuntimeException(e);
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////        try {
////            FileInputStream fis = new FileInputStream("C:/Users/HOANGPHUC/Desktop/thuPOI.docx");
////            XWPFDocument doc = new XWPFDocument(fis);
////            List<XWPFPictureData> picList = doc.getAllPictures();
////            Iterator<XWPFPictureData> iterator = picList.iterator();
////            while(iterator.hasNext()){
////                XWPFPictureData pic = iterator.next();
////                byte[] bytepic = pic.getData();
////                BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytepic));
////                ImageIO.write(imag, "png", new File("D:/Image/" + pic.getFileName()));
////            }
////        } catch (FileNotFoundException e) {
////            throw new RuntimeException(e);
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
        Document doc = new Document();
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/HOANGPHUC/Desktop/thu.pdf"));
            doc.open();

            Image fox = Image.getInstance("D:/Image/1.png");
            fox.scaleToFit(100, 150);
            Phrase p = new Phrase("quick brown fox jumps over the lazy dog.");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add("Or, to say it in a more colorful way: quick \n");
            p.add(new Chunk(fox, 0, 0, true));
//            ColumnText ct = new ColumnText(writer.getDirectContent());
//            ct.setSimpleColumn(new Rectangle(50, 600, 800, 800));
//            ct.addText(p);
//            ct.go();
//            Paragraph paragraph = new Paragraph();
//            paragraph.add(p);
//            doc.add(paragraph);
            doc.add(p);
            doc.close();
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
//        catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
//    import com.itextpdf.text.Document;
//            import com.itextpdf.text.DocumentException;
//            import com.itextpdf.text.Image;
//            import com.itextpdf.text.Paragraph;
//            import com.itextpdf.text.pdf.ColumnText;
//            import com.itextpdf.text.pdf.PdfContentByte;
//            import com.itextpdf.text.pdf.PdfWriter;
//
//            import java.io.FileOutputStream;
//            import java.io.IOException;
//
//public class test {
//    public static void main(String[] args) {
//        Document document = new Document();
//        try {
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/HOANGPHUC/Desktop/output.pdf"));
//            document.open();
//
//            // Load the image and set dimensions
//            Image image = Image.getInstance("D:/Image/photo.png");
//            image.scaleToFit(800, 800);
//
//            // Create ColumnText and add content
//            ColumnText columnText = new ColumnText(writer.getDirectContent());
//            columnText.addElement(new Paragraph("Some text"));
//
//            // Create PdfContentByte to hold image position
//            PdfContentByte canvas = columnText.getCanvas();
//            canvas.saveState();
//            canvas.rectangle(100, 400, 800, 800);
//            canvas.clip();
//            canvas.newPath();
//
//            // Add the image
//            image.setAbsolutePosition(100, 400);
//            canvas.addImage(image);
//
//            // Restore state and add ColumnText to the document
//            canvas.restoreState();
//            columnText.setSimpleColumn(100, 400, 300, 600);
//            columnText.go();
//
//            document.close();
//            writer.close();
//        } catch (IOException | DocumentException e) {
//            e.printStackTrace();
//        }
//    }
//}