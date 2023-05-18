package com.example.baitaplonoop.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class AlertOOP {
    public static void  mustFill(String Title, String HeaderText, String Content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Title);
        alert.setHeaderText(HeaderText);
        alert.setContentText(Content);
        Label contentLabel = (Label) alert.getDialogPane().lookup(".content.label");
        contentLabel.setStyle("-fx-text-fill: red");
        alert.showAndWait();
    }

    public static void  AddDone(String Title, String HeaderText, String Content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Title);
        alert.setHeaderText(HeaderText);
        alert.setContentText(Content);
        Label contentLabel = (Label) alert.getDialogPane().lookup(".content.label");
        contentLabel.setStyle("-fx-text-fill: blue");
        alert.showAndWait();
    }
}
