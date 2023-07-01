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

import static com.example.baitaplonoop.controller.GUI11Controller.breadCrumb;
import static com.example.baitaplonoop.controller.GUI11Controller.level;

public class GUI33Controller implements Initializable {
    public TextField nameCategory_tf;
    public TextField categoryID_tf;
    public Button addCategory_Btn;

    public TreeView<String> parentCategory_tv;
    public Label addParentCategory_lb;
    public TextArea infoCategory_tf;
    boolean checkAddParent = false;
    DBConnect db = new DBConnect();
    String parentCategoryName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
           parentCategory_tv.setVisible(false);
        // Event to show category in TreeView
        addParentCategory_lb.setOnMouseClicked(mouseEvent -> {
            if(parentCategory_tv.isVisible()){
                parentCategory_tv.setVisible(false);
            }
            else {
                parentCategory_tv.setVisible(true);
                //checkAddCategoryQuestion = true;
                TreeItem<String> root = new TreeItem<>("Course IT:");
                showTreeViewCategory.setTreeViewImport("Select * from Category where parentID IS NULL", root);
                parentCategory_tv.setRoot(root);
                parentCategory_tv.setOnKeyPressed(keyEvent -> {
                    TreeItem<String> item = parentCategory_tv.getSelectionModel().getSelectedItem();
                    addParentCategory_lb.setText(item.getValue());
                    parentCategoryName = item.getValue();
                    parentCategory_tv.setVisible(false);
                    addParentCategory_lb.setVisible(true);
                    checkAddParent = true;
                });

            }
        });
        // Event to press the Button Add Category
        addCategory_Btn.setOnMouseClicked(buttonAddCategoryEvent -> {
            // To check fill the field in Category if fill all the field then add data Category into Database
            if (nameCategory_tf.getText().trim().equals("") || categoryID_tf.getText().trim().equals("")) {
                AlertOOP.mustFill("Add Category Status", "Add category fail", "You must file the blank field");
            } else if (!checkAddParent) {
                String[] addCategory = {null, nameCategory_tf.getText(), categoryID_tf.getText(), infoCategory_tf.getText()};
                db.InsertCategory(addCategory);
                AlertOOP.AddDone("Add Category status", "Add category done", "Add category without parent category");
                breadCrumb.clear();
                level.clear();
                ChangeScene.mainSceneGUI11(this, buttonAddCategoryEvent);
            } else {
                String IDParentCategory;
                try {
                    IDParentCategory = String.valueOf(db.FindCategoryID(parentCategoryName));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String[] addCategory = {IDParentCategory, nameCategory_tf.getText(), categoryID_tf.getText(), infoCategory_tf.getText()};
                db.InsertCategory(addCategory);
                AlertOOP.AddDone("Add Category status", "Add category done", "Add category with parent Category");
                breadCrumb.clear();
                level.clear();
                ChangeScene.mainSceneGUI11(this, buttonAddCategoryEvent);
            }
        });
    }
}
