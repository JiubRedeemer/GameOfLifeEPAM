package com.example.gameoflifeepam.view;

import com.example.gameoflifeepam.controller.SimulationController;
import com.example.gameoflifeepam.model.Grid;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class MainViewJavaFX extends VBox implements MainView {
    private Canvas canvas = new Canvas(400, 400);
    private final Toolbar toolbar;
    private final Affine affine;
    private final int sizeX = 20;
    private final int sizeY = 20;

    private Grid grid = new Grid(sizeX, sizeY);

    public MainViewJavaFX() {
        SimulationController simulationController = new SimulationController(this.grid, 1, 0, this);
        simulationController.run();
        this.canvas = new Canvas(400, 400);
        this.toolbar = new Toolbar();
        this.toolbar.clear.setOnAction(actionEvent -> {
            this.grid.cleanGrid();
            this.updateGrid(this.grid);
            this.draw();
        });
//        this.toolbar.step.setOnAction(actionEvent -> {
//            SimulationController stepSimulatorController = new SimulationController(this.grid, 1, 1000, this);
//            new Thread(stepSimulatorController).start();
//        });
        this.toolbar.start.setOnAction(actionEvent -> {
            SimulationController simulationController1 = new SimulationController(this.grid, 10, 1000, this);
            Thread thread = new Thread(simulationController1);
            thread.start();
            this.toolbar.start.setText("Running");
            this.toolbar.start.setDisable(true);
            this.toolbar.clear.setDisable(true);
            if (!thread.isAlive()) {
                this.toolbar.start.setText("Start");
                this.toolbar.start.setDisable(false);
                this.toolbar.clear.setDisable(false);
            }
            this.toolbar.stop.setOnAction(actionEvent1 -> {
                thread.interrupt();
                this.toolbar.start.setText("Start");
                this.toolbar.start.setDisable(false);
                this.toolbar.clear.setDisable(false);
            });
        });
        this.canvas.setOnMouseClicked(this::handleDraw);
        this.getChildren().addAll(this.canvas, toolbar);
        this.affine = new Affine();
        this.affine.appendScale(400 / (float)sizeX, 400 / (float)sizeY);
    }

    private void handleDraw(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();

        //System.out.println(mouseX + "," + mouseY);

        try {
            Point2D cellCoordinate = this.affine.inverseTransform(mouseX, mouseY);
            int simX = (int) cellCoordinate.getX();
            int simY = (int) cellCoordinate.getY();

            grid.getCells()[simY][simX].setAlive(!this.grid.getCells()[simY][simX].isAlive());

            this.updateGrid(this.grid);
            this.draw();
           // System.out.println(cellCoordinate);
            //System.out.println(grid.checkNeighborsOfCell(simX, simY));

        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }

    }

    public void draw() {

        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);
        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, 450, 450);
        for (int y = 0; y < this.grid.getSizeY(); y++) {
            for (int x = 0; x < this.grid.getSizeX(); x++) {
                if (this.grid.getCells()[y][x].isAlive()) {
                    g.setFill(Color.BLACK);
                    g.fillRect(x, y, 1, 1);
                }
            }
        }

        g.setStroke(Color.GREY);
        g.setLineWidth(0.05f);
        for (int y = 0; y < this.grid.getSizeY(); y++) {
            g.strokeLine(0, y, sizeY, y);
        }
        for (int x = 0; x < this.grid.getSizeX(); x++) {
            g.strokeLine(x, 0, x, sizeX);
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
