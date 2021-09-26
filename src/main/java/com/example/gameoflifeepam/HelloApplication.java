package com.example.gameoflifeepam;

import com.example.gameoflifeepam.controller.SimulationController;
import com.example.gameoflifeepam.model.Grid;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        SimulationController simulationController = new SimulationController(new Grid(10,5), 5);
        Thread simulationControllerThread = new Thread(simulationController);
        simulationControllerThread.start();



        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!!!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}