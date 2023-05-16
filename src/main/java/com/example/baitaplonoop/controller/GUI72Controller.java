package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.util.ExportFilePDF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI61Controller.timeLimit;

public class GUI72Controller implements Initializable {
    @FXML
    Label lbWarning;
    @FXML
    Button btnStart;
    @FXML
    Button btnCancel;
    @FXML
    Button btnExport;

    void setUpScene(){
        lbWarning.setText("Your attempt will have a time limit of " + timeLimit + " mins. When you start, the timer will begin to count down and cannot be paused. You must finish your attempt before it expires. Are you sure to start quiz now?");
    }
    void setEvent(){
        btnCancel.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        });
        btnExport.setOnAction(event -> {
            ExportFilePDF.exportPDFFile();
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpScene();
        setEvent();
    }
}
