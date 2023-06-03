package com.example.baitaplonoop.util;

import com.example.baitaplonoop.controller.GUI11Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeScene {
    public static void changeSceneUsingMouseEvent(Initializable controller, String path, MouseEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
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
    public static void GUI21ListQuestion(Initializable controller, ActionEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.tabPane.setVisible(true);
        Tab tab = gui11Controller.questionTab_tp;
        gui11Controller.tabPane.getSelectionModel().select(tab);
        stage.setScene(scene);
    }
    public static void GUI33AddCategory(Initializable controller, MouseEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.tabPane.setVisible(true);
        Tab tab = gui11Controller.categoryTab_tp;
        gui11Controller.tabPane.getSelectionModel().select(tab);
        stage.setScene(scene);
    }
    public static void GUI34ImportFileQuestion(Initializable controller, ActionEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.tabPane.setVisible(true);
        Tab tab = gui11Controller.importTab_tp;
        gui11Controller.tabPane.getSelectionModel().select(tab);
        stage.setScene(scene);
    }

    public static void GUI35AddQuiz(Initializable controller, ActionEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.addQuiz_ap.setVisible(true);
        stage.setScene(scene);
    }
    public static void  mainSceneGUI11(Initializable controller, MouseEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
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
        GUI11Controller gui11Controller = fxmlLoader.getController();
        stage.setScene(scene);
    }
    public static void GUI32AddQuestion(Initializable controller, ActionEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.addQuestion_ap.setVisible(true);
        stage.setScene(scene);
    }
    public static void GUI61PreviewQuiz(Initializable controller, MouseEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.GUI61_ap.setVisible(true);
        stage.setScene(scene);
    }
}
