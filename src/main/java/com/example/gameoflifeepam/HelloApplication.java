package com.example.gameoflifeepam;

import com.example.gameoflifeepam.view.MainViewJavaFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {




        MainViewJavaFX mainViewJavaFX = new MainViewJavaFX();
        Scene scene = new Scene(mainViewJavaFX, 400, 512);
        stage.setScene(scene);
        stage.show();

        mainViewJavaFX.showNext();


    }

    public static void main(String[] args) {
        launch();
    }
}