package com.example.baitaplonoop.controller;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.quizChosen;


public class SetPasswordForPDFFile implements Initializable {
    @FXML
    PasswordField txtPassword;
    @FXML
    PasswordField txtRepeat;
    @FXML
    Button btnOK;
    @FXML
    Button btnCancel;
    @FXML
    Label lbAlert;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnOK.setOnAction(event -> {
            if(txtPassword.getText().trim().equals("") || txtRepeat.getText().trim().equals("")){
                lbAlert.setText("You must fill all the fields");
            }else if(!txtPassword.getText().equals(txtRepeat.getText())){
                lbAlert.setText("Repeat your password again!");
                txtRepeat.setText("");
            }else{
                String hardPassword = txtPassword.getText();
                byte[] hardPasswordByte = hardPassword.getBytes();

                PdfReader reader;
                try {
                    File file = new File("./src/main/resources/com/example/baitaplonoop/pdf/" + quizChosen + ".pdf");
                    byte[] bytes = Files.readAllBytes(file.toPath());
                    reader = new PdfReader(bytes);
                    PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(file));
                    stamp.setEncryption(hardPasswordByte, null, PdfWriter.ALLOW_COPY, PdfWriter.ENCRYPTION_AES_256);
                    stamp.close();
                    reader.close();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                } catch (IOException | DocumentException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        btnCancel.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        });
    }
}