package com.example.gameoflifeepam.view;

import com.example.gameoflifeepam.model.Grid;

public interface MainView {

    void drawGrid(Grid grid);

    void drawCell(Grid grid, int x, int y);

    void updateGridFromSim(Grid grid);

}
