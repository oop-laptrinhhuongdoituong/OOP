package com.example.baitaplonoop.Controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.ImportFile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
            if(files.size() == 0){
                lbAlert.setText("There are no files imported");
            }
            String path = files.get(0).getName();
            if(path.substring(path.length()-4, path.length()).equals("docx")){
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
            }
            else if(path.substring(path.length()-3, path.length()).equals("txt")){
                lbAlert.setText("Correct format");
            }
            else{
                lbAlert.setText("Wrong format");
            }
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
