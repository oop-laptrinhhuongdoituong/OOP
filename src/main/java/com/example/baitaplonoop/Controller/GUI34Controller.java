package com.example.baitaplonoop.Controller;

import com.example.baitaplonoop.sql.DBConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GUI34Controller implements Initializable {
    @FXML
    Label lbChooseImportCategory;
    @FXML
    TreeView<String> treeViewImport;
    DBConnect db = new DBConnect();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        treeViewImport.setVisible(false);
        lbChooseImportCategory.setOnMouseClicked(mouseEvent -> {
            treeViewImport.setVisible(true);
            TreeItem<String>  root = new TreeItem<>("Course IT:");
//            ResultSet rs = db.getData("SELECT * from Category where parentID IS NULL");
//            ArrayList<String> listRoot = new ArrayList<String>();
//            try{
//                while(rs.next()){
//                    listRoot.add(rs.getString("categoryName"));
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            for(String s : listRoot){
//                root.getChildren().add(new TreeItem<>(s));
//            }
//            treeViewImport.setRoot(root);
            setTreeViewImport("Select * from Category where parentID IS NULL", root);
            treeViewImport.setRoot(root);
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
