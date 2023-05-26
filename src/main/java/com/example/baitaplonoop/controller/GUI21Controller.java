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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GUI21Controller implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        category.setVisible(false);
        table.setVisible(false);
        Default.setOnMouseClicked(mouseEvent -> {
            Default.setText("Default");
            table.setVisible(false);
            questionsList.clear();
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
        category.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                questionsList.clear();
                table.setItems(questionsList);
                TreeItem<String> item = category.getSelectionModel().getSelectedItem();
                if (gui2_1CheckBox.isSelected())
                    insertQuestionInto.insertQuestionIntoTableViewWithSubcategory(item, questionsList);
                else insertQuestionInto.insertQuestionIntoTableViewWithoutSubcategory(item, questionsList);
                category.setVisible(false);
                Default.setText(FindCategoryInfo.findCategoryName(item.getValue()));
                question.setCellValueFactory(new PropertyValueFactory<addQuestion, String>("questionText"));
                action.setCellValueFactory(new PropertyValueFactory<addQuestion, Button>("button"));
                for (int i = 0; i < questionsList.size(); i++) {
                    int k = i;
                    questionsList.get(i).getButton().setOnAction(event1 -> {
                        Stage stage = (Stage) ((Node) event1.getSource()).getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI32AddQuestion.fxml"));
                        try {
                            Parent gui32 = loader.load();
                            Scene scene = new Scene(gui32);
                            GUI32Controller gui32Controller = loader.getController();
                            String[] editQuestionText = db.FindQuestionInfo(questionsList.get(k).getQuestionID());
                            String[] choiceInfo1 = db.FindChoiceInfo(questionsList.get(k).getQuestionID() + "1");
                            if (choiceInfo1 == null) choiceInfo1 = new String[]{" ", ""};
                            String[] choiceInfo2 = db.FindChoiceInfo(questionsList.get(k).getQuestionID() + "2");
                            if (choiceInfo2 == null) choiceInfo2 = new String[]{" ", "", ""};
                            String[] choiceInfo3 = db.FindChoiceInfo(questionsList.get(k).getQuestionID() + "3");
                            if (choiceInfo3 == null) choiceInfo3 = new String[]{" ", "", ""};
                            String[] choiceInfo4 = db.FindChoiceInfo(questionsList.get(k).getQuestionID() + "4");
                            if (choiceInfo4 == null) choiceInfo4 = new String[]{" ", "", ""};
                            String[] choiceInfo5 = db.FindChoiceInfo(questionsList.get(k).getQuestionID() + "5");
                            if (choiceInfo5 == null) choiceInfo5 = new String[]{" ", "", ""};
                            String[] choiceInfo6 = db.FindChoiceInfo(questionsList.get(k).getQuestionID() + "6");
                            if (choiceInfo6 == null) choiceInfo6 = new String[]{" ", "", ""};
                            gui32Controller.editingQuestionChoice(Default.getText(), questionsList.get(k).getQuestionID(), editQuestionText[0],editQuestionText[1], choiceInfo1[0], choiceInfo1[1], choiceInfo1[2], choiceInfo2[0], choiceInfo2[1], choiceInfo2[2], choiceInfo3[0], choiceInfo3[1], choiceInfo3[2], choiceInfo4[0], choiceInfo4[1], choiceInfo4[2], choiceInfo5[0], choiceInfo5[1], choiceInfo5[2], choiceInfo6[0], choiceInfo6[1], choiceInfo6[2]);
                            stage.setScene(scene);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                table.setItems(questionsList);
                table.setVisible(true);
            }
        });
        gui2_1CheckBox.setOnAction(event -> {
            category.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    questionsList.clear();
                    table.setItems(questionsList);
                    TreeItem<String> item = category.getSelectionModel().getSelectedItem();
                    if (gui2_1CheckBox.isSelected())
                        insertQuestionInto.insertQuestionIntoTableViewWithSubcategory(item, questionsList);
                    else insertQuestionInto.insertQuestionIntoTableViewWithoutSubcategory(item, questionsList);
                    category.setVisible(false);
                    Default.setText(FindCategoryInfo.findCategoryName(item.getValue()));
                    question.setCellValueFactory(new PropertyValueFactory<addQuestion, String>("questionText"));
                    action.setCellValueFactory(new PropertyValueFactory<addQuestion, Button>("button"));
                    for (int i = 0; i < questionsList.size(); i++) {
                        int k = i;
                        questionsList.get(i).getButton().setOnAction(event1 -> {
                            Stage stage = (Stage) ((Node) event1.getSource()).getScene().getWindow();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI32AddQuestion.fxml"));
                            try {
                                Parent gui32 = loader.load();
                                Scene scene = new Scene(gui32);
                                GUI32Controller gui32Controller = loader.getController();
                                String[] editQuestionText = db.FindQuestionInfo(questionsList.get(k).getQuestionID());
                                String[] choiceInfo1 = db.FindChoiceInfo(questionsList.get(k).getQuestionID() + "1");
                                if (choiceInfo1 == null) choiceInfo1 = new String[]{" ", "", ""};
                                String[] choiceInfo2 = db.FindChoiceInfo(questionsList.get(k).getQuestionID() + "2");
                                if (choiceInfo2 == null) choiceInfo2 = new String[]{" ", "", ""};
                                String[] choiceInfo3 = db.FindChoiceInfo(questionsList.get(k).getQuestionID() + "3");
                                if (choiceInfo3 == null) choiceInfo3 = new String[]{" ", "", ""};
                                String[] choiceInfo4 = db.FindChoiceInfo(questionsList.get(k).getQuestionID() + "4");
                                if (choiceInfo4 == null) choiceInfo4 = new String[]{" ", "", ""};
                                String[] choiceInfo5 = db.FindChoiceInfo(questionsList.get(k).getQuestionID() + "5");
                                if (choiceInfo5 == null) choiceInfo5 = new String[]{" ", "", ""};
                                String[] choiceInfo6 = db.FindChoiceInfo(questionsList.get(k).getQuestionID() + "6");
                                if (choiceInfo6 == null) choiceInfo6 = new String[]{" ", "", ""};
                                gui32Controller.editingQuestionChoice(Default.getText(), questionsList.get(k).getQuestionID(), editQuestionText[0],editQuestionText[1], choiceInfo1[0], choiceInfo1[1], choiceInfo1[2], choiceInfo2[0], choiceInfo2[1], choiceInfo2[2], choiceInfo3[0], choiceInfo3[1], choiceInfo3[2], choiceInfo4[0], choiceInfo4[1], choiceInfo4[2], choiceInfo5[0], choiceInfo5[1], choiceInfo5[2], choiceInfo6[0], choiceInfo6[1], choiceInfo6[2]);
                                stage.setScene(scene);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                    table.setItems(questionsList);
                    table.setVisible(true);
                }
            });
        });
        gui2_1.setOnMouseClicked(mouseEvent -> {
            if (category.isVisible() && IsMouseOnLabel.isMouseOnLabel(Default, mouseEvent) == false)
                category.setVisible(false);
        });
        categoriesButton.setOnAction(e -> {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI33AddCategory.fxml"));
            try {
                Parent GUI33AddCategory = loader.load();
                Scene scene = new Scene(GUI33AddCategory);
                stage.setScene(scene);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        importButton.setOnAction(e -> {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI34.fxml"));
            try {
                Parent GUI33AddCategory = loader.load();
                Scene scene = new Scene(GUI33AddCategory);
                stage.setScene(scene);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        createQuestionButton.setOnAction(e -> {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI32AddQuestion.fxml"));
            try {
                Parent GUI33AddCategory = loader.load();
                Scene scene = new Scene(GUI33AddCategory);
                stage.setScene(scene);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

    }

}
