package com.example.baitaplonoop.controller;

import com.example.baitaplonoop.util.BreadCrumb;
import com.example.baitaplonoop.util.ChangeScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static com.example.baitaplonoop.controller.GUI11Controller.*;


public class GUI11PopUpController implements Initializable{
    @FXML
    private Hyperlink hlQuestion;
    @FXML
    private Hyperlink hlCategory;
    @FXML
    private Hyperlink hlImport;
    public static double currentWidth;
    public static double currentHeight;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hlQuestion.setOnMouseClicked(mouseEvent -> {
            Hyperlink question_hf=new Hyperlink(" / "+hlQuestion.getText());
            question_hf.setOnAction(e -> {
                BreadCrumb.changeBreadCrumb(breadCrumb,level,question_hf);
                ChangeScene.GUI21ListQuestion(this,e);

            });
            BreadCrumb.addBreadCrumb(breadCrumb,level,1,question_hf);
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
            Stage oldstage = (Stage) ((Node) settingEvent.getSource()).getScene().getWindow();
            currentWidth = oldstage.getScene().getWidth();
            currentHeight = oldstage.getScene().getHeight();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUInew.fxml"));
            Parent parent;
            try {
                parent = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(parent, currentWidth, currentHeight);
            GUI11Controller gui11Controller = fxmlLoader.getController();
            gui11Controller.tabPane.setVisible(true);
            Tab tab = gui11Controller.questionTab_tp;
            gui11Controller.tabPane.getSelectionModel().select(tab);
            oldstage.setScene(scene);
        });
        hlCategory.setOnMouseClicked(mouseEvent -> {
            Hyperlink category_hl=new Hyperlink(" / "+hlCategory.getText());
            category_hl.setOnMouseClicked(mouseEvent1 -> {
                ChangeScene.GUI33AddCategory(this,mouseEvent1);
                BreadCrumb.changeBreadCrumb(breadCrumb,level,category_hl);
            });
            BreadCrumb.addBreadCrumb(breadCrumb,level,1,category_hl);

            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
            Stage oldstage = (Stage) ((Node) settingEvent.getSource()).getScene().getWindow();
            currentWidth = oldstage.getScene().getWidth();
            currentHeight = oldstage.getScene().getHeight();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUInew.fxml"));
            Parent parent;
            try {
                parent = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(parent, currentWidth, currentHeight);
            GUI11Controller gui11Controller = fxmlLoader.getController();
            gui11Controller.tabPane.setVisible(true);
            Tab tab = gui11Controller.categoryTab_tp;
            gui11Controller.tabPane.getSelectionModel().select(tab);
            oldstage.setScene(scene);
        });
        hlImport.setOnMouseClicked(mouseEvent -> {
            Hyperlink import_hf=new Hyperlink(" / "+hlImport.getText());
            import_hf.setOnAction(e -> {
                ChangeScene.GUI34ImportFileQuestion(this,e);
                BreadCrumb.changeBreadCrumb(breadCrumb,level,import_hf);
            });
            BreadCrumb.addBreadCrumb(breadCrumb,level,1,import_hf);
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.close();
            Stage oldstage = (Stage) ((Node) settingEvent.getSource()).getScene().getWindow();
            currentWidth = oldstage.getScene().getWidth();
            currentHeight = oldstage.getScene().getHeight();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/baitaplonoop/GUInew.fxml"));
            Parent parent;
            try {
                parent = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(parent, currentWidth, currentHeight);
            GUI11Controller gui11Controller = fxmlLoader.getController();
            gui11Controller.tabPane.setVisible(true);
            Tab tab = gui11Controller.importTab_tp;
            gui11Controller.tabPane.getSelectionModel().select(tab);
            oldstage.setScene(scene);
        });
    }
}
