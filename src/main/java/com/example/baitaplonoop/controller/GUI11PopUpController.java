package com.example.baitaplonoop.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.settingEvent;
import static com.example.baitaplonoop.controller.GUI61Controller.StartEvent;


public class GUI11PopUpController implements Initializable{
    @FXML
    private Hyperlink hlQuestion;
    @FXML
    private Hyperlink hlCategory;
    @FXML
    private Hyperlink hlImport;
    @FXML
    private Hyperlink hlExport;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hlQuestion.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
            Stage oldstage = (Stage) ((Node) settingEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI21.fxml"));
            Parent parent = null;
            try {
                parent = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(parent);
            oldstage.setScene(scene);
        });
        hlCategory.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
            Stage oldstage = (Stage) ((Node) settingEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI33AddCategory.fxml"));
            Parent parent = null;
            try {
                parent = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(parent);
            oldstage.setScene(scene);
        });
        hlImport.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
            Stage oldstage = (Stage) ((Node) settingEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI34.fxml"));
            Parent parent = null;
            try {
                parent = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(parent);
            oldstage.setScene(scene);
        });
    }
}
