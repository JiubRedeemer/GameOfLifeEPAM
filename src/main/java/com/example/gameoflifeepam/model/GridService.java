package com.example.gameoflifeepam.model;

import java.util.Random;

public class GridService implements GridServiceInterface {

    @Override
    public int checkNeighbors(Grid grid, int x, int y) {
        Cell[][] cells = grid.getCells();
        int sizeX = grid.getSizeX();
        int sizeY = grid.getSizeY();

        int neighbors = 0;

        // Check neighbors in forward directions
        if (y > 0 && cells[y - 1][x].isAlive()) {
            neighbors++;
        }
        if (y < sizeY - 1) {
            if (cells[y + 1][x].isAlive()) {
                neighbors++;
            }
        }
        if (x > 0 && cells[y][x - 1].isAlive()) {
            neighbors++;
        }
        if (x < sizeX - 1 && cells[y][x + 1].isAlive()) {
            neighbors++;
        }

        // Check diagonals in zeros
        if (x > 0 && y > 0 && cells[y - 1][x - 1].isAlive()) {
            neighbors++;
        }
        if (x < sizeX - 1 && y < sizeY - 1 && cells[y + 1][x + 1].isAlive()) {
            neighbors++;
        }
        if (x > 0 && y < sizeY - 1 && cells[y + 1][x - 1].isAlive()) {
            neighbors++;
        }
        if (x < sizeX - 1 && y > 0 && cells[y - 1][x + 1].isAlive()) {
            neighbors++;
        }
        return neighbors;
    }

    @Override
    public void fillByRandom(Grid grid) {
        Random random = new Random();
        for (int y = 0; y < grid.getSizeY(); y++) {
            for (int x = 0; x < grid.getSizeX(); x++) {
                grid.getCells()[y][x] = new Cell(random.nextBoolean());
            }
        }
    }

    @Override
    public boolean isEmpty(Grid grid) {
        Cell[][] cells = grid.getCells();
        for (Cell[] array : cells) {
            for (Cell val : array) {
                if (val != null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void clearGrid(Grid grid) {
        Cell[][] cells = grid.getCells();
        int sizeX = grid.getSizeX();
        int sizeY = grid.getSizeY();
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                cells[y][x].setAlive(false);
            }
        }
    }
}
