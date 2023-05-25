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
//        try{
//            FileInputStream fis = new FileInputStream("C:/Users/HOANGPHUC/Desktop/thuPOI.docx");
//            XWPFDocument doc = new XWPFDocument(fis);
//            List<XWPFParagraph> paragraphs = doc.getParagraphs();
////            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
////            String paragraph = extractor.getText();
////            String part[] = paragraph.split("\n");
////            for(int i = 0; i< part.length; i++){
////                System.out.println(part[i] + " " + paragraphs.get(i).getText());
////            }
//            for(XWPFParagraph p : paragraphs){
//                List<XWPFRun> runs = p.getRuns();
//                if(runs != null){
//                    for(XWPFRun r : runs){
//                        List<XWPFPicture> listPic = r.getEmbeddedPictures();
//                        if(listPic != null && listPic.size() != 0){
//                            for(XWPFPicture pic : listPic){
//                                XWPFPictureData data = pic.getPictureData();
//                                byte[] bytepic = data.getData();
//                                BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytepic));
//                                ImageIO.write(imag, "png", new File("D:/Image/" + data.getFileName()));
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        DBConnect db = new DBConnect();
//        try {
//            ResultSet rs = db.getData("Select questionImage from Question");
//            while(rs.next()){
//                InputStream is = rs.getBinaryStream(1);
//                if(is == null){
//                    continue;
//                }
//                FileOutputStream os = new FileOutputStream(new File("D:/Image/photo.png"));
//                byte[] contents = new byte[1024];
//                int size = 0;
//                while((size = is.read(contents)) != -1){
//                    os.write(contents,0, size);
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            FileInputStream fis = new FileInputStream("C:/Users/HOANGPHUC/Desktop/thuPOI.docx");
//            XWPFDocument doc = new XWPFDocument(fis);
//            List<XWPFPictureData> picList = doc.getAllPictures();
//            Iterator<XWPFPictureData> iterator = picList.iterator();
//            while(iterator.hasNext()){
//                XWPFPictureData pic = iterator.next();
//                byte[] bytepic = pic.getData();
//                BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytepic));
//                ImageIO.write(imag, "png", new File("D:/Image/" + pic.getFileName()));
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        Document doc = new Document();
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/HOANGPHUC/Desktop/thu.pdf"));
            doc.open();
            Image dog = Image.getInstance("D:/Image/photo.png");
            Image fox = Image.getInstance("D:/Image/1.png");
            Phrase p = new Phrase("quick brown fox jumps over the lazy dog.\n");
            p.add("Or, to say it in a more colorful way: quick brown \n");
            p.add(new Chunk(fox, 0, 0, true));
            p.add(" jumps over the lazy \n");
            p.add(new Chunk(dog, 0, 0, true));
            p.add(".");
            ColumnText ct = new ColumnText(writer.getDirectContent());
            ct.setSimpleColumn(new Rectangle(50, 600, 400, 800));
            ct.addText(p);
            ct.go();
//            Paragraph paragraph = new Paragraph();
//            paragraph.add(p);
//            doc.add(paragraph);
//            doc.close();
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
