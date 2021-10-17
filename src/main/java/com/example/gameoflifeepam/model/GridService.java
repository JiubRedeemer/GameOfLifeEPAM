package com.example.gameoflifeepam.model;

public interface GridService {
    /**
     * Check neighbors for cell in grid
     * @param grid grid in which neighbors are checked
     * @param x x coordinate
     * @param y y coordinate
     * @return the number of neighbors in the cell
     */
    int checkNeighbors(Grid grid, int x, int y);

    /**
     * Fill grid by random
     * @param grid grid that will be randomly filled
     */
    void fillByRandom(Grid grid);

    /**
     * Check grid for empty state
     * @param grid grid to be checked for emptiness
     * @return empty state
     */
    boolean isEmpty(Grid grid);

    /**
     * Make all cells in grid are dead
     * @param grid grid in which cells will be killed
     */
    void killAllInGrid(Grid grid);
}
