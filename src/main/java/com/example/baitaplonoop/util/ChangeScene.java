package com.example.baitaplonoop.util;

import com.example.baitaplonoop.controller.GUI11Controller;
import com.example.baitaplonoop.controller.GUI32Controller;
import com.example.baitaplonoop.controller.GUI62Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;

import static com.example.baitaplonoop.controller.GUI11PopUpController.currentHeight;
import static com.example.baitaplonoop.controller.GUI11PopUpController.currentWidth;

public class ChangeScene {

    public static void GUI32AddQuestion(Initializable controller, ActionEvent event, String categoryName, String questionID, String questionText, String questionMedia, String choiceText1, String choiceGrade1, String choiceMedia1, String choiceText2, String choiceGrade2, String choiceMedia2, String choiceText3, String choiceGrade3, String choiceMedia3, String choiceText4, String choiceGrade4, String choiceMedia4, String choiceText5, String choiceGrade5, String choiceMedia5, String choiceText6, String choiceGrade6, String choiceMedia6){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentWidth = stage.getScene().getWidth();
        currentHeight = stage.getScene().getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent, currentWidth, currentHeight);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        AnchorPane gui32AddQuestion = gui11Controller.addQuestion_ap;
        AnchorPane editQuestion_ap = (AnchorPane) gui32AddQuestion.lookup("#addQuestion_ap");
        FXMLLoader loader2 = new FXMLLoader(ChangeScene.class.getResource("/com/example/baitaplonoop/GUI32AddQuestion.fxml"));
        Parent root2;
        try {
            root2 = loader2.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GUI32Controller controller32 = loader2.getController();
        controller32.editingQuestionChoice(categoryName, questionID, questionText, questionMedia, choiceText1, choiceGrade1, choiceMedia1, choiceText2, choiceGrade2, choiceMedia2, choiceText3, choiceGrade3, choiceMedia3, choiceText4, choiceGrade4, choiceMedia4, choiceText5, choiceGrade5, choiceMedia5, choiceText6, choiceGrade6, choiceMedia6);
        gui11Controller.addQuestion_ap.setVisible(true);
        editQuestion_ap.getChildren().setAll(root2);
        stage.setScene(scene);
    }
    public static void GUI21ListQuestion(Initializable controller, ActionEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentWidth = stage.getScene().getWidth();
        currentHeight = stage.getScene().getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent, currentWidth, currentHeight);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.tabPane.setVisible(true);
        Tab tab = gui11Controller.questionTab_tp;
        gui11Controller.tabPane.getSelectionModel().select(tab);
        stage.setScene(scene);
    }
    public static void GUI33AddCategory(Initializable controller, MouseEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentWidth = stage.getScene().getWidth();
        currentHeight = stage.getScene().getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;

        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent, currentWidth, currentHeight);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.tabPane.setVisible(true);
        Tab tab = gui11Controller.categoryTab_tp;
        gui11Controller.tabPane.getSelectionModel().select(tab);
        stage.setScene(scene);
        System.out.println(scene.getHeight());
        stage.setFullScreen(stage.isFullScreen());
    }
    public static void GUI34ImportFileQuestion(Initializable controller, ActionEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentWidth = stage.getScene().getWidth();
        currentHeight = stage.getScene().getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent, currentWidth, currentHeight);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.tabPane.setVisible(true);
        Tab tab = gui11Controller.importTab_tp;
        gui11Controller.tabPane.getSelectionModel().select(tab);
        stage.setScene(scene);
    }

    public static void GUI35AddQuiz(Initializable controller, ActionEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentWidth = stage.getScene().getWidth();
        currentHeight = stage.getScene().getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent, currentWidth, currentHeight);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.addQuiz_ap.setVisible(true);
        stage.setScene(scene);
    }
    public static void  mainSceneGUI11(Initializable controller, MouseEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentWidth = stage.getScene().getWidth();
        currentHeight = stage.getScene().getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent, currentWidth, currentHeight);
        stage.setScene(scene);
    }
    public static void GUI32AddQuestion(Initializable controller, ActionEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentWidth = stage.getScene().getWidth();
        currentHeight = stage.getScene().getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent, currentWidth, currentHeight);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.addQuestion_ap.setVisible(true);
        stage.setScene(scene);
    }
    public static void GUI61PreviewQuiz(Initializable controller, MouseEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentWidth = stage.getScene().getWidth();
        currentHeight = stage.getScene().getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent, currentWidth, currentHeight);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.GUI61_ap.setVisible(true);
        stage.setScene(scene);
    }
    public static void GUI62ShowQuestionChosen(Initializable controller, MouseEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentWidth = stage.getScene().getWidth();
        currentHeight = stage.getScene().getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent, currentWidth, currentHeight);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.GUI62_ap.setVisible(true);
        stage.setScene(scene);
    }

    public static void GUI63(Initializable controller, MouseEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentWidth = stage.getScene().getWidth();
        currentHeight = stage.getScene().getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent, currentWidth, currentHeight);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.GUI63_ap.setVisible(true);
        stage.setScene(scene);
    }
    public static void GUI65(Initializable controller, MouseEvent event){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentWidth = stage.getScene().getWidth();
        currentHeight = stage.getScene().getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent, currentWidth, currentHeight);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        gui11Controller.GUI65_ap.setVisible(true);
        stage.setScene(scene);
    }

    public static void GUI62(Initializable controller, ActionEvent event, ObservableList<Pair<String,String>> choiceQuestion){
        String path = "/com/example/baitaplonoop/GUInew.fxml";
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentWidth = stage.getScene().getWidth();
        currentHeight = stage.getScene().getHeight();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(controller.getClass().getResource(path));
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent, currentWidth, currentHeight);
        GUI11Controller gui11Controller = fxmlLoader.getController();
        FXMLLoader loader2 = new FXMLLoader(ChangeScene.class.getResource("/com/example/baitaplonoop/GUI62.fxml"));
        Parent root2;
        try {
            root2 = loader2.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GUI62Controller controller62 = loader2.getController();
        controller62.setChosenQuestions(choiceQuestion);
        gui11Controller.GUI62_ap.setVisible(true);
        gui11Controller.GUI62_ap.getChildren().setAll(root2);
        stage.setScene(scene);
    }
}
