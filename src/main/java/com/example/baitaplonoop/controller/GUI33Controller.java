package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.AlertOOP;
import com.example.baitaplonoop.util.ChangeScene;
import com.example.baitaplonoop.util.showTreeViewCategory;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class GUI33Controller implements Initializable {
    public TextField nameCategory_tf;
    public TextField categoryID_tf;
    public Button addCategory_Btn;
    public TextField infoCategory_tf;
    public TreeView<String> parentCategory_tv;
    public Label addParentCategory_lb;

    boolean checkAddParent = false;

    DBConnect db = new DBConnect();

    String parentCategoryName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Choose the parent category
        addParentCategory_lb.setOnMouseClicked(mouseEvent -> {
            //checkAddParent = true;
            parentCategory_tv.setVisible(true);
            addParentCategory_lb.setVisible(false);
            TreeItem<String> root = new TreeItem<>("Course IT:");
            showTreeViewCategory.setTreeViewImport("Select * from Category where parentID IS NULL", root);
            parentCategory_tv.setRoot(root);
            parentCategory_tv.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                addParentCategory_lb.setText(newValue.getValue());
                parentCategoryName = newValue.getValue();
                checkAddParent = true;
                System.out.println(parentCategoryName);
            });
        });

        // Event to press the Button Add Category
        addCategory_Btn.setOnMouseClicked(buttonAddCategoryEvent -> {
            // To check fill the field in Category if fill all the field then add data Category into Database
            if(nameCategory_tf.getText().trim().equals("") || categoryID_tf.getText().trim().equals("")){
                AlertOOP.mustFill("Add Category Status", "Add category fail", "You must file the blank field");
            } else if (!checkAddParent) {
                String[] addCategory = {null, nameCategory_tf.getText(), categoryID_tf.getText(), infoCategory_tf.getText()};
                int categoryRowInsert = db.InsertCategory(addCategory);
                AlertOOP.AddDone("Add Category status", "Add category done", "Add category without parent category");
                ChangeScene.changeSceneUsingMouseEvent(this,  "/com/example/baitaplonoop/GUI11.fxml", buttonAddCategoryEvent);
            } else {
                String IDParentCategory;
                try {
                    IDParentCategory = String.valueOf(db.FindCategoryID(parentCategoryName));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String[] addCategory = {IDParentCategory, nameCategory_tf.getText(), categoryID_tf.getText(), infoCategory_tf.getText()};
                int categoryRowInsert = db.InsertCategory(addCategory);
                AlertOOP.AddDone("Add Category status", "Add category done", "Add category with parent Category");
                ChangeScene.changeSceneUsingMouseEvent(this,  "/com/example/baitaplonoop/GUI11.fxml", buttonAddCategoryEvent);
            }
        });
    }
}
