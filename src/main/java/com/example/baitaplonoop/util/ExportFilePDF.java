package com.example.baitaplonoop.util;

import com.example.baitaplonoop.sql.DBConnect;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Alert;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.baitaplonoop.controller.GUI11Controller.quizChosen;

public class ExportFilePDF {
    static DBConnect db = new DBConnect();
    public static void exportPDFFile(){
        Document doc = new Document();
        String path = "./src/main/resources/com/example/baitaplonoop/pdf/" + quizChosen + ".pdf";
        ResultSet rs = db.getData("Select questionID from QuestionInQuiz where quizName = N'" + quizChosen + "'");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Import information");
        String content = "";
        try{
            if(!rs.next()){
                content = "This quiz is empty!";
                alert.setContentText(content);
                alert.show();
            }
            else {
                PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(path));
                doc.open();
                ResultSet rs1 = db.getData("Select * from Question where questionID = '" + rs.getString("questionID") + "'");
                ResultSet rs2 = db.getData("Select * from Choice where questionID = '" + rs.getString("questionID") + "'");
                Phrase p = new Phrase();
                if (rs1.next()) {
                    String imagePath = rs1.getString("questionMedia");
                    if(imagePath != null){
                        p.add(rs1.getString("questionID") + ": " + rs1.getString("questionText") + "\n\n");
                        Image image = Image.getInstance(imagePath);
                        image.scaleToFit(150,200);
                        p.add(new Chunk(image, 0, 0, true));
                        p.add("\n");
                    }else{
                        p.add(rs1.getString("questionID") + ": " + rs1.getString("questionText") + "\n");
                    }
                }
                char c = 'A';
                while (rs2.next()) {
                    p.add(String.valueOf(c) + ". " + rs2.getString("choiceText") + "\n");
                    String imagePath = rs2.getString("choiceMedia");
                    if(imagePath != null){
                        Image image = Image.getInstance(imagePath);
                        image.scaleToFit(150,200);
                        p.add(new Chunk(image, 0, 0, true));
                        p.add("\n");
                    }
                    c++;
                }
                p.add("\n");
                while (rs.next()) {
                    ResultSet rs3 = db.getData("Select * from Question where questionID = '" + rs.getString("questionID") + "'");
                    ResultSet rs4 = db.getData("Select * from Choice where questionID = '" + rs.getString("questionID") + "'");
                    if (rs3.next()) {
                        String imagePath = rs3.getString("questionMedia");
                        if(imagePath != null){
                            p.add(rs3.getString("questionID") + ": " + rs3.getString("questionText") + "\n\n");
                            Image image = Image.getInstance(imagePath);
                            image.scaleToFit(150,200);
                            p.add(new Chunk(image, 0, 0, true));
                            p.add("\n");
                        }else{
                            p.add(rs3.getString("questionID") + ": " + rs3.getString("questionText") + "\n");
                        }
                    }
                    char C = 'A';
                    while (rs4.next()) {
                        p.add(String.valueOf(C) + ". " + rs4.getString("choiceText") + "\n");
                        String imagePath = rs4.getString("choiceMedia");
                        if(imagePath != null){
                            Image image1 = Image.getInstance(imagePath);
                            image1.scaleToFit(150,200);
                            p.add(new Chunk(image1, 0, 0, true));
                            p.add("\n");
                        }
                        C++;
                    }
                    p.add("\n");
                }
                doc.add(p);
                doc.close();
                writer.close();
                content = "Export sucessfully!";
                alert.setContentText(content);
                alert.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}