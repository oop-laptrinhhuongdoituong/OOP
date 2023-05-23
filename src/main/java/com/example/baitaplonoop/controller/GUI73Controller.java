package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.AnchorPaneGUI7;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.quizChosen;

public class GUI73Controller implements Initializable {
    @FXML
    private Label quizName_lb;
    private String  quizName;
    @FXML
    private AnchorPane apQuestion;
    DBConnect db = new DBConnect();
    @FXML
    private Label timerLabel;
    private Timeline timeline;
    private int seconds = 0;
    private void updateTimerLabel() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, secs);
        timerLabel.setText("Time left "+formattedTime);
    }
    private  void stopTimer(){
        timeline.stop();
    }
    private void timerAction(int a){
        seconds=a*60;
        String hours;
        if(a/60<10)hours="0"+Integer.toString(a/60);
        else hours=Integer.toString(a/60);
        String minutes;
        if(a%60<10)minutes="0"+Integer.toString(a%60);
        else minutes=Integer.toString(a%60);
        timerLabel .setText("Time left "+hours+":"+minutes+":00");
         timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    seconds--;
                    updateTimerLabel();
                    if (seconds ==0) {
                        stopTimer();
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = db.getData("Select questionID from QuestionInQuiz where quizName = N'" + quizChosen + "'");
        int i = 0;
        try {
            while (rs.next()) {
                AnchorPaneGUI7 question = new AnchorPaneGUI7(i + 1, rs.getString("questionID"));
                apQuestion.getChildren().add(question);
                AnchorPane.setRightAnchor(question, 0.0);
                AnchorPane.setLeftAnchor(question, 0.0);
                AnchorPane.setTopAnchor(question, 250.0 * i);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        quizName = quizChosen;
        quizName_lb.setText(quizName + " / Edit quiz");
        LocalDateTime localDateTime = LocalDateTime.now();
        ResultSet rs1 = db.getData("select * from dbo.Quiz where quizName = N'" + quizName + "'");
        LocalDateTime openTime;
        LocalDateTime closeTime;

        try {
            if (rs1.next()) {
                openTime = rs1.getTimestamp("openTime").toLocalDateTime();
                closeTime = rs1.getTimestamp("closeTime").toLocalDateTime();
                if (localDateTime.isAfter(openTime) && localDateTime.isBefore(closeTime)) {
                    timerAction(rs1.getInt("timeLimit"));
                }
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
