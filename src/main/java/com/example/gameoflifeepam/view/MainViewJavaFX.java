package com.example.gameoflifeepam.view;

import com.example.gameoflifeepam.controller.SimulationController;
import com.example.gameoflifeepam.model.Grid;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class MainViewJavaFX extends VBox implements MainView {

    static final int GRID_SIZE_X = 30, GRID_SIZE_Y = 30;
    static final int TIME_OF_FRAME = 1000;
    static final int EPOCHS_ON_START = 10;
    public static final int WINDOW_SIZE_X = 700, WINDOW_SIZE_Y = 730;
    static final Color BACKGROUND_COLOR = Color.LIGHTGRAY;
    static final Color CELL_COLOR = Color.LIGHTSALMON;
    static final Color LINE_COLOR = Color.GREY;
    static final float LINE_WIDTH = 0.05f;
    static final String START_BUTTON_TEXT = "Start";
    static final String START_BUTTON_TEXT_RUNNING = "Running";

    private final Canvas canvas;
    private final Toolbar toolbar;
    private final Affine affine;

    private Grid grid;

    public MainViewJavaFX() {
        this.canvas = new Canvas(WINDOW_SIZE_X, WINDOW_SIZE_Y);
        this.grid = new Grid(GRID_SIZE_X, GRID_SIZE_Y);
        this.toolbar = new Toolbar();

        SimulationController simulationController = new SimulationController(this.grid, 1, 1, this);
        simulationController.run();

        this.toolbar.clear.setOnAction(actionEvent -> {
            this.grid.cleanGrid();
            this.updateGrid(this.grid);
        });

        this.toolbar.start.setOnAction(actionEvent -> {
            SimulationController simulationController1 = new SimulationController(this.grid, EPOCHS_ON_START, TIME_OF_FRAME, this);
            Thread thread = new Thread(simulationController1);
            thread.start();
            this.toolbar.start.setText(START_BUTTON_TEXT_RUNNING);
            this.toolbar.start.setDisable(true);
            this.toolbar.clear.setDisable(true);
            if (!thread.isAlive()) {
                this.toolbar.start.setText(START_BUTTON_TEXT);
                this.toolbar.start.setDisable(false);
                this.toolbar.clear.setDisable(false);
            }
            this.toolbar.stop.setOnAction(actionEvent1 -> {
                thread.interrupt();
                this.toolbar.start.setText(START_BUTTON_TEXT);
                this.toolbar.start.setDisable(false);
                this.toolbar.clear.setDisable(false);
            });
        });
        this.canvas.setOnMouseClicked(this::handleDraw);
        this.getChildren().addAll(this.canvas, toolbar);
        this.affine = new Affine();
        this.affine.appendScale(WINDOW_SIZE_X / (float) GRID_SIZE_X, WINDOW_SIZE_Y / (float) GRID_SIZE_Y);
        draw();
    }

    private void handleDraw(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();

        try {
            Point2D cellCoordinate = this.affine.inverseTransform(mouseX, mouseY);
            int simX = (int) cellCoordinate.getX();
            int simY = (int) cellCoordinate.getY();

            grid.getCells()[simY][simX].setAlive(!this.grid.getCells()[simY][simX].isAlive());

            this.updateGrid(this.grid);
            // TODO: rewrite update algo

        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }

    }

    public void draw() {

        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);
        g.setFill(BACKGROUND_COLOR);
        g.fillRect(0, 0, WINDOW_SIZE_X, WINDOW_SIZE_Y);
        for (int y = 0; y < this.grid.getSizeY(); y++) {
            for (int x = 0; x < this.grid.getSizeX(); x++) {
                if (this.grid.getCells()[y][x].isAlive()) {
                    g.setFill(CELL_COLOR);
                    g.fillRect(x, y, 1, 1);
                }
            }
        }

        g.setStroke(LINE_COLOR);
        g.setLineWidth(LINE_WIDTH);
        for (int y = 0; y < this.grid.getSizeY(); y++) {
            g.strokeLine(0, y, GRID_SIZE_Y, y);
        }
        for (int x = 0; x < this.grid.getSizeX(); x++) {
            g.strokeLine(x, 0, x, GRID_SIZE_X);
        }
    }

    @Override
    public void updateGrid(Grid grid) {
        this.grid = grid;
        draw();
    }

}
