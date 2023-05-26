package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.AnchorPaneGUI7;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.quizChosen;

public class GUI73Controller implements Initializable {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label quizName_lb;
    @FXML
    private Label timerLabel;
    private String quizName;
    public AnchorPane QuestionOverview_ap;
    @FXML
    private AnchorPane apQuestion;
    @FXML
    private AnchorPane anchorPane1;
    private GridPane gridPane = new GridPane();
    @FXML
    private  ScrollPane questionView_cr;
    DBConnect db = new DBConnect();
    private Timeline timeline;
    private int seconds = 0;
    @FXML
    Hyperlink hlFinish;
    ArrayList<AnchorPaneGUI7> listQuestion = new ArrayList<>();
    ArrayList<Button> listButton = new ArrayList<>();

    private void updateTimerLabel() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, secs);
        timerLabel.setText("Time left " + formattedTime);
    }

    private void stopTimer() {
        timeline.stop();
    }

    private void timerAction(int a) {
        seconds = a * 60;
        String hours;
        if (a / 60 < 10) hours = "0" + Integer.toString(a / 60);
        else hours = Integer.toString(a / 60);
        String minutes;
        if (a % 60 < 10) minutes = "0" + Integer.toString(a % 60);
        else minutes = Integer.toString(a % 60);
        timerLabel.setText("Time left " + hours + ":" + minutes + ":00");
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    seconds--;
                    updateTimerLabel();
                    if (seconds == 0) {
                        stopTimer();
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    void insertNavigation(int numberButton) {
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0;");
        GridPane gridPane = new GridPane();
        gridPane.setVgap(1);
        gridPane.setHgap(1);
        AnchorPane.setTopAnchor(gridPane, 0.0); // cách top 98 pixel
        AnchorPane.setLeftAnchor(gridPane, 0.0); // cách left 50 pixel
        AnchorPane.setRightAnchor(gridPane, 0.00); // cách right 50 pixel
        for (int i = 0; i < numberButton; i++) {
            Button button = new Button();
            button.setAlignment(Pos.TOP_CENTER);
            button.setFont(Font.font(15));
            button.setStyle("-fx-background-color: linear-gradient(to bottom, #FFFFFF 70%, #C0C0C0 30%)");
            if (0 <= i && i < 9) button.setText("0" + String.valueOf(i + 1));
            else button.setText(String.valueOf(i + 1));
            button.setId("question" + String.valueOf(i + 1));
            GridPane.setConstraints(button, i % 5, i / 5);
            GridPane.setMargin(button, new Insets(2, 1, 2, 1));
            GridPane.setHgrow(button, Priority.ALWAYS);
            GridPane.setVgrow(button, Priority.ALWAYS); // cho phép button mở rộng theo chiều dọc
            gridPane.getChildren().add(button);
        }
        // Thêm gridpane vào anchorpane
        anchorPane1.getChildren().add(gridPane);


    }
    void insertIntoGridPane(){
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-padding: 0;");

        gridPane.setVgap(1);
        gridPane.setHgap(1);
        AnchorPane.setTopAnchor(gridPane, 0.0); // cách top 98 pixel
        AnchorPane.setLeftAnchor(gridPane, 0.0); // cách left 50 pixel
        AnchorPane.setRightAnchor(gridPane, 0.00); // cách right 50 pixel
        anchorPane1.getChildren().add(gridPane);

    }
    private void scrollToNode(ScrollPane scrollPane, AnchorPane node) {
        Bounds bounds = node.getBoundsInParent();
        double y = bounds.getMinY();
        double contentHeight = scrollPane.getContent().getBoundsInLocal().getHeight();
        double viewportHeight = scrollPane.getViewportBounds().getHeight();
        double vValue = (y - 0.5 * viewportHeight) / (contentHeight - viewportHeight);
        scrollPane.setVvalue(vValue);
        scrollPane.setHvalue(0.00);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insertIntoGridPane();
        ResultSet rs = db.getData("Select questionID from QuestionInQuiz where quizName = N'" + quizChosen + "'");
        int i = 0;
        double questionHeight = 0.0;
        try{
            while(rs.next()){
                AnchorPaneGUI7 question = new AnchorPaneGUI7(i+1, rs.getString("questionID"));
                listQuestion.add(question);
        try {
            while (rs.next()) {
                AnchorPaneGUI7 question = new AnchorPaneGUI7(i + 1, rs.getString("questionID"));
                ///tao button tuong ung voi cau hoi
                    Button button = new Button();
                    button.setAlignment(Pos.TOP_CENTER);
                    button.setFont(Font.font(15));
                    button.setStyle("-fx-background-color: linear-gradient(to bottom, #FFFFFF 70%, #C0C0C0 30%)");
                    if (0 <= i && i < 9) button.setText("0" + String.valueOf(i + 1));
                    else button.setText(String.valueOf(i + 1));
                    button.setId("question" + String.valueOf(i + 1));
                    GridPane.setConstraints(button, i % 5, i / 5);
                    GridPane.setMargin(button, new Insets(2, 1, 2, 1));
                    GridPane.setHgrow(button, Priority.ALWAYS);
                    GridPane.setVgrow(button, Priority.ALWAYS); // cho phép button mở rộng theo chiều dọc
                    gridPane.getChildren().add(button);
                ///////////////////////////////////Hung
                apQuestion.getChildren().add(question);

                AnchorPane.setRightAnchor(question, 0.0);
                AnchorPane.setLeftAnchor(question, 0.0);
                AnchorPane.setTopAnchor(question, questionHeight);
                button.setOnAction(event -> {
                    scrollToNode(questionView_cr,question);
                });
                questionHeight += 10.0;
                questionHeight += question.getPrefHeight();

                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        quizName = quizChosen;
        quizName_lb.setText(quizName + " / Edit quiz");
        LocalDateTime localDateTime = LocalDateTime.now();
        ResultSet rs1 = db.getData("select * from dbo.Quiz where quizName = N'" + quizName + "'");
        LocalDateTime openTime;
        LocalDateTime closeTime;

        try {
            if (rs1.next()) {
                openTime = rs1.getTimestamp("openTime").toLocalDateTime();
                closeTime = rs1.getTimestamp("closeTime").toLocalDateTime();
                if (localDateTime.isAfter(openTime) && localDateTime.isBefore(closeTime)) {
                    timerAction(rs1.getInt("timeLimit"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //addButtonsAndGridPane(i,QuestionOverview_ap);
        addButtonsAndGridPane(i,QuestionOverview_ap,this);
        //setQuestionButtonAction(i,apQuestion);
        hlFinish.setOnMouseClicked(mouseEvent -> {
            double mark = 0;
            for(int j = 0; j < listQuestion.size(); j++){
                for(int h = 0; h < listQuestion.get(j).listChoice.size(); h++){
                    if(listQuestion.get(j).listRadioButton.get(h).isSelected()){
                        mark += listQuestion.get(j).question.getQuestionMark() * listQuestion.get(j).listChoice.get(h).getChoiceGrade();
                    }
                }
            }
            System.out.println(mark);
        });
    }
    // Hàm để thêm button vào gridpane
    // Hàm để thêm button vào gridpane và thêm gridpane vào anchorpane
    public void addButtonsAndGridPane(int numberButton, AnchorPane anchorPane, GUI73Controller gui73Controller) {
        // Tạo một gridpane mới
        GridPane gridPane = new GridPane();
        // Đặt vị trí và kích thước của gridpane trong anchorpane
        // Bạn có thể thay đổi các giá trị này tùy theo ý muốn
        AnchorPane.setTopAnchor(gridPane, 98.0); // cách top 98 pixel
        AnchorPane.setLeftAnchor(gridPane, 50.0); // cách left 50 pixel
        AnchorPane.setRightAnchor(gridPane, 50.0); // cách right 50 pixel
        AnchorPane.setBottomAnchor(gridPane, 50.0); // cách bottom 50 pixel
        // Duyệt qua số lượng button cần thêm
        for (int i = 0; i < numberButton; i++) {
            // Tạo một button mới với text là số thứ tự của nó
            Button button = new Button(String.valueOf(i + 1));
            button.setId("question" + String.valueOf(i+1) );
            // Đặt vị trí và kích thước của button trong gridpane
            // Bạn có thể thay đổi các giá trị này tùy theo ý muốn
            GridPane.setConstraints(button, i % 5, i / 5); // column = i % 5, row = i / 5
            //GridPane.setMargin(button, new Insets(2)); // khoảng cách giữa các button
            GridPane.setMargin(button,new Insets(2, 2, 2, 2));
            GridPane.setHgrow(button, Priority.ALWAYS); // cho phép button mở rộng theo chiều ngang
            //GridPane.setVgrow(button, Priority.ALWAYS); // cho phép button mở rộng theo chiều dọc

            // Thêm button vào gridpane
            gridPane.getChildren().add(button);
        }
        // Thêm gridpane vào anchorpane
        anchorPane.getChildren().add(gridPane);

    }
    // Hàm để thêm button vào gridpane và thêm gridpane vào anchorpane
    public void addButtonsAndGridPane(int numberButton, AnchorPane anchorPane, Scene scene) {
        // Tạo một gridpane mới
        GridPane gridPane = new GridPane();
        // Đặt vị trí và kích thước của gridpane trong anchorpane
        // Bạn có thể thay đổi các giá trị này tùy theo ý muốn
        AnchorPane.setTopAnchor(gridPane, 98.0); // cách top 98 pixel
        AnchorPane.setLeftAnchor(gridPane, 50.0); // cách left 50 pixel
        AnchorPane.setRightAnchor(gridPane, 50.0); // cách right 50 pixel
        AnchorPane.setBottomAnchor(gridPane, 50.0); // cách bottom 50 pixel
        // Tạo một mảng chứa các anchorpane với số thứ tự tương ứng
        AnchorPane[] anchorPanes = new AnchorPane[numberButton];
        for (int i = 0; i < numberButton; i++) {
            // Tạo một anchorpane mới với text là số thứ tự của nó
            AnchorPane ap = new AnchorPane();
            Label label = new Label("AnchorPane " + (i + 1));
            label.setFont(new Font(20));
            label.setTextFill(Color.WHITE);
            ap.getChildren().add(label);
            ap.setStyle("-fx-background-color: #" + Integer.toHexString((int)(Math.random() * 16777215))); // màu nền ngẫu nhiên
            AnchorPane.setTopAnchor(label, 100.0); // cách top 100 pixel
            AnchorPane.setLeftAnchor(label, 100.0); // cách left 100 pixel
            // Thêm anchorpane vào mảng
            anchorPanes[i] = ap;
        }
        // Duyệt qua số lượng button cần thêm
        for (int i = 0; i < numberButton; i++) {
            // Tạo một button mới với text là số thứ tự của nó
            Button button = new Button(String.valueOf(i + 1));
            // Đặt vị trí và kích thước của button trong gridpane
            // Bạn có thể thay đổi các giá trị này tùy theo ý muốn
            GridPane.setConstraints(button, i % 5, i / 5); // column = i % 5, row = i / 5
            GridPane.setMargin(button, new Insets(10)); // khoảng cách giữa các button
            GridPane.setHgrow(button, Priority.ALWAYS); // cho phép button mở rộng theo chiều ngang
            GridPane.setVgrow(button, Priority.ALWAYS); // cho phép button mở rộng theo chiều dọc
            // Set sự kiện cho button khi được nhấn
            int index = i; // biến để lưu số thứ tự của button
            button.setOnAction(event -> {
                // Chuyển đến anchorpane với số thứ tự tương ứng trong mảng
                scene.getRoot().getChildrenUnmodifiable().setAll(anchorPanes[index]);
            });
            // Thêm button vào gridpane
            gridPane.getChildren().add(button);
        }
        // Thêm gridpane vào anchorpane
        anchorPane.getChildren().add(gridPane);
    }
    // Hàm để set sự kiện cho button có id là "question" + i
    public void setQuestionButtonAction(int i, AnchorPane anchorPane) {
        // Tìm button có id là "question" + i trong anchorpane
        Button button = (Button) anchorPane.lookup("question" + String.valueOf(i+1));
        // Kiểm tra nếu button tồn tại
        if (button != null) {
            // Set sự kiện cho button khi được nhấn
            button.setOnAction(event -> {
                // Tìm câu hỏi thứ i trong anchorpane
                Label question = (Label) anchorPane.lookup("question" + String.valueOf(i+1));
                // Kiểm tra nếu câu hỏi tồn tại
                if (question != null) {
                    // Đưa màn hình đến vị trí của câu hỏi
                    question.requestFocus();
                }
            });
        }
    }
}