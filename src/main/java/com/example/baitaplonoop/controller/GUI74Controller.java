package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.util.AnchorPaneFinish;
import com.example.baitaplonoop.util.BreadCrumb;
import com.example.baitaplonoop.util.ChangeScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.*;
import static com.example.baitaplonoop.controller.GUI72Controller.openTime;
import static com.example.baitaplonoop.controller.GUI73Controller.finishTime;

public class GUI74Controller implements Initializable {
    GridPane gridPane=new GridPane();
    @FXML
    private Label lbStart;
    @FXML
    private Label lbState;
    @FXML
    private Label lbCompleted;
    @FXML
    private Label lbTime;
    @FXML
    private ScrollPane questionView_cr;
    @FXML
    private Label lbMarks;
    @FXML
    private Label lbGrade;
    @FXML
    private AnchorPane apQuestion;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private FlowPane flowPane2;
    @FXML
    private Hyperlink hlFinish;
    ArrayList<Button> listButton = new ArrayList<>();
    void insertIntoGridPane() {
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
        double vValue = y / (contentHeight - viewportHeight);
        scrollPane.setVvalue(vValue);
        scrollPane.setHvalue(0.00);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        flowPane2.getChildren().addAll(breadCrumb);
        insertIntoGridPane();
        hlFinish.setOnMouseClicked(event -> {
            Hyperlink quiz_hf=new Hyperlink(" / "+quizChosen);
            quiz_hf.setOnMouseClicked(event1 -> {
                BreadCrumb.changeBreadCrumb(breadCrumb,level,quiz_hf);
                ChangeScene.GUI61PreviewQuiz(this, event1);
            });
            BreadCrumb.addBreadCrumb(breadCrumb,level,1,quiz_hf);
            ChangeScene.GUI61PreviewQuiz(this, event);
        });
    }
    public void setUpScene(ArrayList<AnchorPaneFinish> result, double marks, double yourmarks){
        lbStart.setText(openTime.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, h:mm a")));
        lbCompleted.setText(finishTime.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, h:mm a")));
        lbState.setText("Finished");
        Duration duration = Duration.between(openTime, finishTime);
        long hours = duration.toHours();
        long mins = duration.toMinutes() % 60;
        long secs = duration.toSeconds() % 60;
        String time = "";
        if(hours > 1){
            time += hours + " hours ";
        }else if(hours > 0){
            time += hours + " hour ";
        }
        if(mins > 1){
            time += mins + " mins ";
        }else if(mins > 0 || hours > 0){
            time += mins + " min ";
        }
        if(secs > 1){
            time += secs + " secs";
        }else{
            time += secs + " sec";
        }
        lbTime.setText(time);
        DecimalFormat df = new DecimalFormat("#.##");
        lbMarks.setText(df.format(yourmarks) + "/" + df.format(marks));
        double grade = yourmarks/marks;
        lbGrade.setText(df.format(grade*10.0) + " out of 10.00 (" + (int)Math.round(grade*100) + "%)");
        double questionHeight = 0;
        for(int i = 0; i < result.size(); i++){
            ///tao button tuong ung voi cau hoi
            Button button = new Button();
            button.setAlignment(Pos.TOP_CENTER);
            button.setFont(Font.font(15));
            button.setBorder( new Border(new BorderStroke(
                    Color.SILVER,                       // Màu viền
                    BorderStrokeStyle.SOLID,            // Kiểu viền
                    CornerRadii.EMPTY,                  // Độ bo góc
                    new BorderWidths(2)))              // Độ dày
            );
            button.setStyle("-fx-background-color: linear-gradient(to bottom, #FFFFFF 70%, #C0C0C0 30%)");
            if (i < 9) button.setText("0" + (i + 1));
            else button.setText(String.valueOf(i + 1));
            button.setId("question" + (i + 1));
            listButton.add(button);
            GridPane.setConstraints(button, i % 5, i / 5);
            GridPane.setMargin(button, new Insets(2, 1, 2, 1));
            GridPane.setHgrow(button, Priority.ALWAYS);
            GridPane.setVgrow(button, Priority.ALWAYS); // cho phép button mở rộng theo chiều dọc
            gridPane.getChildren().add(button);
            int finalI = i;
            button.setOnAction(event -> scrollToNode(questionView_cr,result.get(finalI)));
            ///////////////////////////////////Hung
            apQuestion.getChildren().add(result.get(i));
            AnchorPane.setTopAnchor(result.get(i), questionHeight);
            AnchorPane.setLeftAnchor(result.get(i), 0.0);
            AnchorPane.setRightAnchor(result.get(i), 0.0);
            questionHeight += result.get(i).getPrefHeight();
        }
    }
}
