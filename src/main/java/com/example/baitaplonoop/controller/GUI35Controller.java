package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.AlertOOP;
import com.example.baitaplonoop.util.ChangeScene;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import jfxtras.scene.control.LocalDateTimeTextField;
import org.apache.commons.compress.archivers.zip.ScatterZipOutputStream;

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
    public CheckBox enableTime_cb;
    public LocalDateTimeTextField openQuiz_dp;
    public LocalDateTimeTextField closeQuiz_dp;
    DBConnect db = new DBConnect();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enableTime_cb.setSelected(true);
        createQuiz_btn.setOnMouseClicked(createQuizEvent -> {
            String openQuiz = null, endQuiz = null, timeLimitQuiz = null;
            if (openQuiz_dp.getLocalDateTime() != null)
                openQuiz = openQuiz_dp.getLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            if (closeQuiz_dp.getLocalDateTime() != null)
                endQuiz = closeQuiz_dp.getLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            //if(enableTime_cb.isSelected())
            if (enableTime_cb.isSelected()) timeLimitQuiz = timeQuiz_tf.getText();
            String quizInfo[] = {quizName_tf.getText(), quizDescription_tf.getText(), openQuiz, endQuiz, timeQuiz_tf.getText()};
            db.AddNewQuiz(quizInfo);
            AlertOOP.AddDone("Add quiz status", "Add quiz done", "Done!");
            ChangeScene.mainSceneGUI11(this, createQuizEvent);
        });
        cancel_btn.setOnMouseClicked(cancelQuizEvent -> {
            breadCrumb.remove(1, breadCrumb.size());
            level.remove(1, breadCrumb.size());
            ChangeScene.changeSceneUsingMouseEvent(this, "/com/example/baitaplonoop/GUI11.fxml", cancelQuizEvent);
        });
        enableTime_cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (enableTime_cb.isSelected()) {
                    timeQuiz_tf.setDisable(false);
                } else {
                    timeQuiz_tf.setDisable(true);
                    timeQuiz_tf.clear();
                }
            }
        });
    }

}
