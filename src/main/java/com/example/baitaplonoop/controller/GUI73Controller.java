package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.AnchorPaneGUI7;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.quizChosen;

public class GUI73Controller implements Initializable {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label quizName_lb;
    @FXML
    private Label timerLabel;
    private String quizName;
    public AnchorPane QuestionOverview_ap;
    @FXML
    private AnchorPane apQuestion;
    @FXML
    private AnchorPane anchorPane1;
    DBConnect db = new DBConnect();
    private Timeline timeline;
    private int seconds = 0;

    private void updateTimerLabel() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, secs);
        timerLabel.setText("Time left " + formattedTime);
    }

    private void stopTimer() {
        timeline.stop();
    }

    private void timerAction(int a) {
        seconds = a * 60;
        String hours;
        if (a / 60 < 10) hours = "0" + Integer.toString(a / 60);
        else hours = Integer.toString(a / 60);
        String minutes;
        if (a % 60 < 10) minutes = "0" + Integer.toString(a % 60);
        else minutes = Integer.toString(a % 60);
        timerLabel.setText("Time left " + hours + ":" + minutes + ":00");
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    seconds--;
                    updateTimerLabel();
                    if (seconds == 0) {
                        stopTimer();
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    void insertNavigation(int numberButton){
            scrollPane.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0;");
            GridPane gridPane = new GridPane();
            gridPane.setVgap(1);
            gridPane.setHgap(1);
            AnchorPane.setTopAnchor(gridPane, 0.0); // cách top 98 pixel
            AnchorPane.setLeftAnchor(gridPane, 0.0); // cách left 50 pixel
            AnchorPane.setRightAnchor(gridPane, 0.00); // cách right 50 pixel
            for (int i = 0; i < numberButton; i++) {
                Button button = new Button();
                button.setAlignment(Pos.TOP_CENTER);
                button.setFont(Font.font(15));
                button.setStyle("-fx-background-color: linear-gradient(to bottom, #FFFFFF 70%, #C0C0C0 30%)");
                if(0<=i&&i<9) button.setText("0"+String.valueOf(i+1));
                else button.setText(String.valueOf(i+1));
                button.setId("question" + String.valueOf(i+1) );
                GridPane.setConstraints(button, i % 5, i / 5);
                GridPane.setMargin(button,new Insets(2, 1, 2, 1));
                GridPane.setHgrow(button, Priority.ALWAYS);
                GridPane.setVgrow(button, Priority.ALWAYS); // cho phép button mở rộng theo chiều dọc
                gridPane.getChildren().add(button);
            }
            // Thêm gridpane vào anchorpane
            anchorPane1.getChildren().add(gridPane);


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = db.getData("Select questionID from QuestionInQuiz where quizName = N'" + quizChosen + "'");
        int i = 0;
        double questionHeight = 0.0;
        try {
            while (rs.next()) {
                AnchorPaneGUI7 question = new AnchorPaneGUI7(i + 1, rs.getString("questionID"));
                apQuestion.getChildren().add(question);
                AnchorPane.setRightAnchor(question, 0.0);
                AnchorPane.setLeftAnchor(question, 0.0);
                AnchorPane.setTopAnchor(question, questionHeight);
                questionHeight += 10.0;
                questionHeight += question.getPrefHeight();
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       insertNavigation(100);
    }
}