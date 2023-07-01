package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.breadCrumb;
import static com.example.baitaplonoop.controller.GUI11Controller.level;
import static com.example.baitaplonoop.util.ImportFile.ErrorLine;
import static com.example.baitaplonoop.util.ImportFile.numberOfQuestion;

public class GUI34Controller implements Initializable {
    @FXML
    Label lbChooseImportCategory;
    @FXML
    TreeView<String> treeViewImport;
    @FXML
    AnchorPane apDropFile;
    @FXML
    Label lbFilePath;
    @FXML
    Button btnImport;
    @FXML
    Button btnChooseAFile;
    @FXML
    Label lbAlert;
    List<File> files;
    DBConnect db = new DBConnect();

    public void setEvent(){
        lbChooseImportCategory.setOnMouseClicked(mouseEvent -> {
            if(treeViewImport.isVisible()){
                treeViewImport.setVisible(false);
            }
            else {
                try {
                    treeViewImport.setVisible(true);
                    TreeItem<String> root = new TreeItem<>("Course IT:");
                    treeViewImport.setRoot(root);
                    insertCategoryIntoTreeView.insertCategory("select * from Category where parentID IS NULL", root);
                    treeViewImport.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> lbChooseImportCategory.setText(FindCategoryInfo.findCategoryName(newValue.getValue())));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        apDropFile.setOnDragOver(dragEvent -> {
            if (dragEvent.getDragboard().hasFiles()) {
                dragEvent.acceptTransferModes(TransferMode.ANY);
            }
        });
        apDropFile.setOnDragDropped(dragEvent -> {
            files = dragEvent.getDragboard().getFiles();
            File file = files.get(0);
            lbFilePath.setText(file.getName());
        });
        btnImport.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Import information");
            ButtonType btnContinue = new ButtonType("Continue", ButtonBar.ButtonData.YES);
            ButtonType btnBack = new ButtonType("Home page", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(btnContinue, btnBack);
            String contentText;
            if (files == null || files.size() == 0) {
                contentText = "There are no files imported";
                lbAlert.setText("There are no files imported");
                AlertOOP.mustFill("Import File", " ", contentText);
            } else {
                String path = files.get(0).getName();
                if (path.endsWith("docx")) {
                    if (ImportFile.checkFileDOCX(files.get(0))) {
                        lbAlert.setText("Correct format");
                        ResultSet rs = db.getData("SELECT * from Category where categoryName = N'" + lbChooseImportCategory.getText() + "'");
                        String categoryID = "";
                        try {
                            while (rs.next()) {
                                categoryID = rs.getString("categoryID");
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        ImportFile.importQuestionFromDocxFile(files.get(0), categoryID);
                        contentText = "Success: " + numberOfQuestion + " question(s) imported";
                    } else {
                        lbAlert.setText("Error at: " + ErrorLine);
                        contentText = "Error at: " + ErrorLine;
                    }
                } else if (path.endsWith("txt")) {
                    if (ImportFile.checkFileTXT(files.get(0))) {
                        lbAlert.setText("Correct format");
                        ResultSet rs = db.getData("SELECT * from Category where categoryName = N'" + lbChooseImportCategory.getText() + "'");
                        String categoryID = "";
                        try {
                            while (rs.next()) {
                                categoryID = rs.getString("categoryID");
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        ImportFile.importQuestionFromTXTFile(files.get(0), categoryID);
                        contentText = "Success: " + numberOfQuestion + " question(s) imported";
                    } else {
                        lbAlert.setText("Error at: " + ErrorLine);
                        contentText = "Error at: " + ErrorLine;
                    }
                } else {
                    lbAlert.setText("Wrong format");
                    contentText = "Wrong format";
                }
                AlertOOP.AddDone("Import File", " ", contentText);
                breadCrumb.clear();
                level.clear();
                ChangeScene.mainSceneGUI11(this,event);
            }
        });
        btnChooseAFile.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Choose a file");
            FileChooser.ExtensionFilter fileFilter = new FileChooser.ExtensionFilter("Files", "*.txt", "*.docx");
            fc.getExtensionFilters().add(fileFilter);
            File file = fc.showOpenDialog(null);
            if (files == null) {
                files = new ArrayList<>();
            }
            files.clear();
            if (file != null) {
                files.add(file);
                lbFilePath.setText(file.getName());
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        treeViewImport.setVisible(false);
        setEvent();
    }
}