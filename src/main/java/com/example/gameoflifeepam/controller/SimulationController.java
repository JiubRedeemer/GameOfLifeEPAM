package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Cell;
import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.view.MainView;

import java.util.Random;

public class SimulationController implements Runnable {
    private final Grid grid;
    private Grid pregrid;
    private final int epochs;
    private final MainView mainView;
    private int timeOfFrame;

    Runnable creator = new Runnable() {
        @Override
        public void run() {
            for (int y = 0; y < grid.getSizeY(); y++) {
                for (int x = 0; x < grid.getSizeX(); x++) {
                    int neighbors = pregrid.checkNeighborsOfCell(x, y);
                    if (neighbors == 3) {
                        grid.getCells()[y][x].setAlive(true);
                    }
                }
            }
        }
    };

    Runnable killer = new Runnable() {
        @Override
        public void run() {
            for (int y = 0; y < grid.getSizeY(); y++) {
                for (int x = 0; x < grid.getSizeX(); x++) {
                    int neighbors = pregrid.checkNeighborsOfCell(x, y);
                    if (neighbors < 2) {
                        grid.getCells()[y][x].setAlive(false);
                    } else if (neighbors > 3) {
                        grid.getCells()[y][x].setAlive(false);
                    }
                }
            }
        }
    };

    Runnable shower = new Runnable() {
        @Override
        public void run() {
            mainView.updateGrid(grid);
        }
    };

    public SimulationController(Grid grid, int epochs, int timeOfFrame, MainView mainView) {
        this.grid = grid;
        this.pregrid = grid;
        if (!this.grid.isEmpty()) {
            this.pregrid = new Grid(grid);
        }
        this.epochs = epochs;
        this.mainView = mainView;
        this.timeOfFrame = timeOfFrame;
    }

    public void run() {
        control();

    }

    public void control() {
        if (grid.isEmpty()) {
            fillGridByRandom();
        }

        for (int i = 0; i < epochs; i++) {
            if (Thread.currentThread().isInterrupted()) break;
            try {
                Thread creatorThread = new Thread(creator);
                creatorThread.setName("CREATOR");

                creatorThread.start();
                // creatorThread.join();
                Thread killerThread = new Thread(killer);
                creatorThread.setName("KILLER");

                killerThread.start();
                // killerThread.join();
                Thread showerThread = new Thread(shower);
                creatorThread.setName("SHOWER");

                showerThread.start();
                showerThread.join();
                this.pregrid = new Grid(this.grid);

                Thread.sleep(timeOfFrame);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public void fillGridByRandom() {
        Random random = new Random();
        for (int y = 0; y < grid.getSizeY(); y++) {
            for (int x = 0; x < grid.getSizeX(); x++) {
                grid.getCells()[y][x] = new Cell(random.nextBoolean());
            }
        }
    }
}
