package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Cell;
import com.example.gameoflifeepam.model.Grid;

public class Killer implements Runnable {
    private final Grid grid;
    private int epochs;

    public Killer(Grid grid, int epochs) {
        this.grid = grid;
        this.epochs = epochs;

    }

    @Override
    public void run() {
        for (int i = 0; i < epochs; i++) {
            for (int y = 0; y < grid.getSizeY(); y++) {
                for (int x = 0; x < grid.getSizeX(); x++) {
                    int neighbors = grid.checkNeighborsOfCell(x, y);
                    if (neighbors < 2) {
                        grid.getCells()[y][x].setAlive(false);
                    } else if (neighbors > 4) {
                        grid.getCells()[y][x].setAlive(false);
                    }

                }
            }
        }
    }
}
