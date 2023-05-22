package com.example.baitaplonoop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    FXMLLoader loader = new FXMLLoader(Main.class.getResource("GUI32AddQuestion.fxml"));

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("The Quiz");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
