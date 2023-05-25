package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.ChangeScene;
import com.example.baitaplonoop.util.showTreeViewCategory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
            checkAddParent = true;
            parentCategory_tv.setVisible(true);
            addParentCategory_lb.setVisible(false);
            TreeItem<String> root = new TreeItem<>("Course IT:");
            showTreeViewCategory.setTreeViewImport("Select * from Category where parentID IS NULL", root);
            parentCategory_tv.setRoot(root);
            parentCategory_tv.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                addParentCategory_lb.setText(newValue.getValue());
                parentCategoryName = newValue.getValue();
                System.out.println(parentCategoryName);
            });
        });

        // Event to press the Button Add Category
        addCategory_Btn.setOnMouseClicked(buttonAddCategoryEvent -> {
            String content = "";
            Alert alertAddCategory = new Alert(Alert.AlertType.INFORMATION);
            alertAddCategory.setTitle("Add Category Information");
            ButtonType btnContinue = new ButtonType("Oke", ButtonBar.ButtonData.YES);
            ButtonType btnBack = new ButtonType("Home page", ButtonBar.ButtonData.NO);
            alertAddCategory.getButtonTypes().setAll(btnContinue, btnBack);

            // To check fill the field in Category if fill all the field then add data Category into Database
            if(checkAddParent == false || nameCategory_tf.getText().trim().equals("") || categoryID_tf.getText().trim().equals("")){
                content = "You must fill all the field!";
            } else {
                String IDParentCategory = null;
                try {
                    IDParentCategory = String.valueOf(db.FindCategoryID(parentCategoryName));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String addCategory[] = {IDParentCategory, nameCategory_tf.getText(), categoryID_tf.getText(), infoCategory_tf.getText()};
                int categoryRowInsert = db.InsertCategory(addCategory);
                content = "Insert successfully!";
            }
            alertAddCategory.setContentText(content);
            Optional<ButtonType> result = alertAddCategory.showAndWait();
            if(result.get().equals(btnBack)){
                ChangeScene.changeSceneUsingMouseEvent(this, "/com/example/baitaplonoop/GUI11.fxml", buttonAddCategoryEvent);
            }
        });
    }


}
