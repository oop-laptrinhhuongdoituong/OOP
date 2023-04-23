package com.example.baitaplonoop.Gui;

import com.example.baitaplonoop.DBConnect;
import com.example.baitaplonoop.Model.addQuestion;
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
import org.apache.poi.xssf.usermodel.helpers.XSSFXmlColumnPr;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class Gui6_5 implements Initializable {
    @FXML
    private CheckBox gui6_5CheckBox;
    @FXML
    private AnchorPane gui6_5;
    @FXML
    private Label Default;
    @FXML
    private ListView<String> questionsListView;
    @FXML
    private TreeView<String> category;
    @FXML
    private ComboBox<String> numberOfQuestions;
    private ObservableList<String> numberOfComboBox= FXCollections.observableArrayList();
    private ObservableList<String> questionsList= FXCollections.observableArrayList();
    DBConnect db=new DBConnect();
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
    //find number of question of category
    private String numberQuestionsOfACategory(TreeItem<String> item){
        String a=item.getValue();
        int n=a.length()-1;
        int i=n-1;
        if(a.charAt(n)!=')')return "0";
        while(a.charAt(i)!='('&&i>=0){
            if(a.charAt(i)<'0'||a.charAt(i)>'9')return "0";
            i--;
        }
        return a.substring(i+1,n);
    }
    private String numberQuestionOfCategoryAndSubCategories(TreeItem<String> item) {
        String b = numberQuestionsOfACategory(item);
        if (item.isLeaf()) {
            return b;
        } else {
          for(TreeItem<String> item1: item.getChildren()){
              b=Integer.toString(Integer.parseInt(b)+Integer.parseInt(numberQuestionOfCategoryAndSubCategories(item1)));
          }
          return b;
        }
    }
    //Add questions to the table if you also add questions of subcategories
    public void insertQuestionIntoListViewWithSubcategory(TreeItem<String> item){
        String questionView="select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'"+findCategoryName(item.getValue())+"'";
        ResultSet rs1=db.getData(questionView);
        try {
            while (rs1.next()){
                addQuestion question1=new addQuestion(rs1.getString("questionID"),rs1.getString("questionText"),new Button("Edit"));
                questionsList.add(question1.getQuestionID()+": " +question1.getQuestionText());
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        if(item.isLeaf()==false){
            for(TreeItem<String> subItem : item.getChildren()){
                insertQuestionIntoListViewWithSubcategory(subItem);
            }
        }
    }
    private void showQuestionInCaseShowQuestionOfSubcategories(){
        category.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount()==3){
                questionsList.clear();
                numberOfComboBox.clear();
                numberOfQuestions.getSelectionModel().clearSelection();
                questionsListView.setItems(questionsList);
                TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                insertQuestionIntoListViewWithSubcategory(item);
                category.setVisible(false);
                Default.setText(findCategoryName(item.getValue()));
                questionsListView.setItems(questionsList);
                for(int i=1;i<=Integer.parseInt(numberQuestionOfCategoryAndSubCategories(item));i++){
                    numberOfComboBox.add(Integer.toString(i));
                }
                numberOfQuestions.setItems(numberOfComboBox);
                questionsListView.setVisible(true);
            }
        });
        category.setOnKeyPressed(keyEvent -> {

            if(keyEvent.getCode()==KeyCode.ENTER){
                questionsList.clear();
                numberOfComboBox.clear();
                numberOfQuestions.getSelectionModel().clearSelection();
                questionsListView.setItems(questionsList);
                TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                insertQuestionIntoListViewWithSubcategory(item);
                category.setVisible(false);
                Default.setText(findCategoryName(item.getValue()));
                questionsListView.setItems(questionsList);
                for(int i=1;i<=Integer.parseInt(numberQuestionOfCategoryAndSubCategories(item));i++){
                    numberOfComboBox.add(Integer.toString(i));
                }
                numberOfQuestions.setItems(numberOfComboBox);
                questionsListView.setVisible(true);
            }
        });
    }
    private void showQuestionsOfCategoryWithoutSubcategories(){
        category.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount()==3){
                questionsList.clear();
                numberOfComboBox.clear();
                numberOfQuestions.getSelectionModel().clearSelection();
                questionsListView.setItems(questionsList);
                TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                category.setVisible(false);
                Default.setText(findCategoryName(item.getValue()));
                String categoryName=findCategoryName(item.getValue());
                String questionView="select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'"+categoryName+"'";
                ResultSet rs1=db.getData(questionView);
                try {
                    while (rs1.next()){
                        addQuestion question1=new addQuestion(rs1.getString("questionID"),rs1.getString("questionText"),new Button("Edit"));
                        questionsList.add((question1.getQuestionID()+": "+question1.getQuestionText()));
                    }
                    questionsListView.setItems(questionsList);
                    for(int i=1;i<=Integer.parseInt(numberQuestionsOfACategory(item));i++){
                        numberOfComboBox.add(Integer.toString(i));
                    }
                    numberOfQuestions.setItems(numberOfComboBox);
                   questionsListView.setVisible(true);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        });
        category.setOnKeyPressed(keyEvent -> {

            if(keyEvent.getCode()== KeyCode.ENTER){
                questionsList.clear();
                numberOfComboBox.clear();
                numberOfQuestions.getSelectionModel().clearSelection();
                questionsListView.setItems(questionsList);
                TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                category.setVisible(false);
                Default.setText(findCategoryName(item.getValue()));
                String categoryName=findCategoryName(item.getValue());
                String questionView="select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'"+categoryName+"'";
                ResultSet rs1=db.getData(questionView);
                try {
                    while (rs1.next()){
                        addQuestion question1=new addQuestion(rs1.getString("questionID"),rs1.getString("questionText"),new Button("Edit"));
                        questionsList.add((question1.getQuestionID()+": "+question1.getQuestionText()));
                    }
                    questionsListView.setItems(questionsList);
                    for(int i=1;i<=Integer.parseInt(numberQuestionsOfACategory(item));i++){
                        numberOfComboBox.add(Integer.toString(i));
                    }
                    numberOfQuestions.setItems(numberOfComboBox);
                    questionsListView.setVisible(true);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle){
        questionsListView.setVisible(false);
        category.setVisible(false);
        Default.setText("Default");
        Default.setOnMouseClicked(mouseEvent -> {


            TreeItem<String> root = new TreeItem<>("For IT");
            try {
                insertCategory("select * from dbo.Category where parentID is NULL", root);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            category.setRoot(root);
            category.toFront();
            category.setVisible(true);
        });
        gui6_5CheckBox.setSelected(false);
        if (gui6_5CheckBox.isSelected()) {
            showQuestionInCaseShowQuestionOfSubcategories();
        }
        else {
            showQuestionsOfCategoryWithoutSubcategories();
        }
        gui6_5CheckBox.setOnAction(event -> {
            if (gui6_5CheckBox.isSelected()) {
                showQuestionInCaseShowQuestionOfSubcategories();
            }
            else {
                showQuestionsOfCategoryWithoutSubcategories();
            }
        });
        gui6_5.setOnMouseClicked(mouseEvent -> {
            if(category.isVisible()&& isMouseOnLabel(Default,mouseEvent)==false)category.setVisible(false);
        });
    }
}