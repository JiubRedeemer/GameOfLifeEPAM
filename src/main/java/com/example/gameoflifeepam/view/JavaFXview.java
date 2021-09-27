package com.example.gameoflifeepam.view;

import com.example.gameoflifeepam.controller.SimulationController;
import com.example.gameoflifeepam.model.Grid;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class JavaFXview extends VBox implements MainView {
    private Button stepButton;
    private Canvas canvas;
    private Affine affine;
    private Grid grid = new Grid(10, 10);

    public JavaFXview() {
        SimulationController simulationController = new SimulationController(grid, 1, 1000, this);
        simulationController.run();
        this.stepButton = new Button("step");
        this.stepButton.setOnAction(actionEvent -> {
            new SimulationController(grid, 1, 1000, this).run();

        });
        this.canvas = new Canvas(400, 400);
        this.getChildren().addAll(this.stepButton, this.canvas);
        this.affine = new Affine();
        this.affine.appendScale(400 / 10f, 400 / 10f);
    }

    public void draw() {

        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);
        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, 450, 450);
        g.setFill(Color.BLACK);
        for (int y = 0; y < this.grid.getSizeY(); y++) {
            for (int x = 0; x < this.grid.getSizeX(); x++) {
                if (this.grid.getCells()[y][x].isAlive()) {
                    g.fillRect(x, y, 1, 1);
                }
            }
        }

        g.setStroke(Color.GREY);
        g.setLineWidth(0.05f);
        for (int y = 0; y < this.grid.getSizeY(); y++) {
            g.strokeLine(0, y, 10, y);
        }
        for (int x = 0; x < this.grid.getSizeX(); x++) {
            g.strokeLine(x, 0, x, 10);
        }
    }

    @Override
    public void updateGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void showNext() {
        draw();
    }
}
