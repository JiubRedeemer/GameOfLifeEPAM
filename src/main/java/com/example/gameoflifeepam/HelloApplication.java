package com.example.gameoflifeepam;

import com.example.gameoflifeepam.controller.SimulationController;
import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.view.JavaFXview;
import com.example.gameoflifeepam.view.MainViewConsole;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {




        JavaFXview javaFXview = new JavaFXview();
        //SimulationController simulationController = new SimulationController(new Grid(10,10), 1, 1000, javaFXview );
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(javaFXview, 320, 240);
        stage.setScene(scene);
        stage.show();

        //simulationController.run();
        javaFXview.showNext();


    }

    public static void main(String[] args) {
        launch();
    }
}