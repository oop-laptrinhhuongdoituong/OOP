package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.breadCrumb;
import static com.example.baitaplonoop.controller.GUI11Controller.level;

public class GUI21Controller implements Initializable {
    @FXML
    private Button createQuestionButton;
    @FXML
    private TableView<addQuestion> table;
    @FXML
    private TableColumn<addQuestion, String> question;
    @FXML
    private TableColumn<addQuestion, Button> action;
    private final ObservableList<addQuestion> questionsList = FXCollections.observableArrayList();
    @FXML
    private Label Default;
    @FXML
    private TreeView<String> category;
    @FXML
    private CheckBox gui2_1CheckBox;
    DBConnect db = new DBConnect();

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
            }

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
                question.setCellValueFactory(new PropertyValueFactory<>("questionText"));
                action.setCellValueFactory(new PropertyValueFactory<>("button"));
                for (int i = 0; i < questionsList.size(); i++) {
                    int k = i;
                    questionsList.get(i).getButton().setOnAction(event1 -> {
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
                        Hyperlink editQuestion_hl =new Hyperlink(" / "+"Editing a Multiple choice question");
                        String[] finalChoiceInfo = choiceInfo1;
                        String[] finalChoiceInfo1 = choiceInfo2;
                        String[] finalChoiceInfo2 = choiceInfo3;
                        String[] finalChoiceInfo3 = choiceInfo4;
                        String[] finalChoiceInfo4 = choiceInfo5;
                        String[] finalChoiceInfo5 = choiceInfo6;
                        editQuestion_hl.setOnAction(e -> {
                            BreadCrumb.changeBreadCrumb(breadCrumb,level,editQuestion_hl);
                            ChangeScene.GUI32AddQuestion(this, e, Default.getText(), questionsList.get(k).getQuestionID(), editQuestionText[0], editQuestionText[1], finalChoiceInfo[0], finalChoiceInfo[1], finalChoiceInfo[2], finalChoiceInfo1[0], finalChoiceInfo1[1], finalChoiceInfo1[2], finalChoiceInfo2[0], finalChoiceInfo2[1], finalChoiceInfo2[2], finalChoiceInfo3[0], finalChoiceInfo3[1], finalChoiceInfo3[2], finalChoiceInfo4[0], finalChoiceInfo4[1], finalChoiceInfo4[2], finalChoiceInfo5[0], finalChoiceInfo5[1], finalChoiceInfo5[2]);
                        });
                        BreadCrumb.addBreadCrumb(breadCrumb,level,2,editQuestion_hl);
                        ChangeScene.GUI32AddQuestion(this, event1, Default.getText(), questionsList.get(k).getQuestionID(), editQuestionText[0], editQuestionText[1], choiceInfo1[0], choiceInfo1[1], choiceInfo1[2], choiceInfo2[0], choiceInfo2[1], choiceInfo2[2], choiceInfo3[0], choiceInfo3[1], choiceInfo3[2], choiceInfo4[0], choiceInfo4[1], choiceInfo4[2], choiceInfo5[0], choiceInfo5[1], choiceInfo5[2], choiceInfo6[0], choiceInfo6[1], choiceInfo6[2]);
                    });
                }
                table.setItems(questionsList);
                table.setVisible(true);
            }
        });
        gui2_1CheckBox.setOnAction(event -> category.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                questionsList.clear();
                table.setItems(questionsList);
                TreeItem<String> item = category.getSelectionModel().getSelectedItem();
                if (gui2_1CheckBox.isSelected())
                    insertQuestionInto.insertQuestionIntoTableViewWithSubcategory(item, questionsList);
                else insertQuestionInto.insertQuestionIntoTableViewWithoutSubcategory(item, questionsList);
                category.setVisible(false);
                Default.setText(FindCategoryInfo.findCategoryName(item.getValue()));
                question.setCellValueFactory(new PropertyValueFactory<>("questionText"));
                action.setCellValueFactory(new PropertyValueFactory<>("button"));
                for (int i = 0; i < questionsList.size(); i++) {
                    int k = i;
                    questionsList.get(i).getButton().setOnAction(event1 -> {

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
                        Hyperlink editQuestion_hl =new Hyperlink(" / "+"Editing a Multiple choice question");
                        String[] finalChoiceInfo = choiceInfo6;
                        String[] finalChoiceInfo1 = choiceInfo5;
                        String[] finalChoiceInfo2 = choiceInfo4;
                        String[] finalChoiceInfo3 = choiceInfo3;
                        String[] finalChoiceInfo4 = choiceInfo2;
                        String[] finalChoiceInfo5 = choiceInfo1;
                        editQuestion_hl.setOnAction(e -> {
                            BreadCrumb.changeBreadCrumb(breadCrumb,level,editQuestion_hl);
                            ChangeScene.GUI32AddQuestion(this, e, Default.getText(), questionsList.get(k).getQuestionID(), editQuestionText[0], editQuestionText[1], finalChoiceInfo5[0], finalChoiceInfo5[1], finalChoiceInfo5[2], finalChoiceInfo4[0], finalChoiceInfo4[1], finalChoiceInfo4[2], finalChoiceInfo3[0], finalChoiceInfo3[1], finalChoiceInfo3[2], finalChoiceInfo2[0], finalChoiceInfo2[1], finalChoiceInfo2[2], finalChoiceInfo1[0], finalChoiceInfo1[1], finalChoiceInfo1[2], finalChoiceInfo[0], finalChoiceInfo[1], finalChoiceInfo[2]);
                        });
                        BreadCrumb.addBreadCrumb(breadCrumb,level,2,editQuestion_hl);
                        ChangeScene.GUI32AddQuestion(this, event1, Default.getText(), questionsList.get(k).getQuestionID(), editQuestionText[0], editQuestionText[1], choiceInfo1[0], choiceInfo1[1], choiceInfo1[2], choiceInfo2[0], choiceInfo2[1], choiceInfo2[2], choiceInfo3[0], choiceInfo3[1], choiceInfo3[2], choiceInfo4[0], choiceInfo4[1], choiceInfo4[2], choiceInfo5[0], choiceInfo5[1], choiceInfo5[2], choiceInfo6[0], choiceInfo6[1], choiceInfo6[2]);
                    });
                }
                table.setItems(questionsList);
                table.setVisible(true);
            }
        }));
        createQuestionButton.setOnAction(e -> {
            Hyperlink createQuestion_hl =new Hyperlink(" / "+"Adding a Multiple choice question");
            createQuestion_hl.setOnAction(event -> {
                BreadCrumb.changeBreadCrumb(breadCrumb,level,createQuestion_hl);
                ChangeScene.GUI32AddQuestion(this,event);
            });
            BreadCrumb.addBreadCrumb(breadCrumb,level,2,createQuestion_hl);
            ChangeScene.GUI32AddQuestion(this, e);
        });
    }
}
