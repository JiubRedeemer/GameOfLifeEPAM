package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.view.MainView;

public class Simulator implements Runnable {
    private final Grid grid;
    private final MainView mainView;
    private final static Object locker = new Object();
    private int timeOfFrame;

    SimulationAction simulationAction;

    public Simulator(Grid grid, MainView mainView, SimulationAction simulationAction, int timeOfFrame) {
        this.grid = grid;
        this.mainView = mainView;
        this.simulationAction = simulationAction;
        this.timeOfFrame = timeOfFrame;
    }

    @Override
    public synchronized void run() {
        synchronized (locker) {
            switch (simulationAction) {
                case CREATE -> {
                    createCells();
                }
                case KILL -> {
                    killCells();
                }
                case SHOW -> {
                    showCells();
                }
            }
        }
    }


    private void createCells() {
        for (int y = 0; y < grid.getSizeY(); y++) {
            for (int x = 0; x < grid.getSizeX(); x++) {
                int neighbors = grid.checkNeighborsOfCell(x, y);
                if (neighbors == 3) {
                    grid.getCells()[y][x].setAlive(true);
                }
            }

        }
    }

    private void killCells() {
        for (int y = 0; y < grid.getSizeY(); y++) {
            for (int x = 0; x < grid.getSizeX(); x++) {
                int neighbors = grid.checkNeighborsOfCell(x, y);
                if (neighbors < 2) {
                    grid.getCells()[y][x].setAlive(false);
                } else if (neighbors > 3) {
                    grid.getCells()[y][x].setAlive(false);
                }

            }
        }
    }

    private void showCells() {
        try {
            mainView.updateGrid(grid);
            mainView.showNext();
            Thread.sleep(timeOfFrame);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
