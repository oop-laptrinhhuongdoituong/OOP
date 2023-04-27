package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.addQuestion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class GUI21Controller implements Initializable {
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
    //insert category into the TreeView
    void insertCategory(String query1,TreeItem<String> root) throws Exception {
        ResultSet rs1 = db.getData(query1);
        try {
            while (rs1.next()) {
                String childrenCategory = rs1.getString("categoryName");
                String numberQuestions;
                ResultSet rs2=db.getData(("select count(*) as cnt from dbo.Category as c,dbo.Question as q where q.categoryID=c.categoryID and c.categoryName= N'")+ childrenCategory+"'");
                rs2.next();
                String childrenCategory1;
                numberQuestions=Integer.toString(rs2.getInt("cnt"));
                if(numberQuestions.equals("0")==false){
                childrenCategory1=childrenCategory+"("+numberQuestions+")";}
                else {
                    childrenCategory1=childrenCategory;
                }
                TreeItem<String> item = new TreeItem<>(childrenCategory1);
                String a = "Select * from dbo.Category where parentID = N'" + rs1.getString("categoryID") + "'";
                insertCategory(a, item);
                root.getChildren().add(item);

            }

        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    //Add questions to the table if you also add questions of subcategories
    public void insertQuestionIntoTableViewWithSubcategory(TreeItem<String> item){
        String questionView="select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'"+findCategoryName(item.getValue())+"'";
        ResultSet rs1=db.getData(questionView);
        try {
            while (rs1.next()){
                addQuestion question1=new addQuestion(rs1.getString("categoryID"),rs1.getString("questionID"),rs1.getString("questionText"),rs1.getString("questionImage"),rs1.getDouble("questionMark"),new Button("Edit"));
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
    // check mouse on Label
    private boolean isMouseOnLabel(Label label, MouseEvent event) {
        Bounds bounds = label.getBoundsInParent();
        double mouseX = event.getSceneX();
        double mouseY = event.getSceneY();
        return bounds.contains(mouseX, mouseY);
    }
    //find Name of category from treeView
    private String findCategoryName(String a){
        int n=a.length()-1;
        int i=n-1;
        if(a.charAt(n)!=')')return a;
        while(a.charAt(i)!='('&&i>=0){
            if(a.charAt(i)<'0'||a.charAt(i)>'9')return a;
            i--;
        }
        return a.substring(0,i);
    }
    //create click event for treeView
    private void showQuestionInCaseShowQuestionOfSubcategories(){
        category.setOnMouseClicked(mouseEvent -> {

            if(mouseEvent.getClickCount()==3){
                questionsList.clear();
                table.setItems(questionsList);
                TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                insertQuestionIntoTableViewWithSubcategory(item);
                category.setVisible(false);
                Default.setText(findCategoryName(item.getValue()));
                question.setCellValueFactory(new PropertyValueFactory<addQuestion,String>("questionText"));
                action.setCellValueFactory(new PropertyValueFactory<addQuestion,Button>("button"));
                table.setItems(questionsList);
                table.setVisible(true);
            }
        });
        category.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()== KeyCode.ENTER ){
                questionsList.clear();
                table.setItems(questionsList);
                TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                insertQuestionIntoTableViewWithSubcategory(item);
                category.setVisible(false);
                Default.setText(findCategoryName(item.getValue()));
                question.setCellValueFactory(new PropertyValueFactory<addQuestion,String>("questionText"));
                action.setCellValueFactory(new PropertyValueFactory<addQuestion,Button>("button"));
                table.setItems(questionsList);
                table.setVisible(true);
            }
        });
    }
    private void showQuestionsOfCategoryWithoutSubcategories(){
        category.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount()==3){
                questionsList.clear();
                table.setItems(questionsList);
                TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                category.setVisible(false);
                Default.setText(findCategoryName(item.getValue()));
                String categoryName=findCategoryName(item.getValue());
                String questionView="select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'"+categoryName+"'";
                ResultSet rs1=db.getData(questionView);
                try {
                    while (rs1.next()){
                        addQuestion question1=new addQuestion(rs1.getString("categoryID"),rs1.getString("questionID"),rs1.getString("questionText"),rs1.getString("questionImage"),rs1.getDouble("questionMark"),new Button("Edit"));
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
                String questionView="select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'"+findCategoryName(item.getValue())+"'";
                ResultSet rs1=db.getData(questionView);
                Default.setText(findCategoryName(item.getValue()));
                try {
                    while (rs1.next()){
                        addQuestion question1=new addQuestion(rs1.getString("categoryID"),rs1.getString("questionID"),rs1.getString("questionText"),rs1.getString("questionImage"),rs1.getDouble("questionMark"),new Button("Edit"));
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

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        category.setVisible(false);
        table.setVisible(false);
        Default.setOnMouseClicked(mouseEvent -> {
            Default.setText("Default");

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
            showQuestionInCaseShowQuestionOfSubcategories();
        }
        else {
            showQuestionsOfCategoryWithoutSubcategories();
        }
        gui2_1CheckBox.setOnAction(event -> {
            if (gui2_1CheckBox.isSelected()) {
              showQuestionInCaseShowQuestionOfSubcategories();
            }
            else {
                showQuestionsOfCategoryWithoutSubcategories();
            }
        });
        gui2_1.setOnMouseClicked(mouseEvent -> {
            if(category.isVisible()&& isMouseOnLabel(Default,mouseEvent)==false)category.setVisible(false);
        });
    }
}
