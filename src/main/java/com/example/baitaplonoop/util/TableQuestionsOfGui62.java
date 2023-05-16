package com.example.baitaplonoop.util;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TableQuestionsOfGui62 {
    CheckBox multiQuestionsChoice;
    Text order;
    Label setting;
    String questionText;
    String questionID;
    Label plusIcon;
    Label deleteIcon;

    public TableQuestionsOfGui62(CheckBox multiQuestionsChoice, Text order, Label setting, String questionText, String questionID, Label plusIcon, Label deleteIcon, TextField questionMark) {
        this.multiQuestionsChoice = multiQuestionsChoice;
        this.order = order;
        this.setting = setting;
        this.questionText = questionText;
        this.questionID = questionID;
        this.plusIcon = plusIcon;
        this.deleteIcon = deleteIcon;
        this.questionMark = questionMark;
    }

    public TableQuestionsOfGui62() {
    }

    public CheckBox getMultiQuestionsChoice() {
        return multiQuestionsChoice;
    }

    public void setMultiQuestionsChoice(CheckBox multiQuestionsChoice) {
        this.multiQuestionsChoice = multiQuestionsChoice;
    }

    public Text getOrder() {
        return order;
    }

    public void setOrder(Text order) {
        this.order = order;
    }

    public Label getSetting() {
        return setting;
    }

    public void setSetting(Label setting) {
        this.setting = setting;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public Label getPlusIcon() {
        return plusIcon;
    }

    public void setPlusIcon(Label plusIcon) {
        this.plusIcon = plusIcon;
    }

    public Label getDeleteIcon() {
        return deleteIcon;
    }

    public void setDeleteIcon(Label deleteIcon) {
        this.deleteIcon = deleteIcon;
    }

    public TextField getQuestionMark() {
        return questionMark;
    }

    public void setQuestionMark(TextField questionMark) {
        this.questionMark = questionMark;
    }

    TextField questionMark;

}
