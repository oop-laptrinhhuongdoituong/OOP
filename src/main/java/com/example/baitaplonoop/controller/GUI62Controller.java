package com.example.baitaplonoop.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.LabelView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUI62Controller implements Initializable {
    @FXML
    private Label addLabel;
    @FXML
    private AnchorPane gui62;
    @FXML
    private ListView<Label> listModeAdd;
    private ObservableList<Label> addMode= FXCollections.observableArrayList();
    private void addQuestionMode(){
        listModeAdd.setVisible(false);
        Label label1=new Label("a new question");
        Label label2=new Label("from question bank");
        Label label3=new Label("random a question");
        addMode.addAll(label1,label2,label3);
        listModeAdd.getItems().addAll(addMode);
    }
    // check mouse on
    private boolean isMouseOnLabel(Label label, MouseEvent event) {
        Bounds bounds = label.getBoundsInParent();
        double mouseX = event.getSceneX();
        double mouseY = event.getSceneY();
        return bounds.contains(mouseX, mouseY);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      addQuestionMode();
      addLabel.setOnMouseClicked(mouseEvent -> {
            if(!listModeAdd.isVisible()) listModeAdd.setVisible(true);
            else listModeAdd.setVisible(false);
        });
      gui62.setOnMouseClicked(mouseEvent -> {
            if(listModeAdd.isVisible()&& isMouseOnLabel(addLabel,mouseEvent)==false)listModeAdd.setVisible(false);
        });
      listModeAdd.setOnMouseClicked(mouseEvent -> {
          Label label=listModeAdd.getSelectionModel().getSelectedItem();
          if(label.getText()=="random a question"){
              label.setOnMouseClicked(mouseEvent1 -> {
                  Stage stage = (Stage)((Node) mouseEvent1.getSource()).getScene().getWindow();
                  FXMLLoader loader = new FXMLLoader();
                  loader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI65.fxml"));
                  try {
                      Parent GUI65=loader.load();
                      Scene scene=new Scene(GUI65);
                      stage.setScene(scene);
                  } catch (IOException ex) {
                      throw new RuntimeException(ex);
                  }
              });
          }
      });
    }
}
