package com.example.baitaplonoop.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUI11PopUpController implements Initializable{
    @FXML
    Label lbQuestion;
    @FXML
    Label lbCategory;
    @FXML
    Label lbImport;
    @FXML
    Label lbExport;

    String result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbQuestion.setOnMouseClicked(mouseEvent -> {
            this.result = "Question";
        });
        lbCategory.setOnMouseClicked(mouseEvent -> {
            this.result = "Category";
        });
        lbImport.setOnMouseClicked(mouseEvent -> {
            this.result = "Import";
        });
        lbExport.setOnMouseClicked(mouseEvent -> {
            this.result = "Export";
        });
    }
}
