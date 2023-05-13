package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.quizChosen;

public class GUI61Controller implements Initializable {
    @FXML
    public Label lbQuiz;
    @FXML
    public Label lbQuizTree;
    @FXML
    public Label lbTimeLimit;

    DBConnect db = new DBConnect();

    public void setUpScene(){
        lbQuiz.setText(quizChosen);
        lbQuizTree.setText(quizChosen);
        ResultSet rs = db.getData("Select * from Quiz where quizName = N'" + quizChosen + "'");
        try {
            while (rs.next()) {
                lbTimeLimit.setText(rs.getString("timeLimit") + " mins");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpScene();
    }
}