package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.BreadCrumb;
import com.example.baitaplonoop.util.ChangeScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.*;

public class GUI61Controller implements Initializable {
    @FXML
    public Label lbQuiz;
    public Button imgAddQuestionToQuiz;
    @FXML
    Button btnPreviewQuiz;
    @FXML
    public Label lbTimeLimit;

    public TableView<Pair<String, String>> tbHistory;
    public TableColumn<Pair<String, String>, String> AttemptColumn;
    public TableColumn<Pair<String, String>, String> StatusColumn;
    ObservableList<Pair<String, String>> data = FXCollections.observableArrayList();
    public static ActionEvent StartEvent;

    DBConnect db = new DBConnect();
    public static String timeLimit = "";
    public static boolean isShuffle;
    public static boolean isOpenable;

    void setUpScene(){
        lbQuiz.setText(quizChosen);
        ResultSet rs = db.getData("Select * from Quiz where quizName = N'" + quizChosen + "'");
        ResultSet rs1 = db.getData("Select * from HistoryAttempt where quizName = N'" + quizChosen + "'");
        try {
            while (rs.next()) {
                if(Double.parseDouble(rs.getString("timeLimit")) != 0){
                    timeLimit = rs.getString("timeLimit");
                    lbTimeLimit.setText(timeLimit + " mins");
                    isShuffle = rs.getBoolean("shuffle");
                    isOpenable = !rs.getTimestamp("openTime").toLocalDateTime().isAfter(LocalDateTime.now()) && !rs.getTimestamp("closeTime").toLocalDateTime().isBefore(LocalDateTime.now());
                }else{
                    lbTimeLimit.setText("Unlimited");
                    isShuffle = rs.getBoolean("shuffle");
                    isOpenable = !rs.getTimestamp("openTime").toLocalDateTime().isAfter(LocalDateTime.now()) && !rs.getTimestamp("closeTime").toLocalDateTime().isBefore(LocalDateTime.now());
                }
            }
            while(rs1.next()){
                LocalDateTime timeAttempt = rs1.getTimestamp("dateAttempt").toLocalDateTime();
                double mark = rs1.getDouble("mark");
                data.add(new Pair<>(timeAttempt.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, h:mm a")), new DecimalFormat("#.##").format(mark)));
            }
            AttemptColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
            StatusColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
            AttemptColumn.setCellFactory(pairStringTableColumn -> new TableCell<>(){
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        setText(item);
                        setAlignment(javafx.geometry.Pos.CENTER);
                    } else {
                        setText(null);
                    }
                }
            });
            StatusColumn.setCellFactory(pairStringTableColumn -> new TableCell<>(){
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && !empty) {
                        setText(item);
                        setAlignment(javafx.geometry.Pos.CENTER);
                    } else {
                        setText(null);
                    }
                }
            });
            tbHistory.setItems(data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void setEvent(){
        btnPreviewQuiz.setOnAction(event -> {
            StartEvent = event;
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI72.fxml"));
            try {
                Parent parent = fxmlLoader.load();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        imgAddQuestionToQuiz.setOnMouseClicked(mouseEvent -> {
            Hyperlink addQuestionToQuiz_hl=new Hyperlink(" / "+"Edit quiz");
            addQuestionToQuiz_hl.setOnMouseClicked(event -> {
                BreadCrumb.changeBreadCrumb(breadCrumb,level,addQuestionToQuiz_hl);
                ChangeScene.GUI62ShowQuestionChosen(this, event);
            });
             BreadCrumb.addBreadCrumb(breadCrumb,level,2,addQuestionToQuiz_hl);
            ChangeScene.GUI62ShowQuestionChosen(this, mouseEvent);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpScene();
        setEvent();
    }
}