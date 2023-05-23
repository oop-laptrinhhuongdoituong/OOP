package com.example.baitaplonoop.util;

import com.example.baitaplonoop.sql.DBConnect;
import javafx.scene.control.TreeItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class showTreeViewCategory {
    static DBConnect db = new DBConnect();
    public static void setTreeViewImport(String sql, TreeItem<String> root){
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