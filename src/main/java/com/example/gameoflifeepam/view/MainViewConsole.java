package com.example.gameoflifeepam.view;

import com.example.gameoflifeepam.controller.Simulation;
import com.example.gameoflifeepam.model.Grid;

public class MainViewConsole implements Drawable{
    private Simulation simulation;

    public MainViewConsole(Simulation simulation) {
        this.simulation = simulation;
        simulation.fillGridByRandom();
    }

    @Override
    public void showNext() {
        Grid grid = simulation.showNextEpoch();

        for (int x = 0; x < grid.getSizeY(); x++) {
            for (int y = 0; y < grid.getSizeX(); y++) {
                System.out.print(grid.getCells()[x][y].isAlive() ? "0" : "1");
            }
            System.out.println();
        }

    }
}

