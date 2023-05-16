package com.example.baitaplonoop.util;

import com.example.baitaplonoop.sql.DBConnect;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.util.Pair;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class insertQuestionInto {
    static DBConnect db = new DBConnect();

    static List<String> listQuestionIDInQuiz = new ArrayList<>();
    public static List<String> insertQuesionToQuiz(){

        return listQuestionIDInQuiz;
    }

    public static void ínsertQuestionIntoTableViewWithoutSubcategoryInNewQuiz(TreeItem<String> item, ObservableList<addQuestion> questionsList) {
        String questionView = "select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'" + FindCategoryInfo.findCategoryName(item.getValue()) + "'";
        ResultSet rs1 = db.getData(questionView);

        try {
            while (rs1.next()) {
                FontAwesomeIconButton button = new FontAwesomeIconButton("add",FontAwesomeIcon.PLUS);
                addQuestion question1 = new addQuestion(rs1.getString("categoryID"), rs1.getString("questionID"), rs1.getString("questionText"), rs1.getString("questionImage"), rs1.getDouble("questionMark"), button);
                questionsList.add(question1);
                button.setOnMouseClicked(AddQuestionEvent ->{
                    button.setVisible(false);
                    String newQuestionIDInQuiz = question1.getQuestionID();
                    listQuestionIDInQuiz.add(newQuestionIDInQuiz);
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Add questions to the tableView if you also add questions of subcategories
    public static void insertQuestionIntoTableViewWithSubcategoryInNewQuiz(TreeItem<String> item, ObservableList<addQuestion> questionsList) {
        String questionView = "select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'" + FindCategoryInfo.findCategoryName(item.getValue()) + "'";
        ResultSet rs1 = db.getData(questionView);
        try {
            while (rs1.next()) {
                FontAwesomeIconButton button = new FontAwesomeIconButton("add",FontAwesomeIcon.PLUS);
                addQuestion question1 = new addQuestion(rs1.getString("categoryID"), rs1.getString("questionID"), rs1.getString("questionText"), rs1.getString("questionImage"), rs1.getDouble("questionMark"), button);
                questionsList.add(question1);
                button.setOnMouseClicked(AddQuestionEvent ->{
                    button.setVisible(false);
                    String newQuestionIDInQuiz = question1.getQuestionID();
                    listQuestionIDInQuiz.add(newQuestionIDInQuiz);
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (item.isLeaf() == false) {
            for (TreeItem<String> subItem : item.getChildren()) {
                insertQuestionIntoTableViewWithSubcategoryInNewQuiz(subItem, questionsList);
            }
        }
    }
    //Add questions to the listView if you also add questions of subcategories
    public static void insertQuestionIntoQuestionListWithSubcategoryInNewQuiz(TreeItem<String> item,ObservableList<String> questionsList,ObservableList<Pair<String,String>> questionRandom) {
        String questionView = "select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'" + FindCategoryInfo.findCategoryName(item.getValue()) + "'";
        ResultSet rs1 = db.getData(questionView);
        try {
            while (rs1.next()) {
                FontAwesomeIconButton button = new FontAwesomeIconButton("add",FontAwesomeIcon.PLUS);
                addQuestion question1 = new addQuestion(rs1.getString("categoryID"), rs1.getString("questionID"), rs1.getString("questionText"), rs1.getString("questionImage"), rs1.getDouble("questionMark"), button);
                questionsList.add(question1.getQuestionID() + ": " + question1.getQuestionText());
                button.setOnMouseClicked(AddQuestionEvent ->{
                    button.setVisible(false);
                    String newQuestionIDInQuiz;
                    newQuestionIDInQuiz = question1.getQuestionID();
                    listQuestionIDInQuiz.add(newQuestionIDInQuiz);
                });
                questionRandom.add(new Pair<>(question1.getQuestionID(), question1.getQuestionID() + ": " + question1.getQuestionText()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (item.isLeaf() == false) {
            for (TreeItem<String> subItem : item.getChildren()) {
                insertQuestionInto.insertQuestionIntoQuestionListWithSubcategoryInNewQuiz(subItem,questionsList,questionRandom);
            }
        }
    }
    public static void insertQuestionIntoQuestionListWithoutSubcategory(TreeItem<String> item,ObservableList<String> questionsList,ObservableList<Pair<String,String>> questionRandom) {
        String questionView = "select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'" + FindCategoryInfo.findCategoryName(item.getValue()) + "'";
        ResultSet rs1 = db.getData(questionView);
        try {
            while (rs1.next()) {
                addQuestion question1 = new addQuestion(rs1.getString("categoryID"), rs1.getString("questionID"), rs1.getString("questionText"), rs1.getString("questionImage"), rs1.getDouble("questionMark"), new Button("Edit"));
                questionsList.add((question1.getQuestionID() + ": " + question1.getQuestionText()));
                questionRandom.add(new Pair<>(question1.getQuestionID(), question1.getQuestionID() + ": " + question1.getQuestionText()));
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void ínsertQuestionIntoTableViewWithoutSubcategory(TreeItem<String> item, ObservableList<addQuestion> questionsList) {
        String questionView = "select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'" + FindCategoryInfo.findCategoryName(item.getValue()) + "'";
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

    //Add questions to the tableView if you also add questions of subcategories
    public static void insertQuestionIntoTableViewWithSubcategory(TreeItem<String> item, ObservableList<addQuestion> questionsList) {
        String questionView = "select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'" + FindCategoryInfo.findCategoryName(item.getValue()) + "'";
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
    //Add questions to the listView if you also add questions of subcategories
    public static void insertQuestionIntoQuestionListWithSubcategory(TreeItem<String> item,ObservableList<String> questionsList,ObservableList<Pair<String,String>> questionRandom) {
        String questionView = "select *from dbo.Question as q,dbo.Category as c where q.categoryID = c.categoryID and c.categoryName = N'" + FindCategoryInfo.findCategoryName(item.getValue()) + "'";
        ResultSet rs1 = db.getData(questionView);
        try {
            while (rs1.next()) {
                addQuestion question1 = new addQuestion(rs1.getString("categoryID"), rs1.getString("questionID"), rs1.getString("questionText"), rs1.getString("questionImage"), rs1.getDouble("questionMark"), new Button("Edit"));
                questionsList.add(question1.getQuestionID() + ": " + question1.getQuestionText());
                questionRandom.add(new Pair<>(question1.getQuestionID(), question1.getQuestionID() + ": " + question1.getQuestionText()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (item.isLeaf() == false) {
            for (TreeItem<String> subItem : item.getChildren()) {
                insertQuestionInto.insertQuestionIntoQuestionListWithSubcategory(subItem,questionsList,questionRandom);
            }
        }
    }
}
