package com.example.baitaplonoop;

import com.example.baitaplonoop.sql.DBConnect;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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
        DBConnect db = new DBConnect();
        try {
            ResultSet rs = db.getData("Select questionImage from Question");
            while (rs.next()) {
                InputStream is = rs.getBinaryStream(1);
                if (is == null) {
                    continue;
                }
                FileOutputStream os = new FileOutputStream(new File("D:/Image/photo.png"));
                byte[] contents = new byte[1024];
                int size = 0;
                while((size = is.read(contents)) != -1){
                    os.write(contents,0, size);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    }
}
