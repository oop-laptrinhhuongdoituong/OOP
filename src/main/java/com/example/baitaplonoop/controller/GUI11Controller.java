package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.BreadCrumb;
import com.example.baitaplonoop.util.ChangeScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class GUI11Controller implements Initializable {
    public Tab questionTab_tp;
    public AnchorPane GUI21_tp;
    public AnchorPane GUI33_tp;
    public AnchorPane GUI34_tp;
    public Tab categoryTab_tp;
    public Tab importTab_tp;
    public TabPane tabPane;
    public AnchorPane listQuiz_ap;
    public Button setting_btn;
    public AnchorPane addQuestion_ap;
    public Label message;
    public AnchorPane addQuiz_ap;
    public AnchorPane GUI61_ap;
    public AnchorPane GUI62_ap;
    public AnchorPane GUI63_ap;
    @FXML
    public AnchorPane GUI65_ap;
    @FXML
    public  FlowPane breadcrumb;
    @FXML
    ListView<String> lvQuiz = new ListView<>();
    @FXML
    Button btnTurnEditingOn;
    private final ArrayList<String> listQuiz = new ArrayList<>();
    public static ObservableList<Hyperlink> breadCrumb=FXCollections.observableArrayList();
    public static ObservableList<Integer> level=FXCollections.observableArrayList();
    DBConnect db = new DBConnect();
    public static MouseEvent settingEvent;
    public static String quizChosen = "";

    public void setListViewQuiz(){
        ResultSet rs = db.getData("Select * from Quiz");
        try{
            while (rs.next()){
                listQuiz.add(rs.getString("quizName"));
            }
            lvQuiz.setItems(FXCollections.observableArrayList(listQuiz));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setEvent(){
        lvQuiz.setOnMouseClicked(mouseEvent -> {
            quizChosen = lvQuiz.getSelectionModel().getSelectedItem();
            if(listQuiz.contains(quizChosen)){
                Hyperlink quiz_hf=new Hyperlink(" / "+quizChosen);
                quiz_hf.setOnMouseClicked(event -> {
                    BreadCrumb.changeBreadCrumb(breadCrumb,level,quiz_hf);
                    ChangeScene.GUI61PreviewQuiz(this, event);
                });
                BreadCrumb.addBreadCrumb(breadCrumb,level,1,quiz_hf);
                ChangeScene.GUI61PreviewQuiz(this, mouseEvent);
            }
        });

//        btnTurnEditingOn.setOnAction(turnEditingOneEvent -> ChangeScene.GUI35AddQuiz(this,turnEditingOneEvent));
        btnTurnEditingOn.setOnAction(turnEditingOneEvent -> {
            Hyperlink addQuiz_hl= new Hyperlink(" / Adding a new Quiz");
            addQuiz_hl.setOnAction(event -> {
                BreadCrumb.changeBreadCrumb(breadCrumb,level,addQuiz_hl);
                ChangeScene.GUI35AddQuiz(this,turnEditingOneEvent);
            });
            BreadCrumb.addBreadCrumb(breadCrumb,level,1,addQuiz_hl);
            ChangeScene.GUI35AddQuiz(this, turnEditingOneEvent);
        });

        setting_btn.setOnMouseClicked(event -> {
            settingEvent = event;
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI11PopUp.fxml"));
            try {
                Parent parent = fxmlLoader.load();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void setBreadcrumb(){
       Hyperlink homeHyperlink= new Hyperlink("Home");
       homeHyperlink.setOnMouseClicked(event -> {
           BreadCrumb.changeBreadCrumb(breadCrumb,level,homeHyperlink);
           ChangeScene.mainSceneGUI11(this,event);
       });
       int tmp=0;
       for(Hyperlink hyperlink: breadCrumb){
           if(Objects.equals(hyperlink.getText(), "Home"))tmp++;
       }
       if(tmp==0){
           breadCrumb.add(homeHyperlink);
           level.add(0);
       }
       breadcrumb.getChildren().clear();
       breadcrumb.getChildren().addAll(breadCrumb);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        setListViewQuiz();
        setEvent();
        setBreadcrumb();

       categoryTab_tp.setOnSelectionChanged(event -> {
           Hyperlink category_hl=new Hyperlink(" / "+categoryTab_tp.getText());
           category_hl.setOnMouseClicked(mouseEvent -> {
               ChangeScene.GUI33AddCategory(this,mouseEvent);
               BreadCrumb.changeBreadCrumb(breadCrumb,level,category_hl);
           });
           BreadCrumb.addBreadCrumb(breadCrumb,level,1,category_hl);
           breadcrumb.getChildren().clear();
           breadcrumb.getChildren().addAll(breadCrumb);
       });
        questionTab_tp.setOnSelectionChanged(event -> {
            Hyperlink question_hf=new Hyperlink(" / "+questionTab_tp.getText());
            question_hf.setOnAction(e -> {
                BreadCrumb.changeBreadCrumb(breadCrumb,level,question_hf);
                ChangeScene.GUI21ListQuestion(this,e);
            });
            BreadCrumb.addBreadCrumb(breadCrumb,level,1,question_hf);
            breadcrumb.getChildren().clear();
            breadcrumb.getChildren().addAll(breadCrumb);
        });
        importTab_tp.setOnSelectionChanged(event -> {
            Hyperlink import_hf=new Hyperlink(" / "+importTab_tp.getText());
            import_hf.setOnAction(e -> {
                ChangeScene.GUI34ImportFileQuestion(this,e);
                BreadCrumb.changeBreadCrumb(breadCrumb,level,import_hf);
            });
            BreadCrumb.addBreadCrumb(breadCrumb,level,1,import_hf);
            breadcrumb.getChildren().clear();
            breadcrumb.getChildren().addAll(breadCrumb);
        });
    }
}

