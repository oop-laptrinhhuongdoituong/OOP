package com.example.baitaplonoop.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.settingEvent;



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
            fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUInew.fxml"));
            Parent parent;
            try {
                parent = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(parent);
            GUI11Controller gui11Controller = fxmlLoader.getController();
            Tab tab = gui11Controller.questionTab_tp;
            gui11Controller.tabPane.getSelectionModel().select(tab);
            oldstage.setScene(scene);
        });
        hlCategory.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
            Stage oldstage = (Stage) ((Node) settingEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUInew.fxml"));
            Parent parent;
            try {
                parent = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(parent);
            GUI11Controller gui11Controller = fxmlLoader.getController();
            Tab tab = gui11Controller.categoryTab_tp;
            gui11Controller.tabPane.getSelectionModel().select(tab);
            oldstage.setScene(scene);
        });
        hlImport.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
            Stage oldstage = (Stage) ((Node) settingEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUInew.fxml"));
            Parent parent;
            try {
                parent = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(parent);
            GUI11Controller gui11Controller = fxmlLoader.getController();
            Tab tab = gui11Controller.importTab_tp;
            gui11Controller.tabPane.getSelectionModel().select(tab);
            oldstage.setScene(scene);
        });
    }
}
