package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.util.Pair;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.breadCrumb;
import static com.example.baitaplonoop.controller.GUI11Controller.level;

public class GUI63Controller implements Initializable {
    @FXML
    public Button addSelectedQuestionToQuiz_btn;
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
            if(category.isVisible()){
                category.setVisible(false);
            }
            else {
                Default.setText("Default");
                category.setVisible(true);
                TreeItem<String> root = new TreeItem<>("For IT");
                try {
                    insertCategoryIntoTreeView.insertCategory("select * from dbo.Category where parentID is NULL", root);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                category.setRoot(root);
                category.setVisible(true);
            }
        });
        gui6_3CheckBox.setSelected(false);
        showQuestionInCategory();
        gui6_3CheckBox.setOnAction(event -> showQuestionInCategory());
        addSelectedQuestionToQuiz_btn.setOnAction(event -> {
            for(QuestionCheckBoxInTable a: questionsList){
                if(a.getCheckBox().isSelected()){
                    choiceQuestion.add(new Pair<>(a.getQuestionID(),a.getQuestionText()));
                }
            }
            breadCrumb.remove(breadCrumb.size()-1);
            level.remove(level.size()-1);
            ChangeScene.GUI62(this,event,choiceQuestion);
        });
    }
}