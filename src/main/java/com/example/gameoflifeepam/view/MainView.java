package com.example.gameoflifeepam.view;

import com.example.gameoflifeepam.model.Grid;

public interface MainView {
    /**
     * Render the entire grid
     * @param grid grid for rendering
     */
    void drawGrid(Grid grid);

    /**
     * Draw one cell from the grid
     * @param grid grid where we find cell
     * @param x x coordinate
     * @param y y coordinate
     */
    void drawCell(Grid grid, int x, int y);

    /**
     * Interface for communicating simulator and view
     * @param grid grid for rendering
     */
    void updateGridFromSim(Grid grid);

    /**
     * Stop simulation
     */
    void endingOfSimulation();

}
