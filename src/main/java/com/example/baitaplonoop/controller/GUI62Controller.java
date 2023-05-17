package com.example.baitaplonoop.controller;


import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.FindCategoryInfo;
import com.example.baitaplonoop.util.IsMouseOnLabel;
import com.example.baitaplonoop.util.TableQuestionsOfGui62;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.quizChosen;
import static javafx.scene.control.TableView.UNCONSTRAINED_RESIZE_POLICY;

public class GUI62Controller implements Initializable {
    DBConnect db=new DBConnect();
    @FXML
    private ToggleButton selectMultipleItems;
    @FXML
    private Button deleteMultipleItems;
    @FXML
    private Label addLabel;
    @FXML
    private AnchorPane gui62;
    @FXML
    private ListView<Label> listModeAdd;
    private ObservableList<Label> addMode = FXCollections.observableArrayList();
    private ObservableList<TableQuestionsOfGui62> chosenQuestions = FXCollections.observableArrayList();
    @FXML
    private TableView<TableQuestionsOfGui62> tableQuestions;

    @FXML
    private TableColumn<TableQuestionsOfGui62, Label> DeleteIcon;

    @FXML
    private TableColumn<TableQuestionsOfGui62, CheckBox> MultiQuestionsChoice;
    @FXML
    private Label numberQuestionInTable;

    @FXML
    private TableColumn<TableQuestionsOfGui62, Text> Order;

    @FXML
    private TableColumn<TableQuestionsOfGui62, Label> PlusIcon;

    @FXML
    private TableColumn<TableQuestionsOfGui62, TextField> QuestionMark;

    @FXML
    private TableColumn<TableQuestionsOfGui62, String> QuestionText;

    @FXML
    private TableColumn<TableQuestionsOfGui62, Label> Setting;
    @FXML
    private Label totalOfMark;
    @FXML
    private Label quizNameLink;
    @FXML
    private Label quizName;
    @FXML
    private Button save;

    private void addQuestionMode() {
        listModeAdd.setVisible(false);
        Label label1 = new Label("a new question");
        Label label2 = new Label("from question bank");
        Label label3 = new Label("random a question");
        addMode.addAll(label1, label2, label3);
        listModeAdd.getItems().addAll(addMode);
    }
    private void numberQuestionAndMarkInTable(){
        String a="Question: "+Integer.toString(chosenQuestions.size())+"| This quiz is open";
        numberQuestionInTable.setText(a);
        String b="Total of mark: "+Integer.toString(chosenQuestions.size())+".00";
        totalOfMark.setText(b);
    }
    public void setChosenQuestions(ObservableList<Pair<String, String>> randomQuestion) {
        tableQuestions.setVisible(false);
        int stt = 0;
        for (Pair<String, String> a : randomQuestion) {
            stt++;
            TableQuestionsOfGui62 tableQuestionsOfGui62;

            //Tạo checkbox
            CheckBox multiQuestionsChoice = new CheckBox();
            //Tạo oder
            Text order = new Text();
            order.setText(Integer.toString(stt));
            //Tạo questionText
            String questionText = a.getValue();
            //Tạo questionID
            String questionID = a.getKey();
            //tạo biểu tượng setting
            Label setting = new Label();
            FontAwesomeIconView iconView = new FontAwesomeIconView(FontAwesomeIcon.GEAR);
            iconView.setSize("22px");
            iconView.setFill(Paint.valueOf("#000000"));
            setting.setGraphic(iconView);
            //Tạo biểu tượng plusIcon
            Label plushIcon = new Label();
            FontAwesomeIconView iconView1 = new FontAwesomeIconView(FontAwesomeIcon.SEARCH_PLUS);
            iconView1.setSize("22px");
            iconView1.setFill(Paint.valueOf("#000000"));
            plushIcon.setGraphic(iconView1);
            //tạo biểu tượng deleteIcon
            Label deleteIcon = new Label();
            FontAwesomeIconView iconView2 = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            iconView2.setSize("22px");
            iconView2.setFill(Paint.valueOf("#000000"));
            deleteIcon.setGraphic(iconView2);
            deleteIcon.setOnMouseClicked(mouseEvent -> {
            });
            //tạo textField questionMark
            TextField questionMark = new TextField("1.00");
            questionMark.setDisable(true);
            tableQuestionsOfGui62 = new TableQuestionsOfGui62(multiQuestionsChoice, order, setting, questionText, questionID, plushIcon, deleteIcon, questionMark);
            chosenQuestions.add(tableQuestionsOfGui62);
        }
        deleteEvent();
        numberQuestionAndMarkInTable();
        tableQuestions.setVisible(true);
    }

    private void addQuestionIntoTable() {
        tableQuestions.setVisible(false);
        tableQuestions.setTableMenuButtonVisible(false);
        tableQuestions.setStyle("-fx-table-cell-border-color: transparent;");
        MultiQuestionsChoice.setVisible(false);
        MultiQuestionsChoice.setCellValueFactory(new PropertyValueFactory<TableQuestionsOfGui62, CheckBox>("multiQuestionsChoice"));
        Order.setCellValueFactory(new PropertyValueFactory<TableQuestionsOfGui62, Text>("order"));
        Setting.setCellValueFactory(new PropertyValueFactory<TableQuestionsOfGui62, Label>("setting"));
        QuestionText.setCellValueFactory(new PropertyValueFactory<TableQuestionsOfGui62, String>("questionText"));
        PlusIcon.setCellValueFactory(new PropertyValueFactory<TableQuestionsOfGui62, Label>("plusIcon"));
        DeleteIcon.setCellValueFactory(new PropertyValueFactory<TableQuestionsOfGui62, Label>("deleteIcon"));
        QuestionMark.setCellValueFactory(new PropertyValueFactory<TableQuestionsOfGui62, TextField>("questionMark"));
        numberQuestionAndMarkInTable();
        tableQuestions.setItems(chosenQuestions);
    }
    private void deleteEvent(){
        for(TableQuestionsOfGui62 a: chosenQuestions){
            a.getDeleteIcon().setOnMouseClicked(mouseEvent -> {
                    chosenQuestions.remove(a);
                    int i=0;
                    for(TableQuestionsOfGui62 b :chosenQuestions){
                        i++;
                        b.getOrder().setText(Integer.toString(i));
                    }
                    numberQuestionAndMarkInTable();
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quizNameLink.setText("/" + quizChosen + "/ Edit quiz");
        quizName.setText("Editing quiz: " + quizChosen);
        deleteMultipleItems.setVisible(false);
        tableQuestions.setVisible(false);
        addQuestionIntoTable();
        addQuestionMode();
        addLabel.setOnMouseClicked(mouseEvent -> {
            if (!listModeAdd.isVisible()) listModeAdd.setVisible(true);
            else listModeAdd.setVisible(false);
        });
        gui62.setOnMouseClicked(mouseEvent -> {
            if (listModeAdd.isVisible() && IsMouseOnLabel.isMouseOnLabel(addLabel, mouseEvent) == false)
                listModeAdd.setVisible(false);
        });
        listModeAdd.setOnMouseClicked(mouseEvent -> {
            Label label = listModeAdd.getSelectionModel().getSelectedItem();
            if (label.getText() == "random a question") {
                label.setOnMouseClicked(mouseEvent1 -> {
                    Stage stage = (Stage) ((Node) mouseEvent1.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI65.fxml"));
                    try {
                        Parent GUI65 = loader.load();
                        Scene scene = new Scene(GUI65);
                        stage.setScene(scene);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
            if(label.getText()=="from question bank"){
                label.setOnMouseClicked(mouseEvent1 -> {
                    Stage stage = (Stage) ((Node) mouseEvent1.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI63.fxml"));
                    try {
                        Parent GUI63 = loader.load();
                        Scene scene = new Scene(GUI63);
                        stage.setScene(scene);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        });
    selectMultipleItems.setOnAction(event -> {
        if(selectMultipleItems.isSelected()){
            MultiQuestionsChoice.setVisible(true);
            deleteMultipleItems.setVisible(true);
        }
        else {
            MultiQuestionsChoice.setVisible(false);
            deleteMultipleItems.setVisible(false);
        }
    });
    deleteMultipleItems.setOnAction(event -> {
        for(int i=0;i<chosenQuestions.size();i++){
            if(chosenQuestions.get(i).getMultiQuestionsChoice().isSelected()){
                deleteMultipleItems.setVisible(true);
                chosenQuestions.remove(chosenQuestions.get(i));
                i--;
            }
        }
        int i=0;
        for(TableQuestionsOfGui62 b :chosenQuestions){
            i++;
            b.getOrder().setText(Integer.toString(i));
        }
        numberQuestionAndMarkInTable();
    });
    save.setOnAction(event -> {
      for(int i=0;i<chosenQuestions.size();i++){
          String[] addQuestionInQuiz={chosenQuestions.get(i).getQuestionID(),quizChosen,null};
          try {
              int row = db.InsertQuestionInQuiz(addQuestionInQuiz);
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
      }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI61.fxml"));
        try {
            Parent gui61 = loader.load();
            Scene scene = new Scene(gui61);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });
    }
}
