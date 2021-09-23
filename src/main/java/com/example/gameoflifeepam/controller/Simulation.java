package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Cell;
import com.example.gameoflifeepam.model.Grid;

import java.util.Random;

public class Simulation {
    private final Grid grid;
    private int epochNumber = 0;
    private final int epochs;

    public Simulation(Grid grid, int epochs) {
        this.grid = grid;
        this.epochs = epochs;
    }

    public void fillGridByRandom() {
        Random random = new Random();
        for (int y = 0; y < grid.getSizeY(); y++) {
            for (int x = 0; x < grid.getSizeX(); x++) {
                grid.getCells()[y][x] = new Cell(random.nextBoolean());
            }
        }
    }

    public Grid getGrid() {
        return grid;
    }

    public Grid showNextEpoch() {
        if (epochNumber == 0) {
            epochNumber++;
            return grid;
        } else if (epochNumber < epochs) {
            epochNumber++;
            simulateEpoch();
            return grid;
        } else {
            return grid;
        }
    }

    private void simulateEpoch() {
        for (int y = 0; y < grid.getSizeY(); y++) {
            for (int x = 0; x < grid.getSizeX(); x++) {
                int neighbors = grid.checkNeighborsOfCell(x, y);
                if (neighbors < 2) {
                    grid.getCells()[y][x].setAlive(false);
                } else if (neighbors > 4) {
                    grid.getCells()[y][x].setAlive(false);
                } else if (neighbors == 3) {
                    grid.getCells()[y][x].setAlive(true);
                }
            }
        }
    }
}
