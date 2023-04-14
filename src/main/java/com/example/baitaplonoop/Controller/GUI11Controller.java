package com.example.baitaplonoop.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GUI11Controller implements Initializable {
    @FXML
    ImageView imgSetting;
    FXMLLoader fxmlLoader;
    GUI11PopUpController controller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgSetting.setOnMouseClicked(event -> {
            try {
                fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI11PopUp.fxml"));
                DialogPane pane = fxmlLoader.load();
                controller = fxmlLoader.getController();

                Dialog<String> dialog = new Dialog<>();
                dialog.setDialogPane(pane);
                dialog.setResultConverter(new Callback<ButtonType, String>() {
                    @Override
                    public String call(ButtonType buttonType) {
                        return controller.result;
                    }
                });
                Optional<String>  result = dialog.showAndWait();
                result.ifPresent(gui ->{
                    System.out.println(gui);
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
