package com.example.gameoflifeepam.model;

public interface GridServiceInterface {
    int checkNeighbors(Grid grid, int x, int y); // Check neighbors for cell in grid

    void fillByRandom(Grid grid); // Fill grid by random

    boolean isEmpty(Grid grid); // Check grid for empty state

    void clearGrid(Grid grid); // Make all cells in grid dead
}
