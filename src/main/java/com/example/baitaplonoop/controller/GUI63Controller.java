package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUI63Controller implements Initializable {
    @FXML
    private Button createQuestionButton;
    @FXML
    private ToolBar toolBar;

    @FXML
    private ToggleButton exportButton;
    @FXML
    private ToggleButton importButton;
    @FXML
    private ToggleButton questionsButton;
    @FXML
    private ToggleButton categoriesButton;
    @FXML
    private AnchorPane gui2_1;
    @FXML
    private TableView<addQuestion> table;

    @FXML
    private TableColumn<addQuestion, String> question;

    @FXML
    private TableColumn<addQuestion, Button> action;
    private ObservableList<addQuestion> questionsList = FXCollections.observableArrayList();
    ;
    @FXML
    private Label Default;
    @FXML
    private TreeView<String> category;
    @FXML
    private CheckBox gui2_1CheckBox;

    DBConnect db = new DBConnect();

    //create click event for treeView
    private void showQuestionInCategory() {
        category.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                questionsList.clear();
                table.setItems(questionsList);
                TreeItem<String> item = category.getSelectionModel().getSelectedItem();
                if (gui2_1CheckBox.isSelected())
                    insertQuestionInto.insertQuestionIntoTableViewWithSubcategory(item, questionsList);
                else insertQuestionInto.ínsertQuestionIntoTableViewWithoutSubcategory(item, questionsList);
                category.setVisible(false);
                Default.setText(FindCategoryInfo.findCategoryName(item.getValue()));
                question.setCellValueFactory(new PropertyValueFactory<addQuestion, String>("questionText"));
                action.setCellValueFactory(new PropertyValueFactory<addQuestion, Button>("button"));
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
        gui2_1CheckBox.setSelected(false);
        showQuestionInCategory();
        gui2_1CheckBox.setOnAction(event -> {
            showQuestionInCategory();
        });
        gui2_1.setOnMouseClicked(mouseEvent -> {
            if (category.isVisible() && IsMouseOnLabel.isMouseOnLabel(Default, mouseEvent) == false)
                category.setVisible(false);
        });
    }
}
