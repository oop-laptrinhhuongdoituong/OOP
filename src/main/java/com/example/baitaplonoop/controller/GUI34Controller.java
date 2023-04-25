package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.ImportFile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        treeViewImport.setVisible(false);
        lbChooseImportCategory.setOnMouseClicked(mouseEvent -> {
            treeViewImport.setVisible(true);
            TreeItem<String>  root = new TreeItem<>("Course IT:");
            setTreeViewImport("Select * from Category where parentID IS NULL", root);
            treeViewImport.setRoot(root);
            treeViewImport.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                lbChooseImportCategory.setText(newValue.getValue());
            });
        });
        apDropFile.setOnDragOver(dragEvent -> {
            if(dragEvent.getDragboard().hasFiles()){
                dragEvent.acceptTransferModes(TransferMode.ANY);
            }
        });
        apDropFile.setOnDragDropped(dragEvent -> {
                files = dragEvent.getDragboard().getFiles();
                File file = files.get(0);
                lbFilePath.setText(file.getName());
        });
        btnImport.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Import information");
            ButtonType btnContinue = new ButtonType("Continue", ButtonBar.ButtonData.YES);
            ButtonType btnBack = new ButtonType("Home page", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(btnContinue, btnBack);
            String contentText = "";
            if(files.size() == 0){
                contentText = "There are no files imported";
                lbAlert.setText("There are no files imported");
            }
            String path = files.get(0).getName();
            if(path.substring(path.length()-4, path.length()).equals("docx")){
                if(ImportFile.checkFileDOCX(files.get(0))){
                    lbAlert.setText("Correct format");
                    ResultSet rs = db.getData("SELECT * from Category where categoryName = N'" + lbChooseImportCategory.getText() + "'");
                    String categoryID = "";
                    try{
                        while(rs.next()){
                            categoryID = rs.getString("categoryID");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    ImportFile.importQuestionFromDocxFile(files.get(0), categoryID);
                    contentText = "Success: " + numberOfQuestion + " question(s) imported";
                }else{
                    lbAlert.setText("Error at: " + ErrorLine);
                    contentText = "Error at: " + ErrorLine;
                }
            }
            else if(path.substring(path.length()-3, path.length()).equals("txt")){
                if(ImportFile.checkFileTXT(files.get(0))){
                    lbAlert.setText("Correct format");
                    ResultSet rs = db.getData("SELECT * from Category where categoryName = N'" + lbChooseImportCategory.getText() + "'");
                    String categoryID = "";
                    try{
                        while(rs.next()){
                            categoryID = rs.getString("categoryID");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    ImportFile.importQuestionFromTXTFile(files.get(0), categoryID);
                    contentText = "Success: " + numberOfQuestion + " question(s) imported";
                }else{
                    lbAlert.setText("Error at: " + ErrorLine);
                    contentText = "Error at: " + ErrorLine;
                }
            }
            else{
                lbAlert.setText("Wrong format");
                contentText = "Wrong format";
            }
            alert.setContentText(contentText);
            Optional<ButtonType> result = alert.showAndWait();
        });
    }

    public void setTreeViewImport(String sql, TreeItem<String> root){
        ResultSet rs = db.getData(sql);
        ArrayList<String> categoryName = new ArrayList<>();
        ArrayList<String> categoryID = new ArrayList<>();
        try{
            while(rs.next()){
                categoryID.add(rs.getString("categoryID"));
                categoryName.add(rs.getString("categoryName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int len = categoryID.size();
        for(int i = 0; i<len; i++){
            TreeItem<String> item = new TreeItem<>(categoryName.get(i));
            root.getChildren().add(item);
            String s = "Select * from Category where parentID = '" + categoryID.get(i) + "'";
            setTreeViewImport(s, item);
        }
    }
}
