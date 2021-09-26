package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Grid;

public class Creator implements Runnable {
    private final Grid grid;

    public Creator(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void run() {
            for (int y = 0; y < grid.getSizeY(); y++) {
                for (int x = 0; x < grid.getSizeX(); x++) {
                    int neighbors = grid.checkNeighborsOfCell(x, y);
                    if (neighbors == 3) {
                        grid.getCells()[y][x].setAlive(true);
                    }

            }
        }
    }
}
