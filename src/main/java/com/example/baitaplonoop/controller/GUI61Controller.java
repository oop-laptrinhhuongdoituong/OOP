package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.ChangeScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.quizChosen;

public class GUI61Controller implements Initializable {
    @FXML
    public Label lbQuiz;
    @FXML
    Button btnPreviewQuiz;
    @FXML
    public Label lbTimeLimit;
    @FXML
    public ImageView imgAddQuestionToQuiz;
    public static ActionEvent StartEvent;

    DBConnect db = new DBConnect();
    public static String timeLimit = "";
    public static boolean isShuffle;
    public static boolean isOpenable;

    void setUpScene(){
        lbQuiz.setText(quizChosen);
        ResultSet rs = db.getData("Select * from Quiz where quizName = N'" + quizChosen + "'");
        try {
            while (rs.next()) {
                timeLimit = rs.getString("timeLimit");
                lbTimeLimit.setText(timeLimit + " mins");
                isShuffle = rs.getBoolean("shuffle");
                if(rs.getTimestamp("openTime").toLocalDateTime().isAfter(LocalDateTime.now()) || rs.getTimestamp("closeTime").toLocalDateTime().isBefore(LocalDateTime.now())){
                    isOpenable = false;
                }else{
                    isOpenable = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void setEvent(){
        btnPreviewQuiz.setOnAction(event -> {
            StartEvent = event;
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI72.fxml"));
            try {
                Parent parent = fxmlLoader.load();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        imgAddQuestionToQuiz.setOnMouseClicked(mouseEvent -> {
            ChangeScene.changeSceneUsingMouseEvent(this, "/com/example/baitaplonoop/GUI62.fxml", mouseEvent);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpScene();
        setEvent();
    }
}