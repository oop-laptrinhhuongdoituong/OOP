package com.example.baitaplonoop;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class addQuestion {
    private String questionID;
    private String questionText;
    private Button button;

    public addQuestion(String questionID, String questionText, Button button) {
        this.questionID = questionID;
        this.questionText = questionText;
        this.button = button;
    }
    public addQuestion(){}

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
