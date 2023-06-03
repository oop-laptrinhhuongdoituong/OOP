package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.ChangeScene;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GUI11Controller implements Initializable {
    public Tab questionTab_tp;
    public AnchorPane GUI21_tp;
    public AnchorPane GUI33_tp;
    public AnchorPane GUI34_tp;
    public Tab categoryTab_tp;
    public Tab importTab_tp;
    public TabPane tabPane;
    public AnchorPane listQuiz_ap;
    public Button setting_btn;
    public AnchorPane addQuestion_ap;
    public Label message;
    public AnchorPane addQuiz_ap;
    public AnchorPane GUI61_ap;
    public AnchorPane GUI62_ap;
    public AnchorPane GUI63_ap;
    @FXML
    ListView<String> lvQuiz = new ListView<>();
    @FXML
    Button btnTurnEditingOn;
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










            ChangeScene.GUI61PreviewQuiz(this, mouseEvent);
        });

        btnTurnEditingOn.setOnAction(turnEditingOneEvent -> ChangeScene.GUI35AddQuiz(this,turnEditingOneEvent));
        setting_btn.setOnMouseClicked(event -> {
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

