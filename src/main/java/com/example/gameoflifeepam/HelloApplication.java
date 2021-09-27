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

      //  SimulationController simulationController = new SimulationController(new Grid(10,5), 5, new MainViewConsole());
       // simulationController.run();


        JavaFXview javaFXview = new JavaFXview();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(javaFXview, 320, 240);
        stage.setScene(scene);
        stage.show();

        javaFXview.draw();
    }

    public static void main(String[] args) {
        launch();
    }
}