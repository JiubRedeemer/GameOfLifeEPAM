package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Cell;
import com.example.gameoflifeepam.model.Grid;

import java.util.Random;

public class SimulationController implements Runnable {
    private int maxEpochs, currentEpoch;

    Grid grid;
    Killer killer;
    Creator creator;
    Viewer viewer;

    Thread killerThread;
    Thread creatorThread;
    Thread viewerThread;

    public SimulationController(Grid grid, int epochs) {
        this.grid = grid;
        this.maxEpochs = epochs;
        killer = new Killer(grid);
        creator = new Creator(grid);
        viewer = new Viewer(grid, epochs);
        killerThread = new Thread(killer);
        creatorThread = new Thread(creator);
        viewerThread = new Thread(viewer);
    }

    @Override
    public void run() {
        control();
    }

    public void control() {
        if (grid.isEmpty()) {
            fillGridByRandom();
        }

        while (currentEpoch < maxEpochs) {
            currentEpoch++;

            killerThread.setPriority(1);
            creatorThread.setPriority(2);
            viewerThread.setPriority(3);

            killerThread.start();
            creatorThread.start();
            viewerThread.start();
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
