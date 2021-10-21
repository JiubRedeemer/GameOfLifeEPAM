package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.view.MainView;


public class Renderer implements Runnable {

    private final Grid grid;
    private final MainView mainView;
    private final int timeOfFrame;

    public Renderer(Grid grid, MainView mainView, int timeOfFrame) {
        this.grid = grid;
        this.mainView = mainView;
        this.timeOfFrame = timeOfFrame;
    }

    @Override
    public void run() {
        try {
            mainView.updateGridFromSim(grid);
            Thread.sleep(timeOfFrame);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
