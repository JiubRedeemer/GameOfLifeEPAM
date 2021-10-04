package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.view.MainView;

public interface Simulation {
    void killCells(Grid grid, int epochs);

    void createCells(Grid grid, int epochs);

    void renderCells(Grid grid, MainView mainView, int epochs);

    void control();
}
