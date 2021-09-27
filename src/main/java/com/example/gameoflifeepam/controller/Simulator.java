package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.view.MainView;

public class Simulator implements Runnable {
    private Thread t;
    private final int epochs;
    private final Grid grid;
    private final MainView mainView;
    private final static Object locker = new Object();

    SimulationAction simulationAction;

    public Simulator(int epochs, Grid grid, MainView mainView, SimulationAction simulationAction) {
        this.epochs = epochs;
        this.grid = grid;
        this.mainView = mainView;
        this.simulationAction = simulationAction;
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
       // for (int i = 0; i < epochs; i++) {
            for (int y = 0; y < grid.getSizeY(); y++) {
                for (int x = 0; x < grid.getSizeX(); x++) {
                    int neighbors = grid.checkNeighborsOfCell(x, y);
                    if (neighbors == 3) {
                        grid.getCells()[y][x].setAlive(true);
                    }
                }
           // }
        }
    }

    private void killCells() {
       // for (int i = 0; i < epochs; i++) {
            for (int y = 0; y < grid.getSizeY(); y++) {
                for (int x = 0; x < grid.getSizeX(); x++) {
                    int neighbors = grid.checkNeighborsOfCell(x, y);
                    if (neighbors < 2) {
                        grid.getCells()[y][x].setAlive(false);
                    } else if (neighbors > 3) {
                        grid.getCells()[y][x].setAlive(false);
                    }

                }
           // }
        }
    }

    private void showCells() {
        //for (int i = 0; i < epochs; i++) {
            mainView.showNext(grid);
//            try {
//                Thread.sleep(TIME_OF_FRAME);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

       // }
    }

//    public void start() {
//
//        if (t == null) {
//            t = new Thread(this);
//            t.setName(simulationAction.toString());
//            switch (simulationAction) {
//                case CREATE -> t.setPriority(8);
//                case KILL -> t.setPriority(7);
//                case SHOW -> t.setPriority(6);
//            }
//            t.start();
//        }
//    }
}
