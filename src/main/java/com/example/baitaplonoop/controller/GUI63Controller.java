package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUI63Controller implements Initializable {
    public Button addSelectedQuestionToQuiz_btn;
    @FXML
    private AnchorPane gui6_3;
    @FXML
    private TableView<QuestionCheckBoxInTable> table;
    @FXML
    private TableColumn<QuestionCheckBoxInTable, String> question;
    @FXML
    private TableColumn<QuestionCheckBoxInTable, CheckBox> action;
    private final ObservableList<QuestionCheckBoxInTable> questionsList =FXCollections.observableArrayList();
    private final ObservableList<Pair<String,String>> choiceQuestion=FXCollections.observableArrayList();
    @FXML
    private Label Default;
    @FXML
    private TreeView<String> category;
    @FXML
    private CheckBox gui6_3CheckBox;
    //create click event for treeView
    private void showQuestionInCategory() {
        category.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                questionsList.clear();
                table.setItems(questionsList);
                TreeItem<String> item = category.getSelectionModel().getSelectedItem();
                if (gui6_3CheckBox.isSelected())
                    insertQuestionInto.insertQuestionIntoTableViewWithSubcategoryInNewQuiz(item, questionsList);
                else insertQuestionInto.insertQuestionIntoTableViewWithoutSubcategoryInNewQuiz(item, questionsList);
                category.setVisible(false);
                Default.setText(FindCategoryInfo.findCategoryName(item.getValue()));
                question.setCellValueFactory(new PropertyValueFactory<>("questionText"));
                action.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
                table.setItems(questionsList);
                table.setVisible(true);
            }
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        category.setVisible(false);
        table.setVisible(false);
        Default.setOnMouseClicked(mouseEvent -> {
            Default.setText("Default");

            TreeItem<String> root = new TreeItem<>("For IT");
            try {
                insertCategoryIntoTreeView.insertCategory("select * from dbo.Category where parentID is NULL", root);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            category.setRoot(root);
            category.setVisible(true);
        });
        gui6_3CheckBox.setSelected(false);
        showQuestionInCategory();
        gui6_3CheckBox.setOnAction(event -> showQuestionInCategory());
        gui6_3.setOnMouseClicked(mouseEvent -> {
            if (category.isVisible() && IsMouseOnLabel.isMouseOnLabel(Default, mouseEvent))
                category.setVisible(false);
        });
        addSelectedQuestionToQuiz_btn.setOnMouseClicked(addSelectedQuestionToQuizEvent -> System.out.println(insertQuestionInto.insertQuesionToQuiz()));
        addSelectedQuestionToQuiz_btn.setOnAction(event -> {
            for(QuestionCheckBoxInTable a: questionsList){
                if(a.getCheckBox().isSelected()){
                    choiceQuestion.add(new Pair<>(a.getQuestionID(),a.getQuestionText()));
                }
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI62.fxml"));
            try {
                Parent gui62 = loader.load();
                Scene scene = new Scene(gui62);
                GUI62Controller gui62Controller=loader.getController();
                gui62Controller.setChosenQuestions(choiceQuestion);
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}