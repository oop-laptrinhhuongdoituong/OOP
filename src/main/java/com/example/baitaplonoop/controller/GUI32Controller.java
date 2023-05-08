package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.showTreeViewCategory;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.baitaplonoop.util.addValueComboBox;

public class GUI32Controller implements Initializable {
    public TreeView<String> showCategory_tv;
    public TextArea questionText_tf;
    public TextField questionName_tf;
    public Label categoryName_lb;
    public Button addQuestion_btn;
    public TextField choice1_tf;
    public ComboBox<String> gradeChoice1_cb;
    public ComboBox<String> gradeChoice2_cb;
    public TextField choice2_tf;
    public TextField choice3_tf;
    public ComboBox gradeChoice3_cb;
    public TextField choice4_tf;
    public ComboBox gradeChoice4_cb;
    public TextField choice5_tf;
    public ComboBox gradeChoice5_cb;
    public TextField choice6_tf;
    public ComboBox gradeChoice6_cb;
    public Button createChoice_btn;
    public AnchorPane paneChoice2_ap;
    public AnchorPane buttonPane_ap;
    public AnchorPane paneInScrollPane_ap;
    boolean checkAddCategoryQuestion;
    String nameCategoryQuestion;
    Double gradeChoice1, gradeChoice2, gradeChoice3, gradeChoice4, gradeChoice5, gradeChoice6;

    DBConnect db = new DBConnect();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        addValueComboBox.addValue(gradeChoice1_cb);
        addValueComboBox.addValue(gradeChoice2_cb);
        addValueComboBox.addValue(gradeChoice3_cb);
        addValueComboBox.addValue(gradeChoice4_cb);
        addValueComboBox.addValue(gradeChoice5_cb);
        addValueComboBox.addValue(gradeChoice6_cb);

        gradeChoice1_cb.setOnAction(gradeChoice1Event ->{
            gradeChoice1 = addValueComboBox.convertStringToDouble(gradeChoice1_cb);
        });
        gradeChoice2_cb.setOnAction(gradeChoice2Event ->{
            gradeChoice2 = addValueComboBox.convertStringToDouble(gradeChoice2_cb);
        });
        gradeChoice3_cb.setOnAction(gradeChoice3Event ->{
            gradeChoice3 = addValueComboBox.convertStringToDouble(gradeChoice3_cb);
        });
        gradeChoice4_cb.setOnAction(gradeChoice4Event ->{
            gradeChoice4 = addValueComboBox.convertStringToDouble(gradeChoice4_cb);
        });
        gradeChoice5_cb.setOnAction(gradeChoice5Event ->{
            gradeChoice5 = addValueComboBox.convertStringToDouble(gradeChoice5_cb);
        });
        gradeChoice6_cb.setOnAction(gradeChoice6Event ->{
            gradeChoice6 = addValueComboBox.convertStringToDouble(gradeChoice6_cb);
        });

        // Event to show treeview
        categoryName_lb.setOnMouseClicked(mouseEvent -> {
            showCategory_tv.setVisible(true);
            categoryName_lb.setVisible(false);
            checkAddCategoryQuestion = true;
            TreeItem<String> root = new TreeItem<>("Course IT:");
            showTreeViewCategory.setTreeViewImport("Select * from Category where parentID IS NULL", root);
            showCategory_tv.setRoot(root);
            showCategory_tv.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                categoryName_lb.setText(newValue.getValue());
                nameCategoryQuestion = newValue.getValue();
            });
        });
        // Event to add Quetion into Database
        addQuestion_btn.setOnMouseClicked(saveChangeEvent ->{
            Alert alertAddCategory = new Alert(Alert.AlertType.INFORMATION);
            alertAddCategory.setTitle("Add Category Information");
            ButtonType btnContinue = new ButtonType("Oke", ButtonBar.ButtonData.YES);
            ButtonType btnBack = new ButtonType("Home page", ButtonBar.ButtonData.NO);
            alertAddCategory.getButtonTypes().setAll(btnContinue, btnBack);

            if(checkAddCategoryQuestion == false || questionName_tf.getText() == null || questionText_tf.getText() == null) {
                alertAddCategory.setContentText("Please fill the blank field");
                alertAddCategory.setHeaderText("You must fill all field!");
                alertAddCategory.showAndWait();
            } else {
                String IDCategoryQuestion = null;
                try {
                    IDCategoryQuestion = String.valueOf(db.FindCategoryID(nameCategoryQuestion));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String addQuestion[] = {IDCategoryQuestion, questionName_tf.getText(), questionText_tf.getText()};
                int categoryRowInsert = db.InsertQuestion(addQuestion);

                alertAddCategory.setContentText("Add Category Successfull");
                alertAddCategory.setHeaderText(null);
                alertAddCategory.showAndWait();
            }
        });
        // Event to creat new 3 choice
        createChoice_btn.setOnMouseClicked(createChoiceEvent ->{
            paneChoice2_ap.setTranslateY(239);
            buttonPane_ap.setTranslateY(239);
        });
    }

}
