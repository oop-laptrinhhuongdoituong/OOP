package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.ChangeScene;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.breadCrumb;
import static com.example.baitaplonoop.controller.GUI11Controller.level;

public class GUI35Controller implements Initializable {
    public TextField quizName_tf;
    public TextArea quizDescription_tf;
    public DatePicker openQuiz_dp;
    public DatePicker closeQuiz_dp;
    public TextField timeQuiz_tf;
    public ComboBox<String> expires_cb;
    public Button createQuiz_btn;
    public Button cancel_btn;
    public Spinner<Integer> minuteStart_sp;
    public Spinner<Integer> hourStart_sp;
    public Spinner<Integer> hourEnd_sp;
    public Spinner<Integer> minuteEnd_sp;

    DBConnect db = new DBConnect();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60);
        hourStart_sp.setValueFactory(valueFactory);
        TextFormatter<Integer> textFormatter = new TextFormatter<>(valueFactory.getConverter(), valueFactory.getValue(), change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty() || (newText.matches("\\d+") && Integer.parseInt(newText) >= 0 && Integer.parseInt(newText) <= 60)) {
                return change;
            }
            return null;
        });

        // Gán textFormatter cho spinner
        hourStart_sp.getEditor().setTextFormatter(textFormatter);

        createQuiz_btn.setOnAction(createQuizEvent -> {

            Alert alertCreateQuiz = new Alert(Alert.AlertType.INFORMATION);
            alertCreateQuiz.setTitle("Add Category Information");
            ButtonType btnContinue = new ButtonType("Oke", ButtonBar.ButtonData.YES);
            ButtonType btnBack = new ButtonType("Home page", ButtonBar.ButtonData.NO);
            alertCreateQuiz.getButtonTypes().setAll(btnContinue, btnBack);

            if (quizName_tf.getText() == null || quizDescription_tf.getText() == null || openQuiz_dp.getValue() == null || closeQuiz_dp.getValue() == null || timeQuiz_tf.getText() == null) {
                alertCreateQuiz.setContentText("Please fill the blank field");
                alertCreateQuiz.setHeaderText("You must fill all field!");
                alertCreateQuiz.showAndWait();
            } else {
                alertCreateQuiz.setContentText("Create Quiz Successful!");
                alertCreateQuiz.setHeaderText("Done");
                alertCreateQuiz.showAndWait();
                String[] quiz = {quizName_tf.getText(), quizDescription_tf.getText(), String.valueOf(openQuiz_dp.getValue()), String.valueOf(closeQuiz_dp.getValue()), timeQuiz_tf.getText()};
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
