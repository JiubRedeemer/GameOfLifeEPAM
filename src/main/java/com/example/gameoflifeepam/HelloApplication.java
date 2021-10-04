package com.example.gameoflifeepam;

import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.view.MainViewJavaFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

//        SimCont simCont = new SimCont(new Grid(10,10), new MainViewJavaFX(), 10, 0);
//        simCont.control();

        MainViewJavaFX mainViewJavaFX = new MainViewJavaFX();
        Scene scene = new Scene(mainViewJavaFX, 700, 765);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

      // mainViewJavaFX.showNext();


    }

    public static void main(String[] args) {
        launch();
    }
}