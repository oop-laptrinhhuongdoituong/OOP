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
    public static void  mainSceneGUI21(Initializable controller, ActionEvent event){
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
        Tab tab = gui11Controller.categoryTab_tp;
        gui11Controller.tabPane.getSelectionModel().select(tab);
        stage.setScene(scene);
    }
    public static void  mainSceneGUI21inQuestionTab(Initializable controller, MouseEvent event){
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
        Tab tab = gui11Controller.questionTab_tp;
        gui11Controller.tabPane.getSelectionModel().select(tab);
        stage.setScene(scene);
    }
    public static void  mainSceneGUI21inExportTab(Initializable controller, ActionEvent event){
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
        Tab tab = gui11Controller.importTab_tp;
        gui11Controller.tabPane.getSelectionModel().select(tab);
        stage.setScene(scene);
    }
    public static void  mainSceneGUI21inCategoryTab(Initializable controller, ActionEvent event){
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
        Tab tab = gui11Controller.categoryTab_tp;
        gui11Controller.tabPane.getSelectionModel().select(tab);
        stage.setScene(scene);
    }
    public static void  mainSceneGUI35AddQuiz(Initializable controller, ActionEvent event){
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
        gui11Controller.addQuiz_ap.setVisible(true);
        stage.setScene(scene);
    }
}
