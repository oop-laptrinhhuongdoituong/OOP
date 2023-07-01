package com.example.baitaplonoop.controller;


import com.example.baitaplonoop.sql.DBConnect;
import com.example.baitaplonoop.util.BreadCrumb;
import com.example.baitaplonoop.util.ChangeScene;
import com.example.baitaplonoop.util.TableQuestionsOfGui62;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Pair;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI11Controller.*;

public class GUI62Controller implements Initializable {
    @FXML
    private CheckBox shuffle;
    @FXML
    private ToggleButton selectMultipleItems;
    @FXML
    private Button deleteMultipleItems;
    @FXML
    private Label addLabel;
    @FXML
    private ListView<Label> listModeAdd;
    private final ObservableList<Label> addMode = FXCollections.observableArrayList();
    private final ObservableList<TableQuestionsOfGui62> chosenQuestions = FXCollections.observableArrayList();
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
        String a="Question: "+ chosenQuestions.size() +"| This quiz is open";
        numberQuestionInTable.setText(a);
        String b="Total of mark: "+ chosenQuestions.size() +".00";
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
        MultiQuestionsChoice.setCellValueFactory(new PropertyValueFactory<>("multiQuestionsChoice"));
        Order.setCellValueFactory(new PropertyValueFactory<>("order"));
        Setting.setCellValueFactory(new PropertyValueFactory<>("setting"));
        QuestionText.setCellValueFactory(new PropertyValueFactory<>("questionText"));
        PlusIcon.setCellValueFactory(new PropertyValueFactory<>("plusIcon"));
        DeleteIcon.setCellValueFactory(new PropertyValueFactory<>("deleteIcon"));
        QuestionMark.setCellValueFactory(new PropertyValueFactory<>("questionMark"));
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
    private void setStatementShuffle(String quizName) throws SQLException {
        DBConnect db=new DBConnect();
        ResultSet rs=db.getData("select shuffle from Quiz where quizName = N'"+quizName+"'");
        while (rs.next()){
            boolean shuffleStatement=rs.getBoolean("shuffle");
            shuffle.setSelected(shuffleStatement);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quizName.setText("Editing quiz: " + quizChosen);
        deleteMultipleItems.setVisible(false);
        tableQuestions.setVisible(false);
        addQuestionIntoTable();
        addQuestionMode();
        try {
            setStatementShuffle(quizChosen);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        addLabel.setOnMouseClicked(mouseEvent -> listModeAdd.setVisible(!listModeAdd.isVisible()));
        listModeAdd.setOnMouseClicked(mouseEvent -> {
            Label label = listModeAdd.getSelectionModel().getSelectedItem();
            if (Objects.equals(label.getText(), "random a question")) {
                label.setOnMouseClicked(mouseEvent1 -> {
                    Hyperlink randomquestion_hl=new Hyperlink(" / "+"Add random a question");
                    randomquestion_hl.setOnMouseClicked(mouseEvent2 -> {
                        BreadCrumb.changeBreadCrumb(breadCrumb,level,randomquestion_hl);
                        ChangeScene.GUI65(this,mouseEvent2);
                    });
                    BreadCrumb.addBreadCrumb(breadCrumb,level,3,randomquestion_hl);
                   ChangeScene.GUI65(this,mouseEvent1);
                });
            }
            if(Objects.equals(label.getText(), "from question bank")){
                label.setOnMouseClicked(mouseEvent1 -> {
                    Hyperlink addFromQuestionBank_hl=new Hyperlink(" / "+"Add question from question bank");
                    addFromQuestionBank_hl.setOnMouseClicked(mouseEvent2 -> {
                        BreadCrumb.changeBreadCrumb(breadCrumb,level,addFromQuestionBank_hl);
                        ChangeScene.GUI63(this,mouseEvent2);
                    });
                    BreadCrumb.addBreadCrumb(breadCrumb,level,3,addFromQuestionBank_hl);
                    ChangeScene.GUI63(this, mouseEvent1);
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
        save.setOnMouseClicked(event -> {
            DBConnect db=new DBConnect();
            try {
                db.updateQuiz(shuffle.isSelected(),quizChosen);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for (TableQuestionsOfGui62 chosenQuestion : chosenQuestions) {
                String[] addQuestionInQuiz = {chosenQuestion.getQuestionID(), quizChosen, null};
                try {
                    int row = db.InsertQuestionInQuiz(addQuestionInQuiz);
                    System.out.println(row);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
             Hyperlink quiz_hf=new Hyperlink(" / "+quizChosen);
            quiz_hf.setOnMouseClicked(event1 -> {
                BreadCrumb.changeBreadCrumb(breadCrumb,level,quiz_hf);
                ChangeScene.GUI61PreviewQuiz(this, event1);
            });
            BreadCrumb.addBreadCrumb(breadCrumb,level,1,quiz_hf);
            ChangeScene.GUI61PreviewQuiz(this,event);
        });
    }
}