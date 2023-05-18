package com.example.baitaplonoop.util;


import com.example.baitaplonoop.sql.DBConnect;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnchorPaneGUI7 extends AnchorPane {
    private AnchorPane questionPos;
    private AnchorPane questionContent;
    private Label questionNumber = new Label();
    private Label questionStatus = new Label();
    private Label questionMark = new Label("Mark out of 1.00");
    private Label questionFlag = new Label("Flag question");
    private ToggleGroup group = new ToggleGroup();
    private ArrayList<RadioButton> listRadioButton = new ArrayList<>();
    private Label questionText = new Label();
    private ArrayList<RadioButton> listChoice = new ArrayList<>();
    DBConnect db = new DBConnect();

    public AnchorPaneGUI7(int position, String questionID) {
        setUpQuestionPos(position);
        setUpQuestionContent(questionID);
        this.setPrefSize(100, 250);

        this.getChildren().addAll(questionPos, questionContent);
        AnchorPane.setTopAnchor(questionPos, 0.0);
        AnchorPane.setLeftAnchor(questionPos, 0.0);
        AnchorPane.setTopAnchor(questionContent, 0.0);
        AnchorPane.setRightAnchor(questionContent, 5.0);
        AnchorPane.setLeftAnchor(questionContent, 135.0);
    }

    public void setUpQuestionPos(int position){
        this.questionPos = new AnchorPane();
        this.questionPos.setStyle("-fx-background-color: #FFFFFF");
        this.questionPos.setPrefSize(130, 200);
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
    public void setUpQuestionContent(String questionID){
        this.questionContent = new AnchorPane();
        this.questionContent.setStyle("-fx-background-color: #F0FFFF");
        this.questionContent.setPrefSize(850,240);
        ResultSet rs = db.getData("Select questionText from Question where questionID = '" + questionID + "'");
        try {
            if(rs.next()){
                this.questionText.setText(questionID + ": " + rs.getString("questionText"));
                this.questionContent.getChildren().add(questionText);
                AnchorPane.setTopAnchor(questionText, 14.0);
                AnchorPane.setLeftAnchor(questionText, 14.0);
                ResultSet rs1 = db.getData("Select choiceText from Choice where questionID = '" + questionID + "'");
                char c = 'A';
                while (rs1.next()){
                    listChoice.add(new RadioButton(String.valueOf(c) + ". " + rs1.getString("choiceText")));
                    c++;
                }
                for(int i = 0; i < listChoice.size(); i++){
                    listChoice.get(i).setToggleGroup(group);
                    this.questionContent.getChildren().add(listChoice.get(i));
                    AnchorPane.setTopAnchor(listChoice.get(i), 60.0 + 25.0*i);
                    AnchorPane.setLeftAnchor(listChoice.get(i), 30.0);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
