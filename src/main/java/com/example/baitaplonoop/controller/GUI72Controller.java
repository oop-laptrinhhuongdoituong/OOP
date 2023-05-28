package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.util.ExportFilePDF;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static com.example.baitaplonoop.controller.GUI61Controller.*;

public class GUI72Controller implements Initializable {
    @FXML
    Label lbWarning;
    @FXML
    Button btnStart;
    @FXML
    Button btnCancel;
    @FXML
    Button btnExport;
    public static LocalDateTime openTime;

    void setUpScene(){
        lbWarning.setText("Your attempt will have a time limit of " + timeLimit + " mins. When you start, the timer will begin to count down and cannot be paused. You must finish your attempt before it expires. Are you sure to start quiz now?");
    }
    void setEvent(){
        btnCancel.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        });
        btnExport.setOnAction(event -> {
            ExportFilePDF.exportPDFFile();
        });
        btnStart.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            if(isOpenable == true) {
                openTime = LocalDateTime.now();
                Stage oldstage = (Stage) ((Node) StartEvent.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI73.fxml"));
                Parent parent = null;
                try {
                    parent = fxmlLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(parent);
                oldstage.setScene(scene);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Warning");
                alert.setContentText("This quiz isn't available");
                alert.show();
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpScene();
        setEvent();
    }
}
