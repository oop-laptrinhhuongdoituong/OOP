package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.FindCategoryName;
import com.example.baitaplonoop.util.addQuestion;
import com.example.baitaplonoop.util.insertCategoryIntoTreeView;
import com.example.baitaplonoop.util.insertQuestionIntoTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
    // check mouse on Label
    private boolean isMouseOnLabel(Label label, MouseEvent event) {
        Bounds bounds = label.getBoundsInParent();
        double mouseX = event.getSceneX();
        double mouseY = event.getSceneY();
        return bounds.contains(mouseX, mouseY);
    }

    //create click event for treeView
    private void showQuestionInCaseShowQuestionOfSubcategories(){
        category.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()== KeyCode.ENTER ){
                questionsList.clear();
                table.setItems(questionsList);
                TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                insertQuestionIntoTableView.insertQuestionIntoTableViewWithSubcategory(item,questionsList);
                category.setVisible(false);
                Default.setText(FindCategoryName.findCategoryName(item.getValue()));
                question.setCellValueFactory(new PropertyValueFactory<addQuestion,String>("questionText"));
                action.setCellValueFactory(new PropertyValueFactory<addQuestion,Button>("button"));
                table.setItems(questionsList);
                table.setVisible(true);
            }
        });
    }
    private void showQuestionsOfCategoryWithoutSubcategories(){
        category.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()== KeyCode.ENTER){
                questionsList.clear();
                table.setItems(questionsList);
                TreeItem<String> item=category.getSelectionModel().getSelectedItem();
                category.setVisible(false);
                Default.setText(FindCategoryName.findCategoryName(item.getValue()));
                insertQuestionIntoTableView.ínsertQuestionIntoTableViewWithoutSubcategory(item,questionsList);
                question.setCellValueFactory(new PropertyValueFactory<addQuestion,String>("questionText"));
                action.setCellValueFactory(new PropertyValueFactory<addQuestion,Button>("button"));
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
        categoriesButton.setOnAction(e -> {
            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI33AddCategory.fxml"));
            try {
                Parent GUI33AddCategory=loader.load();
                Scene scene=new Scene(GUI33AddCategory);
                stage.setScene(scene);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
      importButton.setOnAction(e -> {
            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI34.fxml"));
            try {
                Parent GUI33AddCategory=loader.load();
                Scene scene=new Scene(GUI33AddCategory);
                stage.setScene(scene);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        createQuestionButton.setOnAction(e -> {
            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI32AddQuestion.fxml"));
            try {
                Parent GUI33AddCategory=loader.load();
                Scene scene=new Scene(GUI33AddCategory);
                stage.setScene(scene);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
    }
}
