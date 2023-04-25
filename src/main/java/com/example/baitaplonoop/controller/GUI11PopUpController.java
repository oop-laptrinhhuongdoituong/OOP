package com.example.baitaplonoop.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    @FXML
    TextField txtGUI;

    String result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbQuestion.setOnMouseClicked(mouseEvent -> {
            this.result = "Question";
            txtGUI.setText(result);
        });
        lbCategory.setOnMouseClicked(mouseEvent -> {
            this.result = "Category";
            txtGUI.setText(result);
        });
        lbImport.setOnMouseClicked(mouseEvent -> {
                this.result = "Import";
                txtGUI.setText(result);
        });
        lbExport.setOnMouseClicked(mouseEvent -> {
            this.result = "Export";
            txtGUI.setText(result);
        });
    }
}
