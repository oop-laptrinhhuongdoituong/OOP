package com.example.baitaplonoop;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

public class test {
    public static void main(String[] args) {
        try{
            FileInputStream fis = new FileInputStream("C:/Users/HOANGPHUC/Desktop/thuPOI.docx");
            XWPFDocument doc = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
            String paragraph = extractor.getText();
            String[] part = paragraph.split("\n");
            for(int i = 0; i<part.length; i++){
                System.out.println(part[i]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        ImportFile.importQuestionFromDocxFile(file);
//        String s = "Phuc";
//        System.out.println(s);
//        s = "Huyen";
//        System.out.println(s);
//        s += "yeu Phuc";
//        System.out.println(s);
//        String s = "C4K: Theo em phát biểu nào đúng";
//        String part[] = s.split(": ", 2);
//        System.out.println(part[1]);
//        String s = "";
//        s = "Phuc";
//        System.out.println(s);
//        s = "Huyen";
//        System.out.println(s);
//        String s = "Phuc";
//        System.out.println(s.indexOf("hu"));
//        String s = "A";
//        String t = "A. adafdfgf";
//        String part[] = t.split(". ", 2);
//        System.out.println(part[0]);
//        System.out.println(part[1]);
//        ArrayList<String> s = new ArrayList<>();
//        s.add("A");
//        s.add("B");
//        System.out.println(s.get(1));
//        System.out.println(s.get(0));
//        s.clear();
//        System.out.println(s.size());
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
