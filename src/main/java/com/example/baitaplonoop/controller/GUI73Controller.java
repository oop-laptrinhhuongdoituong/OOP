package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.AnchorPaneGUI7;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.quizChosen;
public class GUI73Controller implements Initializable {
    public GridPane questionOverview_gp;
    public AnchorPane QuestionOverview_ap;
    @FXML
    private AnchorPane apQuestion;
    DBConnect db = new DBConnect();
    ArrayList<AnchorPaneGUI7> listAnchorPane = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = db.getData("Select questionID from QuestionInQuiz where quizName = N'" + quizChosen + "'");
        int i = 0;
        try{
            while(rs.next()){
                AnchorPaneGUI7 question = new AnchorPaneGUI7(i+1, rs.getString("questionID"));
                listAnchorPane.add(question);
                apQuestion.getChildren().add(question);
                AnchorPane.setRightAnchor(question, 0.0);
                AnchorPane.setLeftAnchor(question, 0.0);
                AnchorPane.setTopAnchor(question, 250.0*i);
                i++;
                //listAnchorPane.get
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //addButtonsAndGridPane(i,QuestionOverview_ap);
        addButtonsAndGridPane(i,QuestionOverview_ap,this);
        //setQuestionButtonAction(i,apQuestion);
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
