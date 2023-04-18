package com.example.baitaplonoop.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class testController implements Initializable {
    @FXML
    Label lbLabel;
    @FXML
    Label lbShow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbLabel.setOnMouseClicked(mouseEvent -> {
            lbShow.setText("ok");
        });
    }
}
