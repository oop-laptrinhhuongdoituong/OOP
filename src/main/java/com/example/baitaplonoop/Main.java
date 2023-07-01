package com.example.baitaplonoop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static double currentWidth;
    public static double currentHeight;
    FXMLLoader loader = new FXMLLoader(Main.class.getResource("GUInew.fxml"));
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
