package com.example.baitaplonoop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GUI11.fxml"));
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}