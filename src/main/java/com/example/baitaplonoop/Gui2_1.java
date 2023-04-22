package com.example.baitaplonoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Gui2_1 implements Initializable {
    @FXML
    AnchorPane gui2_1;
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

    void insertCategory(String query, TreeItem<String> root) throws Exception {
        table.setVisible(false);
        ResultSet rs1 = db.getData(query);
        try {
            while (rs1.next()) {
                String childrenCategory = rs1.getString("categoryName");
                TreeItem<String> item = new TreeItem<>(childrenCategory);
                String a = "Select * from dbo.Category where parentID = N'" + rs1.getString("categoryID") + "'";
                insertCategory(a, item);
                root.getChildren().add(item);

            }

        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    public void insertQuestionIntoTableViewWithSubcategory(TreeItem<String> item){
        String questionView="select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'"+item.getValue()+"'";
        ResultSet rs1=db.getData(questionView);
        try {
            while (rs1.next()){
                addQuestion question1=new addQuestion(rs1.getString("questionID"),rs1.getString("questionText"),new Button("Edit"));
                questionsList.add(question1);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        if(item.isLeaf()==false){
            for(TreeItem<String> subItem : item.getChildren()){
                insertQuestionIntoTableViewWithSubcategory(subItem);
            }
        }
    }
    private boolean isMouseOnLabel(Label label, MouseEvent event) {
        Bounds bounds = label.getBoundsInParent();
        double mouseX = event.getSceneX();
        double mouseY = event.getSceneY();
        return bounds.contains(mouseX, mouseY);
    }
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        category.setVisible(false);
        table.setVisible(false);

        Default.setOnMouseClicked(mouseEvent -> {

            TreeItem<String> root = new TreeItem<>("For IT");
            try {
                insertCategory("select * from dbo.Category where parentID is NULL", root);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            category.setRoot(root);
            category.setVisible(true);
        });
        gui2_1CheckBox.setSelected(false);
        if (gui2_1CheckBox.isSelected()) {
            category.setOnMouseClicked(mouseEvent -> {
                questionsList.clear();
                table.setItems(questionsList);
                if(mouseEvent.getClickCount()==3){

                    TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                    insertQuestionIntoTableViewWithSubcategory(item);
                    category.setVisible(false);
                    Default.setText(item.getValue());
                    question.setCellValueFactory(new PropertyValueFactory<addQuestion,String>("questionText"));
                    action.setCellValueFactory(new PropertyValueFactory<addQuestion,Button>("button"));
                    table.setItems(questionsList);
                    table.setVisible(true);
                }
            });
            category.setOnKeyPressed(keyEvent -> {
                questionsList.clear();
                table.setItems(questionsList);
                if(keyEvent.getCode()== KeyCode.ENTER ){
                    TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                    insertQuestionIntoTableViewWithSubcategory(item);
                    category.setVisible(false);
                    Default.setText(item.getValue());
                    question.setCellValueFactory(new PropertyValueFactory<addQuestion,String>("questionText"));
                    action.setCellValueFactory(new PropertyValueFactory<addQuestion,Button>("button"));
                    table.setItems(questionsList);
                    table.setVisible(true);
                }
            });
        }
        else {
            category.setOnMouseClicked(mouseEvent -> {
                questionsList.clear();
                table.setItems(questionsList);
                if(mouseEvent.getClickCount()==3){

                    TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                    category.setVisible(false);
                    Default.setText(item.getValue());
                    String questionView="select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'"+item.getValue()+"'";
                    ResultSet rs1=db.getData(questionView);
                    try {
                        while (rs1.next()){
                            addQuestion question1=new addQuestion(rs1.getString("questionID"),rs1.getString("questionText"),new Button("Edit"));
                            questionsList.add(question1);
                        }
                        question.setCellValueFactory(new PropertyValueFactory<addQuestion,String>("questionText"));
                        action.setCellValueFactory(new PropertyValueFactory<addQuestion,Button>("button"));
                        table.setItems(questionsList);
                        table.setVisible(true);
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
            });
            category.setOnKeyPressed(keyEvent -> {
                if(keyEvent.getCode()== KeyCode.ENTER){
                    questionsList.clear();
                    table.setItems(questionsList);
                    TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                    category.setVisible(false);
                    String questionView="select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'"+item.getValue()+"'";
                    ResultSet rs1=db.getData(questionView);
                    Default.setText(item.getValue());
                    try {
                        while (rs1.next()){
                            addQuestion question1=new addQuestion(rs1.getString("questionID"),rs1.getString("questionText"),new Button("Edit"));
                            questionsList.add(question1);
                        }
                        question.setCellValueFactory(new PropertyValueFactory<addQuestion,String>("questionText"));
                        action.setCellValueFactory(new PropertyValueFactory<addQuestion,Button>("button"));
                        table.setItems(questionsList);
                        table.setVisible(true);
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        gui2_1CheckBox.setOnAction(event -> {
            if (gui2_1CheckBox.isSelected()) {
                category.setOnMouseClicked(mouseEvent -> {
                    questionsList.clear();
                    table.setItems(questionsList);
                    if(mouseEvent.getClickCount()==3){

                        TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                        insertQuestionIntoTableViewWithSubcategory(item);
                        category.setVisible(false);
                        Default.setText(item.getValue());
                        question.setCellValueFactory(new PropertyValueFactory<addQuestion,String>("questionText"));
                        action.setCellValueFactory(new PropertyValueFactory<addQuestion,Button>("button"));
                        table.setItems(questionsList);
                        table.setVisible(true);
                    }
                });
                category.setOnKeyPressed(keyEvent -> {
                    questionsList.clear();
                    table.setItems(questionsList);
                    if(keyEvent.getCode()== KeyCode.ENTER ){
                        TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                        insertQuestionIntoTableViewWithSubcategory(item);
                        category.setVisible(false);
                        Default.setText(item.getValue());
                        question.setCellValueFactory(new PropertyValueFactory<addQuestion,String>("questionText"));
                        action.setCellValueFactory(new PropertyValueFactory<addQuestion,Button>("button"));
                        table.setItems(questionsList);
                        table.setVisible(true);
                    }
                });
            }
            else {
                category.setOnMouseClicked(mouseEvent -> {
                    questionsList.clear();
                    table.setItems(questionsList);
                    if(mouseEvent.getClickCount()==3){

                        TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                        category.setVisible(false);
                        Default.setText(item.getValue());
                        String questionView="select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'"+item.getValue()+"'";
                        ResultSet rs1=db.getData(questionView);
                        try {
                            while (rs1.next()){
                                addQuestion question1=new addQuestion(rs1.getString("questionID"),rs1.getString("questionText"),new Button("Edit"));
                                questionsList.add(question1);
                            }
                            question.setCellValueFactory(new PropertyValueFactory<addQuestion,String>("questionText"));
                            action.setCellValueFactory(new PropertyValueFactory<addQuestion,Button>("button"));
                            table.setItems(questionsList);
                            table.setVisible(true);
                        }catch (Exception e){
                            throw new RuntimeException(e);
                        }
                    }
                });
                category.setOnKeyPressed(keyEvent -> {
                    if(keyEvent.getCode()== KeyCode.ENTER){
                        questionsList.clear();
                        table.setItems(questionsList);
                        TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                        category.setVisible(false);
                        String questionView="select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'"+item.getValue()+"'";
                        ResultSet rs1=db.getData(questionView);
                        Default.setText(item.getValue());
                        try {
                            while (rs1.next()){
                                addQuestion question1=new addQuestion(rs1.getString("questionID"),rs1.getString("questionText"),new Button("Edit"));
                                questionsList.add(question1);
                            }
                            question.setCellValueFactory(new PropertyValueFactory<addQuestion,String>("questionText"));
                            action.setCellValueFactory(new PropertyValueFactory<addQuestion,Button>("button"));
                            table.setItems(questionsList);
                            table.setVisible(true);
                        }catch (Exception e){
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
        gui2_1.setOnMouseClicked(mouseEvent -> {
            if(category.isVisible()&& isMouseOnLabel(Default,mouseEvent)==false)category.setVisible(false);
        });
    }
}
