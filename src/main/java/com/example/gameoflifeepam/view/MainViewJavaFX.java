package com.example.gameoflifeepam.view;

import com.example.gameoflifeepam.controller.SimulationController;
import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.model.GridService;
import com.example.gameoflifeepam.model.GridServiceInterface;
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

    public static final int WINDOW_SIZE_X = 700, WINDOW_SIZE_Y = 730;
    static final int TIME_OF_FRAME = 1000;
    static final int EPOCHS_ON_START = 10;
    static final Color BACKGROUND_COLOR = Color.LIGHTGRAY;
    static final Color CELL_COLOR = Color.LIGHTSALMON;
    static final Color LINE_COLOR = Color.GREY;
    static final float LINE_WIDTH = 0.01f;
    static final String START_BUTTON_TEXT = "Start";
    static final String START_BUTTON_TEXT_RUNNING = "Running";

    private final Canvas canvas;
    private final MainViewToolbar mainViewToolbar;
    private final Affine affine;
    private final GridServiceInterface gridService = new GridService();
    private final int gridSizeX, gridSizeY, epochs;

    private Grid grid;

    public MainViewJavaFX(int epochs, int sizeX, int sizeY) {
        this.epochs = epochs;
        this.gridSizeX = sizeX;
        this.gridSizeY = sizeY;
        this.canvas = new Canvas(WINDOW_SIZE_X, WINDOW_SIZE_Y);
        this.grid = new Grid(gridSizeX, gridSizeY);
        this.mainViewToolbar = new MainViewToolbar();

        this.affine = new Affine();
        this.affine.appendScale(WINDOW_SIZE_X / (float) gridSizeX, WINDOW_SIZE_Y / (float) gridSizeY);

        this.mainViewToolbar.getClear().setOnAction(actionEvent -> clearButtonAction());
        this.mainViewToolbar.getStart().setOnAction(actionEvent -> startButtonAction());
        this.canvas.setOnMouseClicked(this::handleDraw);

        this.getChildren().addAll(this.canvas, mainViewToolbar);

        if (gridService.isEmpty(this.grid)) {
            gridService.fillByRandom(this.grid);
        }
        drawGrid(this.grid);
    }

    private void makeButtonsState(boolean state, Button... buttons) {
        for (Button button :
                buttons) {
            button.setDisable(state);
        }
    }

    private void handleDraw(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();

        try {
            Point2D cellCoordinate = this.affine.inverseTransform(mouseX, mouseY);
            int simX = (int) cellCoordinate.getX();
            int simY = (int) cellCoordinate.getY();

            grid.getCells()[simY][simX].setAlive(!this.grid.getCells()[simY][simX].isAlive());
            this.drawCell(this.grid, simX, simY);

        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    private void clearButtonAction() {
        gridService.clearGrid(this.grid);
        this.drawGrid(this.grid);
    }

    private void startButtonAction() {
        SimulationController simulator = new SimulationController(this.grid, epochs, TIME_OF_FRAME, this);
        Thread simulatorThread = new Thread(simulator);
        simulatorThread.start();

        this.mainViewToolbar.getStart().setText(START_BUTTON_TEXT_RUNNING);
        makeButtonsState(true, this.mainViewToolbar.getStart(), this.mainViewToolbar.getClear());

        if (!simulatorThread.isAlive()) {
            this.mainViewToolbar.getStart().setText(START_BUTTON_TEXT);
            makeButtonsState(false, this.mainViewToolbar.getStart(), this.mainViewToolbar.getClear());
        }
        this.mainViewToolbar.getStop().setOnAction(actionEvent1 -> {
            simulator.stopAll();
            this.mainViewToolbar.getStart().setText(START_BUTTON_TEXT);
            makeButtonsState(false, this.mainViewToolbar.getStart(), this.mainViewToolbar.getClear());
        });
    }

    @Override
    public void drawGrid(Grid grid) {
        this.grid = grid;
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
            g.strokeLine(0, y, gridSizeY, y);
        }
        for (int x = 0; x < this.grid.getSizeX(); x++) {
            g.strokeLine(x, 0, x, gridSizeX);
        }
    }

    @Override
    public void drawCell(Grid grid, int x, int y) {
        this.grid = grid;
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);
        g.setFill(grid.getCells()[y][x].isAlive() ? CELL_COLOR : BACKGROUND_COLOR);
        g.fillRect(x, y, 1, 1);
    }
}
