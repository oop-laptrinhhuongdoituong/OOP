package com.example.baitaplonoop.util;


import com.example.baitaplonoop.model.Choice;
import com.example.baitaplonoop.model.Question;
import com.example.baitaplonoop.sql.DBConnect;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class AnchorPaneGUI7 extends AnchorPane {
    private AnchorPane questionPos;
    private AnchorPane questionContent;
    private Label questionNumber = new Label();
    public Label questionStatus = new Label();
    private Label questionMark = new Label("Mark out of 1.00");
    private Label questionFlag = new Label("Flag question");
    public ToggleGroup group = new ToggleGroup();
    public ArrayList<RadioButton> listRadioButton = new ArrayList<>();
    public ArrayList<CheckBox> listCheckBox = new ArrayList<>();
    private Text questionText = new Text();
    public Question question;
    public ArrayList<Choice> listChoice = new ArrayList<>();
    DBConnect db = new DBConnect();
    private int notNullChoice = 0;
    private int boxChecked = 0;

    public AnchorPaneGUI7(int position, String questionID) {
        setUpQuestionPos(position);

        this.setPrefWidth(1000);
        this.setPrefHeight(setUpQuestionContent(questionID));

        this.getChildren().addAll(questionPos, questionContent);
        AnchorPane.setTopAnchor(questionPos, 0.0);
        AnchorPane.setLeftAnchor(questionPos, 0.0);
        AnchorPane.setTopAnchor(questionContent, 0.0);
        AnchorPane.setRightAnchor(questionContent, 5.0);
        AnchorPane.setLeftAnchor(questionContent, 135.0);
        AnchorPane.setBottomAnchor(questionContent, 10.0);
        if(notNullChoice <= 1) {
            group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                    questionStatus.setText("Answered");
                }
            });
        }else{
            for(int i = 0; i < listCheckBox.size(); i++){
                listCheckBox.get(i).selectedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                        if(t1){
                            boxChecked ++;
                            questionStatus.setText("Answered");
                        }else{
                            boxChecked--;
                            if(boxChecked == 0){
                                questionStatus.setText("Not yet answered");
                            }
                        }
                    }
                });
            }
        }
    }

    public void setUpQuestionPos(int position){
        this.questionPos = new AnchorPane();
        this.questionPos.setStyle("-fx-background-color: #FFFFFF");
        this.questionPos.setMinSize(130, 200);
        this.questionNumber.setText("Question " + position);
        this.questionNumber.setTextFill(Paint.valueOf("#ea2d2d"));
        this.questionStatus.setText("Not yet answered");
        this.questionPos.getChildren().addAll(questionNumber, questionStatus, questionMark, questionFlag);
        AnchorPane.setTopAnchor(questionNumber, 14.0);
        AnchorPane.setLeftAnchor(questionNumber, 14.0);
        AnchorPane.setTopAnchor(questionStatus, 64.0);
        AnchorPane.setLeftAnchor(questionStatus, 14.0);
        AnchorPane.setTopAnchor(questionMark, 114.0);
        AnchorPane.setLeftAnchor(questionMark, 14.0);
        AnchorPane.setTopAnchor(questionFlag, 164.0);
        AnchorPane.setLeftAnchor(questionFlag, 14.0);
    }
    public double setUpQuestionContent(String questionID){
        this.questionContent = new AnchorPane();
        this.questionContent.setStyle("-fx-background-color: #F0FFFF");
        this.questionContent.setMinSize(850,240);
        ResultSet rs = db.getData("Select * from Question where questionID = '" + questionID + "'");
        double choiceHeight = 44.0;
        try {
            if(rs.next()){
                question = new Question(rs.getString("categoryID"), rs.getString("questionID"), rs.getString("questionText"), rs.getString("questionMedia"), Double.valueOf(rs.getString("questionMark")));
                this.questionText.setWrappingWidth(900);
                this.questionText.setText(questionID + ": " + rs.getString("questionText"));
                this.questionText.setTextAlignment(TextAlignment.JUSTIFY);
                this.questionContent.getChildren().add(questionText);
                AnchorPane.setTopAnchor(questionText, 14.0);
                AnchorPane.setLeftAnchor(questionText, 14.0);
                ResultSet rs1 = db.getData("Select * from Choice where questionID = '" + questionID + "'");

                while (rs1.next()){
                    if(rs1.getString("choiceGrade") == null){
                        listChoice.add(new Choice(rs1.getString("choiceText"), 0, rs1.getString("choiceID"), rs1.getString("questionID"), false, rs1.getString("choiceMedia")));
                    }else{
                        notNullChoice ++;
                        listChoice.add(new Choice(rs1.getString("choiceText"), Double.valueOf(rs1.getString("choiceGrade")), rs1.getString("choiceID"), rs1.getString("questionID"), false, rs1.getString("choiceMedia")));
                    }
                }
                choiceHeight += questionText.getLayoutBounds().getHeight();
                char c = 'A';
                if(notNullChoice <= 1) {
                    for (int i = 0; i < listChoice.size(); i++) {
                        RadioButton radioButton = new RadioButton(String.valueOf(c) + ". " + listChoice.get(i).getChoiceText());
                        radioButton.setMaxWidth(485);
                        radioButton.setWrapText(true);
                        radioButton.setAlignment(Pos.TOP_CENTER);
                        radioButton.setTextAlignment(TextAlignment.JUSTIFY);
                        listRadioButton.add(radioButton);
                        choiceHeight += 10.0;
                        radioButton.setToggleGroup(group);
                        this.questionContent.getChildren().add(radioButton);
                        AnchorPane.setTopAnchor(radioButton, choiceHeight);
                        AnchorPane.setLeftAnchor(radioButton, 30.0);
                        Text text = new Text(radioButton.getText());
                        text.setFont(radioButton.getFont());
                        text.setWrappingWidth(465);
                        choiceHeight += text.getLayoutBounds().getHeight();
                        c++;
                    }
                    AnchorPane.setBottomAnchor(listRadioButton.get(listRadioButton.size()-1), 10.0);
                }
                else {
                    for (int i = 0; i < listChoice.size(); i++) {
                        CheckBox checkBox = new CheckBox(String.valueOf(c) + ". " + listChoice.get(i).getChoiceText());
                        checkBox.setMaxWidth(485);
                        checkBox.setWrapText(true);
                        checkBox.setAlignment(Pos.TOP_CENTER);
                        checkBox.setTextAlignment(TextAlignment.JUSTIFY);
                        listCheckBox.add(checkBox);
                        choiceHeight += 10.0;
                        this.questionContent.getChildren().add(checkBox);
                        AnchorPane.setTopAnchor(checkBox, choiceHeight);
                        AnchorPane.setLeftAnchor(checkBox, 30.0);
                        Text text = new Text(checkBox.getText());
                        text.setFont(checkBox.getFont());
                        text.setWrappingWidth(465);
                        choiceHeight += text.getLayoutBounds().getHeight();
                        c++;
                    }
                    AnchorPane.setBottomAnchor(listCheckBox.get(listCheckBox.size()-1), 10.0);
                }
                choiceHeight += 20.0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return choiceHeight > this.questionContent.getMinHeight() ? choiceHeight : (this.questionContent.getMinHeight() + 10.0);
    }

    public int getNotNullChoice() {
        return notNullChoice;
    }
}
