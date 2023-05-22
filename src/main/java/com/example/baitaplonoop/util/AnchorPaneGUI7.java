package com.example.baitaplonoop.util;


import com.example.baitaplonoop.sql.DBConnect;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

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
    private Text questionText = new Text();
    private ArrayList<RadioButton> listChoice = new ArrayList<>();
    DBConnect db = new DBConnect();

    public AnchorPaneGUI7(int position, String questionID) {
        setUpQuestionPos(position);
        setUpQuestionContent(questionID);
        this.setMinSize(1000, 250);

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
    public void setUpQuestionContent(String questionID){
        this.questionContent = new AnchorPane();
        this.questionContent.setStyle("-fx-background-color: #F0FFFF");
        this.questionContent.setMinSize(850,240);
        ResultSet rs = db.getData("Select questionText from Question where questionID = '" + questionID + "'");
        try {
            if(rs.next()){
                this.questionText.setWrappingWidth(500);
                this.questionText.setText(questionID + ": " + rs.getString("questionText"));
                this.questionContent.getChildren().add(questionText);
                AnchorPane.setTopAnchor(questionText, 14.0);
                AnchorPane.setLeftAnchor(questionText, 14.0);
                ResultSet rs1 = db.getData("Select choiceText from Choice where questionID = '" + questionID + "'");
                char c = 'A';
                while (rs1.next()){
                    RadioButton radioButton = new RadioButton(String.valueOf(c) + ". " + rs1.getString("choiceText"));
                    radioButton.setMaxWidth(485);
                    radioButton.setWrapText(true);
                    radioButton.setAlignment(Pos.TOP_CENTER);
                    listChoice.add(radioButton);
                    c++;
                }
                double choiceHeight = 44.0 + questionText.getLayoutBounds().getHeight();
                for(int i = 0; i < listChoice.size(); i++){
                    choiceHeight += 10.0;
                    listChoice.get(i).setToggleGroup(group);
                    this.questionContent.getChildren().add(listChoice.get(i));
                    AnchorPane.setTopAnchor(listChoice.get(i), choiceHeight);
                    AnchorPane.setLeftAnchor(listChoice.get(i), 30.0);
                    Text text = new Text(listChoice.get(i).getText());
                    text.setFont(listChoice.get(i).getFont());
                    text.setWrappingWidth(465);
                    choiceHeight += text.getLayoutBounds().getHeight();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
