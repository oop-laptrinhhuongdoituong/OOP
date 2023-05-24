package com.example.baitaplonoop.model;

public class Question {
    private String categoryID;
    private String questionID;
    private String questionText;
    private String questionMedia;
    private double questionMark;

    public Question(String categoryID, String questionID, String questionText, String questionMedia, double questionMark) {
        this.categoryID = categoryID;
        this.questionID = questionID;
        this.questionText = questionText;
        this.questionMedia = questionMedia;
        this.questionMark = questionMark;
    }

    public Question() {
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
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

    public String getQuestionMedia() {
        return questionMedia;
    }

    public void setQuestionMedia(String questionMedia) {
        this.questionMedia = questionMedia;
    }

    public double getQuestionMark() {
        return questionMark;
    }

    public void setQuestionMark(double questionMark) {
        this.questionMark = questionMark;
    }
}
