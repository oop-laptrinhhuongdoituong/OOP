package com.example.baitaplonoop.util;

import com.example.baitaplonoop.model.Question;
import javafx.scene.control.Button;

public class addQuestion extends Question {
    private Button button;
    public addQuestion(){

    }

    public addQuestion(String categoryID, String questionID, String questionText, String questionImage, double questionMark, Button button) {
        super(categoryID, questionID, questionText, questionImage, questionMark);
        this.button = button;
    }

    public void setCategoryID(String categoryID) {
        super.setCategoryID(categoryID);
    }

    public String getCategoryID() {
        return super.getCategoryID();
    }

    public void setQuestionID(String questionID) {
        super.setQuestionID(questionID);
    }

    public String getQuestionID() {
        return super.getQuestionID();
    }

    public void setQuestionText(String questionText) {
        super.setQuestionText(questionText);
    }

    public String getQuestionText() {
        return super.getQuestionText();
    }

    public void setQuestionImage(String questionMedia) {
        super.setQuestionMedia(questionMedia);
    }

    public String getQuestionImage() {
        return super.getQuestionMedia();
    }

    public void setQuestionMark(double questionMark) {
        super.setQuestionMark(questionMark);
    }

    public double getQuestionMark() {
        return super.getQuestionMark();
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }
}

