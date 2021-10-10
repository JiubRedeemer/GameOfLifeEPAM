package com.example.gameoflifeepam;

import com.example.gameoflifeepam.view.StartViewJavaFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        StartViewJavaFX startViewJavaFX = new StartViewJavaFX();
        Scene scene = new Scene(startViewJavaFX, 900, 35);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}