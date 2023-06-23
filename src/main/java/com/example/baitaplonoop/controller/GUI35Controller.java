package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.AlertOOP;
import com.example.baitaplonoop.util.ChangeScene;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import jfxtras.scene.control.LocalDateTimeTextField;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.breadCrumb;
import static com.example.baitaplonoop.controller.GUI11Controller.level;

public class GUI35Controller implements Initializable {
    public TextField quizName_tf;
    public TextArea quizDescription_tf;
    public TextField timeQuiz_tf;
    public ComboBox<String> expires_cb;
    public Button createQuiz_btn;
    public Button cancel_btn;
    public CheckBox enableStart_cb;
    public CheckBox enableEnd_cb;
    public CheckBox enableTime_cb;
    public LocalDateTimeTextField openQuiz_dp;
    public LocalDateTimeTextField closeQuiz_dp;
    DBConnect db = new DBConnect();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createQuiz_btn.setOnAction(createQuizEvent -> {
            String openQuiz = null, endQuiz = null, timeLimitQuiz = null;
            if(!enableStart_cb.isSelected()) openQuiz = openQuiz_dp.getLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            else openQuiz_dp.setEditable(false);
            if(!enableEnd_cb.isSelected())  endQuiz = closeQuiz_dp.getLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            if(!enableTime_cb.isSelected()) timeLimitQuiz = timeQuiz_tf.getText();
            if (quizName_tf.getText() == null || quizDescription_tf.getText() == null || openQuiz_dp.getText() == null || closeQuiz_dp.getText() == null || timeQuiz_tf.getText() == null) {
                AlertOOP.mustFill("Add Quiz Status","You must fill all the blank field","");
            } else {
                String[] quiz = {quizName_tf.getText(), quizDescription_tf.getText(), openQuiz, endQuiz, timeQuiz_tf.getText()};
                db.AddNewQuiz(quiz);
            }
        });
        cancel_btn.setOnMouseClicked(cancelQuizEvent -> {
            breadCrumb.remove(1,breadCrumb.size());
            level.remove(1,breadCrumb.size());
            ChangeScene.changeSceneUsingMouseEvent(this,"/com/example/baitaplonoop/GUI11.fxml",cancelQuizEvent);
        });
    }
}
