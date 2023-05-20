package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.AnchorPaneGUI7;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.quizChosen;

public class GUI73Controller implements Initializable {
    @FXML
    private AnchorPane apQuestion;
    DBConnect db = new DBConnect();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = db.getData("Select questionID from QuestionInQuiz where quizName = N'" + quizChosen + "'");
        int i = 0;
        try{
            while(rs.next()){
                AnchorPaneGUI7 question = new AnchorPaneGUI7(i+1, rs.getString("questionID"));
                apQuestion.getChildren().add(question);
                AnchorPane.setRightAnchor(question, 0.0);
                AnchorPane.setLeftAnchor(question, 0.0);
                AnchorPane.setTopAnchor(question, 250.0*i);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
