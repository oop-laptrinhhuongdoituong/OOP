package com.example.baitaplonoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Gui2_1 implements Initializable {
    @FXML
    private TableView<addQuestion> table;

    @FXML
    private TableColumn<addQuestion, String> question;

    @FXML
    private TableColumn<addQuestion, Button> action;
    private ObservableList<addQuestion> questionsList= FXCollections.observableArrayList();;
    @FXML
   private Label Default;
    @FXML
    private TreeView<String> category;
    DBConnect db=new DBConnect();
    void insertCategory(String query,TreeItem<String> root) throws Exception {
        table.setVisible(false);
      ResultSet rs1=db.getData(query);
      try {
          while(rs1.next()){
              String childrenCategory=rs1.getString("categoryName");
              TreeItem<String> item= new TreeItem<>(childrenCategory);
              String a="Select * from dbo.Category where parentID = N'"+rs1.getString("categoryID")+"'";
               insertCategory(a,item);
               root.getChildren().add(item);

          }

      }catch (Exception e){
          throw new Exception(e);
      }
    }
   @Override
    public void initialize(URL location, ResourceBundle resourceBundle){
        category.setVisible(false);
        table.setVisible(false);
        Default.setOnMouseClicked(mouseEvent -> {

            TreeItem<String> root=new TreeItem<>("For IT");
            try {
                insertCategory("select * from dbo.Category where parentID is NULL",root);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            category.setRoot(root);
            category.setVisible(true);
        });
       category.setOnMouseClicked(mouseEvent -> {
           questionsList.clear();
           table.setItems(questionsList);
         if(mouseEvent.getClickCount()==2 && category.getSelectionModel().getSelectedItem().isLeaf()==true){

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
           if(keyEvent.getCode()== KeyCode.ENTER &&category.getSelectionModel().getSelectedItem().isLeaf()==true){
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
}
