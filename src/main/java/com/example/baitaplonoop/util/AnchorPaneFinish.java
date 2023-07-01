package com.example.baitaplonoop.util;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AnchorPaneFinish extends AnchorPane{
    private AnchorPaneGUI7 questionAnswered;
    private AnchorPane correctStatus;
    private Label correctAnswer;

    public AnchorPaneFinish(AnchorPaneGUI7 questionAnswered, String correctAnswer) {
        this.questionAnswered = questionAnswered;
        setUpCorrectStatus(correctAnswer);
        this.setPrefWidth(1000);
        this.setPrefHeight(this.questionAnswered.getPrefHeight() + 60 + 30);
        this.getChildren().addAll(this.questionAnswered, correctStatus);
        AnchorPane.setTopAnchor(this.questionAnswered, 0.0);
        AnchorPane.setLeftAnchor(this.questionAnswered, 0.0);
        AnchorPane.setRightAnchor(this.questionAnswered, 0.0);
        AnchorPane.setTopAnchor(this.correctStatus, this.questionAnswered.getPrefHeight());
        AnchorPane.setLeftAnchor(this.correctStatus, 135.0);
        AnchorPane.setRightAnchor(this.correctStatus, 0.0);
    }

    public void setUpCorrectStatus(String correctAnswer){
        correctStatus = new AnchorPane();
        correctStatus.setMinSize(850, 60);
        if(correctAnswer.equals("Correct!")){
            correctStatus.setStyle("-fx-background-color: #33FF99");
        }else{
            correctStatus.setStyle("-fx-background-color: #FFE4B5");
        }
        this.correctAnswer = new Label();
        this.correctAnswer.setText(correctAnswer);
        correctStatus.getChildren().add(this.correctAnswer);
        AnchorPane.setTopAnchor(this.correctAnswer, 15.0);
        AnchorPane.setLeftAnchor(this.correctAnswer, 14.0);
    }
}
