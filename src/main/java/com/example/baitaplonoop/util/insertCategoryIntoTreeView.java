package com.example.baitaplonoop.util;

import com.example.baitaplonoop.sql.DBConnect;
import javafx.scene.control.TreeItem;

import java.sql.ResultSet;

public class insertCategoryIntoTreeView {
    static DBConnect db = new DBConnect();

    //insert category into the TreeView
    public static void insertCategory(String query1, TreeItem<String> root) throws Exception {
        ResultSet rs1 = db.getData(query1);
        try {
            while (rs1.next()) {
                String childrenCategory = rs1.getString("categoryName");
                String numberQuestions;
                ResultSet rs2 = db.getData(("select count(*) as cnt from dbo.Category as c,dbo.Question as q where q.categoryID=c.categoryID and c.categoryName= N'") + childrenCategory + "'");
                rs2.next();
                String childrenCategory1;
                numberQuestions = Integer.toString(rs2.getInt("cnt"));
                if (numberQuestions.equals("0") == false) {
                    childrenCategory1 = childrenCategory + "(" + numberQuestions + ")";
                } else {
                    childrenCategory1 = childrenCategory;
                }
                TreeItem<String> item = new TreeItem<>(childrenCategory1);
                String a = "Select * from dbo.Category where parentID = N'" + rs1.getString("categoryID") + "'";
                insertCategory(a, item);
                root.getChildren().add(item);
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
