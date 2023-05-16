package com.example.baitaplonoop.util;

import javafx.scene.control.CheckBox;

public class QuestionCheckBoxInTable {
    private String questionID;
    private String questionText;
    private CheckBox checkBox;

    public QuestionCheckBoxInTable(String questionID, String questionText, CheckBox checkBox) {
        this.questionID = questionID;
        this.questionText = questionText;
        this.checkBox = checkBox;
    }

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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}