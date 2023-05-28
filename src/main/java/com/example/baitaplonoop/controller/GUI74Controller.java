package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.util.AnchorPaneFinish;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI72Controller.openTime;
import static com.example.baitaplonoop.controller.GUI73Controller.finishTime;

public class GUI74Controller implements Initializable {
    @FXML
    private Label lbStart;
    @FXML
    private Label lbState;
    @FXML
    private Label lbCompleted;
    @FXML
    private Label lbTime;
    @FXML
    private Label lbMarks;
    @FXML
    private Label lbGrade;
    @FXML
    private AnchorPane apQuestion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void setUpScene(ArrayList<AnchorPaneFinish> result, double marks, double yourmarks){
        lbStart.setText(openTime.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, h:mm a")));
        lbCompleted.setText(finishTime.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, h:mm a")));
        lbState.setText("Finished");
        Duration duration = Duration.between(openTime, finishTime);
        long hours = duration.toHours();
        long mins = duration.toMinutes() % 60;
        long secs = duration.toSeconds() % 60;
        String time = "";
        if(hours > 1){
            time += hours + " hours ";
        }else if(hours > 0){
            time += hours + " hour ";
        }
        if(mins > 1){
            time += mins + " mins ";
        }else if(mins > 0 || hours > 0){
            time += mins + " min ";
        }
        if(secs > 1){
            time += secs + " secs";
        }else{
            time += secs + " sec";
        }
        lbTime.setText(time);
        DecimalFormat df = new DecimalFormat("#.##");
        lbMarks.setText(df.format(yourmarks) + "/" + df.format(marks));
        double grade = yourmarks/marks;
        lbGrade.setText(df.format(grade*10.0) + " out of 10.00 (" + (int)Math.round(grade*100) + "%)");
        double questionHeight = 0;
        for(int i = 0; i < result.size(); i++){
            apQuestion.getChildren().add(result.get(i));
            AnchorPane.setTopAnchor(result.get(i), questionHeight);
            AnchorPane.setLeftAnchor(result.get(i), 0.0);
            AnchorPane.setRightAnchor(result.get(i), 0.0);
            questionHeight += result.get(i).getPrefHeight();
        }
    }

    public Label getLbStart() {
        return lbStart;
    }

    public void setLbStart(Label lbStart) {
        this.lbStart = lbStart;
    }

    public Label getLbState() {
        return lbState;
    }

    public void setLbState(Label lbState) {
        this.lbState = lbState;
    }

    public Label getLbCompleted() {
        return lbCompleted;
    }

    public void setLbCompleted(Label lbCompleted) {
        this.lbCompleted = lbCompleted;
    }

    public Label getLbTime() {
        return lbTime;
    }

    public void setLbTime(Label lbTime) {
        this.lbTime = lbTime;
    }

    public Label getLbMarks() {
        return lbMarks;
    }

    public void setLbMarks(Label lbMarks) {
        this.lbMarks = lbMarks;
    }

    public Label getLbGrade() {
        return lbGrade;
    }

    public void setLbGrade(Label lbGrade) {
        this.lbGrade = lbGrade;
    }

    public AnchorPane getApQuestion() {
        return apQuestion;
    }

    public void setApQuestion(AnchorPane apQuestion) {
        this.apQuestion = apQuestion;
    }
}
