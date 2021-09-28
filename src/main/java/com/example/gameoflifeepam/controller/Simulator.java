package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Cell;
import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.view.MainView;

public class Simulator implements Runnable {
    private final Grid grid;
    private final MainView mainView;
    private final static Object locker = new Object();
    private int timeOfFrame;
    private Cell cell;
    private int x,y;
    SimulationAction simulationAction;

    public Simulator(Grid grid, Cell cell, int xCell, int yCell, MainView mainView, SimulationAction simulationAction, int timeOfFrame) {
        this.grid = grid;
        this.mainView = mainView;
        this.simulationAction = simulationAction;
        this.timeOfFrame = timeOfFrame;
        this.cell = cell;
        this.x = xCell;
        this.y = yCell;
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
//        for (int y = 0; y < grid.getSizeY(); y++) {
//            for (int x = 0; x < grid.getSizeX(); x++) {
        int neighbors = grid.checkNeighborsOfCell(x, y);
        if (neighbors == 3) {
            cell.setAlive(true);
        }
    }

//        }
//    }

    private void killCells() {
//        for (int y = 0; y < grid.getSizeY(); y++) {
//            for (int x = 0; x < grid.getSizeX(); x++) {
        int neighbors = grid.checkNeighborsOfCell(x, y);
        if (neighbors < 2) {
            cell.setAlive(false);
        } else if (neighbors > 3) {
            cell.setAlive(false);
        }

    }
//        }
//    }

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
