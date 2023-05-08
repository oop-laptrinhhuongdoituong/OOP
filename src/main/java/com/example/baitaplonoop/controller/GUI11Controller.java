package com.example.baitaplonoop.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GUI11Controller implements Initializable {

    public Button popup_btn;
    @FXML
    Button btnTurnEditingOn;
    FXMLLoader fxmlLoader;
    GUI11PopUpController controller;
    static Dialog<String> dialog;
    static EventHandler eventHandler;
//    static Stage GUI11Stage = (Stage) btnTurnEditingOn.getScene().getWindow();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        popup_btn.setOnMouseClicked(event -> {
            try {
                fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI11PopUp.fxml"));
                DialogPane pane = fxmlLoader.load();
                controller = fxmlLoader.getController();

                dialog = new Dialog<>();
                dialog.setDialogPane(pane);
                ButtonType xacNhan = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(xacNhan);
                dialog.setResultConverter(new Callback<ButtonType, String>() {
                    @Override
                    public String call(ButtonType buttonType) {
                        if(buttonType == xacNhan){
                            return controller.result;
                        }
                        return null;
                    }
                });
                Optional<String>  result = dialog.showAndWait();
                if (result.get().equals("Import")){
                    try {
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI34.fxml"));
                        Parent gui34 = null;
                        gui34 = fxmlLoader.load();
                        Scene scene = new Scene(gui34);
                        stage.setScene(scene);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if(result.get().equals("Category")){
                    try {
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI33AddCategory.fxml"));
                        Parent gui33 = null;
                        gui33 = fxmlLoader.load();
                        Scene scene = new Scene(gui33);
                        stage.setScene(scene);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (result.get().equals("Question")) {
                    try {
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUI21.fxml"));
                        Parent gui21 = null;
                        gui21 = fxmlLoader.load();
                        Scene scene = new Scene(gui21);
                        stage.setScene(scene);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
//
