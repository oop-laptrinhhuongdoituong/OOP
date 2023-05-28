package com.example.baitaplonoop.model;

public class Choice {
    private String choiceText;
    private double choiceGrade;
    private String choiceID;
    private String questionID;
    private boolean isSelected;
    private String choiceMedia;



    public Choice(String choiceText, double choiceGrade, String choiceID, String questionID, boolean isSelected, String choiceMedia) {
        this.choiceText = choiceText;
        this.choiceGrade = choiceGrade;
        this.choiceID = choiceID;
        this.questionID = questionID;
        this.isSelected = isSelected;
        this.choiceMedia = choiceMedia;
    }

    public Choice(String choiceText, double choiceGrade) {
        this.choiceText = choiceText;
        this.choiceGrade = choiceGrade;
    }


    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public double getChoiceGrade() {
        return choiceGrade;
    }

    public void setChoiceGrade(double choiceGrade) {
        this.choiceGrade = choiceGrade;
    }

    public String getChoiceID() {
        return choiceID;
    }

    public void setChoiceID(String choiceID) {
        this.choiceID = choiceID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getChoiceMedia() {
        return choiceMedia;
    }

    public void setChoiceMedia(String choiceMedia) {
        this.choiceMedia = choiceMedia;
    }
}
