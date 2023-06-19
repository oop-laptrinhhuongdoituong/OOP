package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.AnchorPaneFinish;
import com.example.baitaplonoop.util.AnchorPaneGUI7;
import com.example.baitaplonoop.util.BreadCrumb;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.*;

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
    private GridPane gridPane = new GridPane();
    @FXML
    private ScrollPane questionView_cr;
    DBConnect db = new DBConnect();
    private Timeline timeline;
    private int seconds = 0;
    @FXML
    Hyperlink hlFinish;
    @FXML
    FlowPane flowPane1;
    public static LocalDateTime finishTime;
    ArrayList<AnchorPaneGUI7> listQuestion = new ArrayList<>();
    ArrayList<AnchorPaneFinish> result = new ArrayList<>();
    ArrayList<Button> listButton = new ArrayList<>();

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

    void insertIntoGridPane() {
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0;");

        gridPane.setVgap(1);
        gridPane.setHgap(1);
        AnchorPane.setTopAnchor(gridPane, 0.0); // cách top 98 pixel
        AnchorPane.setLeftAnchor(gridPane, 0.0); // cách left 50 pixel
        AnchorPane.setRightAnchor(gridPane, 0.00); // cách right 50 pixel
        anchorPane1.getChildren().add(gridPane);

    }

    private void scrollToNode(ScrollPane scrollPane, AnchorPane node) {
        Bounds bounds = node.getBoundsInParent();
        double y = bounds.getMinY();
        double contentHeight = scrollPane.getContent().getBoundsInLocal().getHeight();
        double viewportHeight = scrollPane.getViewportBounds().getHeight();
        double vValue = y / (contentHeight - viewportHeight);
        scrollPane.setVvalue(vValue);
        scrollPane.setHvalue(0.00);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        flowPane1.getChildren().addAll(breadCrumb);
        insertIntoGridPane();
        ResultSet rs = db.getData("Select questionID from QuestionInQuiz where quizName = N'" + quizChosen + "'");
        int i = 0;
        double questionHeight = 0.0;
        try {
            while (rs.next()) {
                AnchorPaneGUI7 question = new AnchorPaneGUI7(i + 1, rs.getString("questionID"));
                ///tao button tuong ung voi cau hoi
                Button button = new Button();
                button.setAlignment(Pos.TOP_CENTER);
                button.setFont(Font.font(15));
                button.setStyle("-fx-background-color: linear-gradient(to bottom, #FFFFFF 70%, #C0C0C0 30%)");
                if (0 <= i && i < 9) button.setText("0" + String.valueOf(i + 1));
                else button.setText(String.valueOf(i + 1));
                button.setId("question" + String.valueOf(i + 1));
                listButton.add(button);
                GridPane.setConstraints(button, i % 5, i / 5);
                GridPane.setMargin(button, new Insets(2, 1, 2, 1));
                GridPane.setHgrow(button, Priority.ALWAYS);
                GridPane.setVgrow(button, Priority.ALWAYS); // cho phép button mở rộng theo chiều dọc
                gridPane.getChildren().add(button);
                ///////////////////////////////////Hung
                apQuestion.getChildren().add(question);
                listQuestion.add(question);

                AnchorPane.setRightAnchor(question, 0.0);
                AnchorPane.setLeftAnchor(question, 0.0);
                AnchorPane.setTopAnchor(question, questionHeight);
                button.setOnAction(event -> {
                    scrollToNode(questionView_cr, question);
                });
                questionHeight += 10.0;
                questionHeight += question.getPrefHeight();
                if(question.getNotNullChoice() <= 1) {
                    question.group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                        @Override
                        public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                            question.questionStatus.setText("Answered");
                            listButton.get(question.position-1).setStyle("-fx-background-color: linear-gradient(to bottom, #FFFFFF 70%, #FFFFFF 30%)");
                        }
                    });
                }else{
                    for(int j = 0; j < question.listCheckBox.size(); j++){
                        question.listCheckBox.get(j).selectedProperty().addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                                if(t1){
                                    question.boxChecked ++;
                                    question.questionStatus.setText("Answered");
                                    listButton.get(question.position-1).setStyle("-fx-background-color: linear-gradient(to bottom, #FFFFFF 70%, #FFFFFF 30%)");
                                }else{
                                    question.boxChecked--;
                                    if(question.boxChecked == 0){
                                        question.questionStatus.setText("Not yet answered");
                                        listButton.get(question.position-1).setStyle("-fx-background-color: linear-gradient(to bottom, #FFFFFF 70%, #C0C0C0 30%)");
                                    }
                                }
                            }
                        });
                    }
                }
                i++;
            }
            quizName = quizChosen;
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
            hlFinish.setOnMouseClicked(mouseEvent -> {
                finishTime = LocalDateTime.now();
                double marks = 0;
                for(int k = 0; k < listQuestion.size(); k++){
                    double questionMark = 0;
                    if(listQuestion.get(k).getNotNullChoice() <= 1){
                        String correctAnswer = "";
                        for(int j = 0; j < listQuestion.get(k).listRadioButton.size(); j++){
                            if(listQuestion.get(k).listRadioButton.get(j).isSelected()){
                                questionMark += listQuestion.get(k).question.getQuestionMark() * listQuestion.get(k).listChoice.get(j).getChoiceGrade();
                            }
                            if(listQuestion.get(k).listChoice.get(j).getChoiceGrade() == 1){
                                correctAnswer = String.valueOf((char) (j+65));
                            }
                        }
                        marks += questionMark;
                        if(questionMark == 0){
                            result.add(new AnchorPaneFinish(listQuestion.get(k), "The correct answer is: " + correctAnswer));
                        }else{
                            result.add(new AnchorPaneFinish(listQuestion.get(k), "Correct!"));
                        }
                    }else{
                        String correctAnswer = "";
                        boolean isHaveMark = true;
                        for(int j = 0; j < listQuestion.get(k).listCheckBox.size(); j++){
                            if(listQuestion.get(k).listCheckBox.get(j).isSelected()){
                                if(listQuestion.get(k).listChoice.get(j).getChoiceGrade() == 0){
                                    isHaveMark = false;
                                }
                                questionMark += listQuestion.get(k).question.getQuestionMark() * listQuestion.get(k).listChoice.get(j).getChoiceGrade();
                            }
                            if(listQuestion.get(k).listChoice.get(j).getChoiceGrade() > 0){
                                correctAnswer += String.valueOf((char) (j+65)) + " ";
                            }
                        }
                        if(isHaveMark == false){
                            questionMark = 0;
                        }
                        marks += questionMark;
                        if(questionMark == 1){
                            result.add(new AnchorPaneFinish(listQuestion.get(k), "Correct!"));
                        }else{
                            result.add(new AnchorPaneFinish(listQuestion.get(k), "The correct answer is: " + correctAnswer));
                        }
                    }
                }

                try {
                    int rowUpdated = db.updateQuizMark(marks, quizChosen);
                    boolean rowInserted = db.insertIntoHistory(quizChosen, marks, finishTime);

                    Hyperlink result_hl=new Hyperlink("Preview Result");
                    BreadCrumb.changeBreadCrumb(breadCrumb,level,result_hl);
                    BreadCrumb.addBreadCrumb(breadCrumb,level,3,result_hl);
                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(this.getClass().getResource("/com/example/baitaplonoop/GUI74.fxml"));
                    Parent parent = loader.load();
                    Scene scene = new Scene(parent);
                    GUI74Controller controller = loader.getController();
                    controller.setUpScene(result, (double) listQuestion.size(), marks);
                    stage.setScene(scene);
                    stage.setMaximized(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            timerLabel.textProperty().addListener((observableValue, s, t1) -> {
                if(t1.equals("Time left 00:00:00")){
                    finishTime = LocalDateTime.now();
                    double marks = 0;
                    for(int k = 0; k < listQuestion.size(); k++){
                        double questionMark = 0;
                        if(listQuestion.get(k).getNotNullChoice() <= 1){
                            String correctAnswer = "";
                            for(int j = 0; j < listQuestion.get(k).listRadioButton.size(); j++){
                                if(listQuestion.get(k).listRadioButton.get(j).isSelected()){
                                    questionMark += listQuestion.get(k).question.getQuestionMark() * listQuestion.get(k).listChoice.get(j).getChoiceGrade();
                                }
                                if(listQuestion.get(k).listChoice.get(j).getChoiceGrade() == 1){
                                    correctAnswer = String.valueOf((char) (j+65));
                                }
                            }
                            marks += questionMark;
                            if(questionMark == 0){
                                result.add(new AnchorPaneFinish(listQuestion.get(k), "The correct answer is: " + correctAnswer));
                            }else{
                                result.add(new AnchorPaneFinish(listQuestion.get(k), "Correct!"));
                            }
                        }else{
                            String correctAnswer = "";
                            boolean isHaveMark = true;
                            for(int j = 0; j < listQuestion.get(k).listCheckBox.size(); j++){
                                if(listQuestion.get(k).listCheckBox.get(j).isSelected()){
                                    if(listQuestion.get(k).listChoice.get(j).getChoiceGrade() == 0){
                                        isHaveMark = false;
                                    }
                                    questionMark += listQuestion.get(k).question.getQuestionMark() * listQuestion.get(k).listChoice.get(j).getChoiceGrade();
                                }
                                if(listQuestion.get(k).listChoice.get(j).getChoiceGrade() > 0){
                                    correctAnswer += String.valueOf((char) (j+65)) + " ";
                                }
                            }
                            if(isHaveMark == false){
                                questionMark = 0;
                            }
                            marks += questionMark;
                            if(questionMark == 1){
                                result.add(new AnchorPaneFinish(listQuestion.get(k), "Correct!"));
                            }else{
                                result.add(new AnchorPaneFinish(listQuestion.get(k), "The correct answer is: " + correctAnswer));
                            }
                        }
                    }

                    try {
                        int rowUpdated = db.updateQuizMark(marks, quizChosen);
                        boolean rowInserted = db.insertIntoHistory(quizChosen, marks, finishTime);
                        Hyperlink result_hl=new Hyperlink("Preview Result");
                        BreadCrumb.changeBreadCrumb(breadCrumb,level,result_hl);
                        BreadCrumb.addBreadCrumb(breadCrumb,level,3,result_hl);
                        Stage stage = (Stage) timerLabel.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(this.getClass().getResource("/com/example/baitaplonoop/GUI74.fxml"));
                        Parent parent = loader.load();
                        Scene scene = new Scene(parent);
                        GUI74Controller controller = loader.getController();
                        controller.setUpScene(result, (double) listQuestion.size(), marks);
                        stage.setScene(scene);
                        stage.setMaximized(true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

