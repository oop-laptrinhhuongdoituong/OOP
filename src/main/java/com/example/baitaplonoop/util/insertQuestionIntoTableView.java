package com.example.baitaplonoop.util;

import com.example.baitaplonoop.sql.DBConnect;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;

import java.sql.ResultSet;

public class insertQuestionIntoTableView {
    static DBConnect db = new DBConnect();

    public static void ínsertQuestionIntoTableViewWithoutSubcategory(TreeItem<String> item, ObservableList<addQuestion> questionsList) {
        String questionView = "select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'" + FindCategoryName.findCategoryName(item.getValue()) + "'";
        ResultSet rs1 = db.getData(questionView);
        try {
            while (rs1.next()) {
                Button button = new Button("edit");
                addQuestion question1 = new addQuestion(rs1.getString("categoryID"), rs1.getString("questionID"), rs1.getString("questionText"), rs1.getString("questionImage"), rs1.getDouble("questionMark"), button);
                questionsList.add(question1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Add questions to the table if you also add questions of subcategories
    public static void insertQuestionIntoTableViewWithSubcategory(TreeItem<String> item, ObservableList<addQuestion> questionsList) {
        String questionView = "select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'" + FindCategoryName.findCategoryName(item.getValue()) + "'";
        ResultSet rs1 = db.getData(questionView);
        try {
            while (rs1.next()) {
                addQuestion question1 = new addQuestion(rs1.getString("categoryID"), rs1.getString("questionID"), rs1.getString("questionText"), rs1.getString("questionImage"), rs1.getDouble("questionMark"), new Button("Edit"));
                questionsList.add(question1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (item.isLeaf() == false) {
            for (TreeItem<String> subItem : item.getChildren()) {
                insertQuestionIntoTableViewWithSubcategory(subItem, questionsList);
            }
        }
    }
}
