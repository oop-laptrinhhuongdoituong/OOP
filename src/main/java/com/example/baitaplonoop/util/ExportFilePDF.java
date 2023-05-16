package com.example.baitaplonoop.util;

import com.example.baitaplonoop.sql.DBConnect;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.example.baitaplonoop.controller.GUI11Controller.quizChosen;

public class ExportFilePDF {
    static DBConnect db = new DBConnect();
    public static void exportPDFFile(){
        Document doc = new Document();
        String path = "C:/Users/HOANGPHUC/Desktop/" + quizChosen + ".pdf";
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
                if (rs1.next()) {
                    Paragraph p = new Paragraph(rs1.getString("questionID") + ": " + rs1.getString("questionText"));
                    InputStream is = rs1.getBinaryStream(4);
                    if (is != null) {
                        FileOutputStream os = new FileOutputStream(new File("D:/Image/photo.png"));
                        byte[] contents = new byte[1024];
                        int size = 0;
                        while ((size = is.read(contents)) != -1) {
                            os.write(contents, 0, size);
                        }
                        Image image = Image.getInstance("D:/Image/photo.png");
                        p.add(image);
                        doc.add(p);
                    }
                }
                char c = 'A';
                while (rs2.next()) {
                    doc.add(new Paragraph(String.valueOf(c) + ". " + rs2.getString("choiceText")));
                    c++;
                }
                while (rs.next()) {
                    ResultSet rs3 = db.getData("Select * from Question where questionID = '" + rs.getString("questionID") + "'");
                    ResultSet rs4 = db.getData("Select * from Choice where questionID = '" + rs.getString("questionID") + "'");
                    if (rs3.next()) {
                        Paragraph P = new Paragraph(rs3.getString("questionID") + ": " + rs3.getString("questionText"));
                        InputStream is = rs3.getBinaryStream(4);
                        if (is != null) {
                            FileOutputStream os = new FileOutputStream(new File("D:/Image/photo.png"));
                            byte[] contents = new byte[1024];
                            int size = 0;
                            while ((size = is.read(contents)) != -1) {
                                os.write(contents, 0, size);
                            }
                            Image image = Image.getInstance("D:/Image/photo.png");
                            P.add(image);
                            doc.add(P);
                        }
                    }
                    char C = 'A';
                    while (rs4.next()) {
                        doc.add(new Paragraph(String.valueOf(C) + ". " + rs4.getString("choiceText")));
                        C++;
                    }
                }
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
