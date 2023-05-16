package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.quizChosen;

public class GUI61Controller implements Initializable {
    @FXML
    public Label lbQuiz;
    @FXML
    Button btnPreviewQuiz;
    @FXML
    public Label lbTimeLimit;

    DBConnect db = new DBConnect();
    public static String timeLimit = "";

    void setUpScene(){
        lbQuiz.setText(quizChosen);
        ResultSet rs = db.getData("Select * from Quiz where quizName = N'" + quizChosen + "'");
        try {
            while (rs.next()) {
                timeLimit = rs.getString("timeLimit");
                lbTimeLimit.setText(timeLimit + " mins");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void setEvent(){
        btnPreviewQuiz.setOnAction(event -> {
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpScene();
        setEvent();
    }
}