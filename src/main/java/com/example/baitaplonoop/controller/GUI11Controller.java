package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.ChangeScene;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class GUI11Controller implements Initializable {
    @FXML
    ImageView imgSetting;
    @FXML
    ListView<String> lvQuiz = new ListView<>();
    @FXML
    Button btnTurnEditingOn;
    FXMLLoader fxmlLoader;
    GUI11PopUpController controller;
    static Dialog<String> dialog;
    DBConnect db = new DBConnect();
    public static MouseEvent settingEvent;

    public static String quizChosen = "";
    public void setListViewQuiz(){
        ArrayList<String> listQuiz = new ArrayList<>();
        ResultSet rs = db.getData("Select * from Quiz");
        try{
            while (rs.next()){
                listQuiz.add(rs.getString("quizName"));
            }
            lvQuiz.setItems(FXCollections.observableArrayList(listQuiz));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setEvent(){
        lvQuiz.setOnMouseClicked(mouseEvent -> {
            quizChosen = lvQuiz.getSelectionModel().getSelectedItem();
            ChangeScene.changeSceneUsingMouseEvent(this, "/com/example/baitaplonoop/GUI61.fxml", mouseEvent);
        });

        btnTurnEditingOn.setOnAction(event -> {
            ChangeScene.changeSceneUsingActionEvent((Initializable) this, "/com/example/baitaplonoop/GUI35AddingQuiz.fxml", event);
        });
        imgSetting.setOnMouseClicked(event -> {
            settingEvent = event;
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI11PopUp.fxml"));
            try {
                Parent parent = fxmlLoader.load();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        setListViewQuiz();
        setEvent();
    }
}
//
