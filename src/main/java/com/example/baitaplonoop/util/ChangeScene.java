package com.example.baitaplonoop.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeScene {
    public static void changeSceneUsingMouseEvent(Initializable controller, String path, MouseEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }
    public static void changeSceneUsingActionEvent(Initializable controller, String path, ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }
}
