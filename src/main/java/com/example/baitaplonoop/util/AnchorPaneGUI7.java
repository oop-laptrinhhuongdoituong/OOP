package com.example.baitaplonoop.util;


import com.example.baitaplonoop.model.Choice;
import com.example.baitaplonoop.model.Question;
import com.example.baitaplonoop.sql.DBConnect;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import static com.example.baitaplonoop.controller.GUI61Controller.isShuffle;

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
    public int boxChecked = 0;
    public int position;

    public AnchorPaneGUI7(int position, String questionID) {
        this.position = position;
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
        double choiceHeight = 0;
        try {
            if(rs.next()){
                question = new Question(rs.getString("categoryID"), rs.getString("questionID"), rs.getString("questionText"), rs.getString("questionMedia"), Double.valueOf(rs.getString("questionMark")));
                this.questionText.setWrappingWidth(900);
                this.questionText.setText(questionID + ": " + rs.getString("questionText"));
                this.questionText.setTextAlignment(TextAlignment.JUSTIFY);
                this.questionContent.getChildren().add(questionText);
                AnchorPane.setTopAnchor(questionText, 14.0);
                AnchorPane.setLeftAnchor(questionText, 14.0);
                choiceHeight += questionText.getLayoutBounds().getHeight() + 14.0;
                if(question.getQuestionMedia() != null){
                    if(question.getQuestionMedia().substring(question.getQuestionMedia().length() - 3, question.getQuestionMedia().length()).equals("png")){
                        Image image = new Image("file:" + question.getQuestionMedia());
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setFitWidth(450);
                        imageView.setFitHeight(300);
                        imageView.setPreserveRatio(true);
                        this.questionContent.getChildren().add(imageView);
                        choiceHeight += 14.0;
                        AnchorPane.setTopAnchor(imageView, choiceHeight);
                        AnchorPane.setLeftAnchor(imageView, 30.0);
                        choiceHeight += 300;
                    } else if (question.getQuestionMedia().substring(question.getQuestionMedia().length() - 3, question.getQuestionMedia().length()).equals("mp4")) {
                        MediaView mediaViewVideo = new MediaView();
                        Path pathVideo = Paths.get(question.getQuestionMedia()).toAbsolutePath();
                        Media media = new Media(pathVideo.toUri().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        mediaViewVideo.setMediaPlayer(mediaPlayer);
                        mediaViewVideo.setFitWidth(450);
                        mediaViewVideo.setFitHeight(300);
                        mediaViewVideo.setPreserveRatio(true);
                        this.questionContent.getChildren().add(mediaViewVideo);
                        choiceHeight += 14.0;
                        AnchorPane.setTopAnchor(mediaViewVideo, choiceHeight);
                        AnchorPane.setLeftAnchor(mediaViewVideo, 30.0);
                        choiceHeight += 314;
                        Button play = new Button("Play");
                        Button pause = new Button("Pause");
                        this.questionContent.getChildren().add(play);
                        this.questionContent.getChildren().add(pause);
                        play.setOnAction(e -> mediaPlayer.play());
                        pause.setOnAction(e -> mediaPlayer.pause());
                        AnchorPane.setTopAnchor(play, choiceHeight);
                        AnchorPane.setLeftAnchor(play, 30.0);
                        AnchorPane.setTopAnchor(pause, choiceHeight);
                        AnchorPane.setLeftAnchor(pause, 100.0);
                        choiceHeight += 40.0;
                        Slider timeSlider = new Slider();
                        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
                            if (!timeSlider.isValueChanging()) {
                                timeSlider.setValue(newTime.toSeconds() / mediaPlayer.getTotalDuration().toSeconds() * 100);
                            }
                        });
                        timeSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
                            if (timeSlider.isValueChanging()) {
                                mediaPlayer.seek(mediaPlayer.getTotalDuration().multiply(newValue.doubleValue() / 100));
                            }
                        });
                        this.questionContent.getChildren().add(timeSlider);
                        AnchorPane.setTopAnchor(timeSlider, choiceHeight);
                        AnchorPane.setLeftAnchor(timeSlider, 30.0);
                        choiceHeight += 20.0;
                    } else if (question.getQuestionMedia().substring(question.getQuestionMedia().length() - 3, question.getQuestionMedia().length()).equals("gif")) {
                        Image image = new Image("file:" + question.getQuestionMedia());
                        ImageView imageView = new ImageView();
                        imageView.setImage(image);
                        imageView.setFitWidth(450);
                        imageView.setFitHeight(300);
                        imageView.setPreserveRatio(true);
                        this.questionContent.getChildren().add(imageView);
                        choiceHeight += 14.0;
                        AnchorPane.setTopAnchor(imageView, choiceHeight);
                        AnchorPane.setLeftAnchor(imageView, 30.0);
                        choiceHeight += 300;
                    }
                }
                ResultSet rs1 = db.getData("Select * from Choice where questionID = '" + questionID + "'");

                while (rs1.next()){
                    if(Float.parseFloat(rs1.getString("choiceGrade")) == 0){
                        listChoice.add(new Choice(rs1.getString("choiceText"), 0, rs1.getString("choiceID"), rs1.getString("questionID"), false, rs1.getString("choiceMedia")));
                    }else{
                        notNullChoice ++;
                        listChoice.add(new Choice(rs1.getString("choiceText"), Double.valueOf(rs1.getString("choiceGrade")), rs1.getString("choiceID"), rs1.getString("questionID"), false, rs1.getString("choiceMedia")));
                    }
                }
                if(isShuffle){
                    Collections.shuffle(listChoice);
                }
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
                        if(listChoice.get(i).getChoiceMedia() != null){
                            if(listChoice.get(i).getChoiceMedia().substring(listChoice.get(i).getChoiceMedia().length() - 3, listChoice.get(i).getChoiceMedia().length()).equals("png")){
                                Image image = new Image("file:" + listChoice.get(i).getChoiceMedia());
                                ImageView imageView = new ImageView();
                                imageView.setImage(image);
                                imageView.setFitWidth(450); // Thiết lập chiều rộng mới là 200
                                imageView.setFitHeight(300); // Thiết lập chiều cao mới là 150
                                imageView.setPreserveRatio(true); // Duy trì tỷ lệ khung hình ban đầu
                                this.questionContent.getChildren().add(imageView);
                                choiceHeight += 14.0;
                                AnchorPane.setTopAnchor(imageView, choiceHeight);
                                AnchorPane.setLeftAnchor(imageView, 30.0);
                                choiceHeight += 300;
                            }
                        }
                    }
//                    AnchorPane.setBottomAnchor(listRadioButton.get(listRadioButton.size()-1), 10.0);
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
                        if(listChoice.get(i).getChoiceMedia() != null){
                            if(listChoice.get(i).getChoiceMedia().substring(listChoice.get(i).getChoiceMedia().length() - 3, listChoice.get(i).getChoiceMedia().length()).equals("png")){
                                Image image = new Image("file:" + listChoice.get(i).getChoiceMedia());
                                ImageView imageView = new ImageView();
                                imageView.setImage(image);
                                imageView.setFitWidth(450); // Thiết lập chiều rộng mới là 200
                                imageView.setFitHeight(300); // Thiết lập chiều cao mới là 150
                                imageView.setPreserveRatio(true); // Duy trì tỷ lệ khung hình ban đầu
                                this.questionContent.getChildren().add(imageView);
                                choiceHeight += 14.0;
                                AnchorPane.setTopAnchor(imageView, choiceHeight);
                                AnchorPane.setLeftAnchor(imageView, 30.0);
                                choiceHeight += 300;
                            }
                        }
                    }
//                    AnchorPane.setBottomAnchor(listCheckBox.get(listCheckBox.size()-1), 10.0);
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
