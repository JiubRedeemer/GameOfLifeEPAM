package com.example.gameoflifeepam.view;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartViewJavaFX extends VBox {
    StartViewToolbar startViewToolbar = new StartViewToolbar();

    public StartViewJavaFX() {
        startViewToolbar.getSetParamsButton().setOnAction(actionEvent -> startButtonAction());
        this.getChildren().addAll(startViewToolbar);
    }

    private void startButtonAction() {
        try {
            int epochs = Integer.parseInt(startViewToolbar.getEpochsField().getText());

            int sizeX = Integer.parseInt(startViewToolbar.getSizeGridXField().getText());

            int sizeY = Integer.parseInt(startViewToolbar.getSizeGridYField().getText());
            int timeOfFrame = Integer.parseInt(startViewToolbar.getTimeOfFrameField().getText());

            Stage stage = new Stage();
            MainViewJavaFX mainViewJavaFX = new MainViewJavaFX(epochs,timeOfFrame, sizeX, sizeY);
            Scene scene = new Scene(mainViewJavaFX, 700, 760);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch (NumberFormatException ex) {
            startViewToolbar.getErrorLabel().setText("Error");
        }

    }


}
