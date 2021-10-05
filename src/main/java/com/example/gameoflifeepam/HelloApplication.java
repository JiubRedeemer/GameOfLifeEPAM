package com.example.gameoflifeepam;

import com.example.gameoflifeepam.view.MainViewJavaFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        MainViewJavaFX mainViewJavaFX = new MainViewJavaFX();
        Scene scene = new Scene(mainViewJavaFX, 700, 765);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}