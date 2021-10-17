package com.example.gameoflifeepam.view;

import com.example.gameoflifeepam.controller.SimulationController;
import com.example.gameoflifeepam.model.*;
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
    static final Color BACKGROUND_COLOR = Color.LIGHTGRAY;
    static final Color CELL_COLOR = Color.LIGHTSALMON;
    static final Color LINE_COLOR = Color.BLACK;
    static final float LINE_WIDTH = 0.01f;
    static final String START_BUTTON_TEXT = "Start";
    static final String START_BUTTON_TEXT_RUNNING = "Running";

    private final Canvas canvas;
    private final MainViewToolbar mainViewToolbar;
    private final Affine affine;
    private final GridService gridService = new GridServiceImpl();
    private final int timeOfFrame;
    private final int gridSizeX;
    private final int gridSizeY;
    private final int epochs;
    private final History history;

    private SimulationStatus simulationStatus;
    private Grid grid;
    private SimulationController simulator;
    private int currentStepInHistory = 0;
    private int maxCurrentStepInHistory = 0;

    public MainViewJavaFX(int epochs, int timeOfFrame, int sizeX, int sizeY) {
        this.history = new HistoryImpl();
        this.epochs = epochs;
        this.timeOfFrame = timeOfFrame;
        this.gridSizeX = sizeX;
        this.gridSizeY = sizeY;
        this.canvas = new Canvas(WINDOW_SIZE_X, WINDOW_SIZE_Y);
        this.grid = new Grid(gridSizeX, gridSizeY);
        this.mainViewToolbar = new MainViewToolbar();

        this.affine = new Affine();
        this.affine.appendScale(WINDOW_SIZE_X / (float) gridSizeX, WINDOW_SIZE_Y / (float) gridSizeY);

        this.mainViewToolbar.getClear().setOnAction(actionEvent -> clearButtonAction());
        this.mainViewToolbar.getStart().setOnAction(actionEvent -> startButtonAction());
        this.mainViewToolbar.getStepForward().setOnAction(actionEvent -> forwardAction());
        this.mainViewToolbar.getStepBack().setOnAction(actionEvent -> backAction());

        this.canvas.setOnMouseClicked(this::handleDraw);

        this.getChildren().addAll(this.canvas, mainViewToolbar);

        if (gridService.isEmpty(this.grid)) {
            gridService.fillByRandom(this.grid);
        }
        drawGrid(this.grid);
    }

    private void backAction() {
        decrementStep();
        grid = history.getHistory().get(currentStepInHistory);
        drawGrid(grid);
        System.out.println(currentStepInHistory + " " + maxCurrentStepInHistory);
    }

    private void forwardAction() {
        incrementStep();
        grid = history.getHistory().get(currentStepInHistory);
        drawGrid(grid);
        System.out.println(currentStepInHistory + " " + maxCurrentStepInHistory);
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
            Point2D cellCoordinate = affine.inverseTransform(mouseX, mouseY);
            int simX = (int) cellCoordinate.getX();
            int simY = (int) cellCoordinate.getY();

            grid.getCells()[simY][simX].setAlive(!grid.getCells()[simY][simX].isAlive());
            drawCell(grid, simX, simY);

        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    private void clearButtonAction() {
        gridService.killAllInGrid(grid);
        drawGrid(grid);
    }

    private void startButtonAction() {
        startSimulation(epochs);
        mainViewToolbar.getStart().setText(START_BUTTON_TEXT_RUNNING);
        makeButtonsState(true, mainViewToolbar.getStart(), mainViewToolbar.getClear());

        if (simulationStatus == SimulationStatus.STARTED) {
            mainViewToolbar.getStart().setText(START_BUTTON_TEXT);
            makeButtonsState(false, mainViewToolbar.getStart(), mainViewToolbar.getClear());
        }
        mainViewToolbar.getStop().setOnAction(actionEvent1 -> stopButtonAction());
    }

    private void startSimulation(int epochs) {
        simulator = new SimulationController(grid, epochs, timeOfFrame, this);
        simulator.run();
    }

    private void stopButtonAction() {
        simulator.stopAll();
        mainViewToolbar.getStart().setText(START_BUTTON_TEXT);
        simulationStatus = SimulationStatus.STOPPED;
        makeButtonsState(false, mainViewToolbar.getStart(), mainViewToolbar.getClear());
    }

    private void addStep() {
        if (maxCurrentStepInHistory < HistoryImpl.CAPACITY - 1) {
            maxCurrentStepInHistory++;
            currentStepInHistory++;
        } else {
            maxCurrentStepInHistory = HistoryImpl.CAPACITY - 1;
            currentStepInHistory = HistoryImpl.CAPACITY - 1;
        }
    }

    private void incrementStep() {
        if (currentStepInHistory < maxCurrentStepInHistory) {
            currentStepInHistory++;
        } else {
            currentStepInHistory = maxCurrentStepInHistory;
        }
    }

    private void decrementStep() {
        if (currentStepInHistory > 0) {
            currentStepInHistory--;
        } else {
            currentStepInHistory = 0;
        }
    }

    @Override
    public void drawGrid(Grid grid) {
        this.grid = grid;
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setTransform(affine);
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
    public void updateGridFromSim(Grid grid) {
        addStep();
        history.add(this.grid, this.currentStepInHistory);
        drawGrid(grid);
    }

    @Override
    public void endingOfSimulation() {
        stopButtonAction();
    }

    @Override
    public void drawCell(Grid grid, int x, int y) {
        this.grid = grid;
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setTransform(affine);
        g.setFill(grid.getCells()[y][x].isAlive() ? CELL_COLOR : BACKGROUND_COLOR);
        g.fillRect(x, y, 1, 1);
    }
}
