package com.example.gameoflifeepam.view;

import com.example.gameoflifeepam.model.Grid;

public class MainViewConsole implements MainView {


    @Override
    public void showNext(Grid grid) {
        for (int x = 0; x < grid.getSizeY(); x++) {
            for (int y = 0; y < grid.getSizeX(); y++) {
                System.out.print(grid.getCells()[x][y].isAlive() ? "1" : "0");
            }
            System.out.println();
        }
    }
}

