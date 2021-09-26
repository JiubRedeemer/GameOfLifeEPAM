package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.view.MainView;
import com.example.gameoflifeepam.view.MainViewConsole;

public class Viewer implements Runnable {
    private final int TIME_OF_FRAME = 1000;

    private final Grid grid;
    private final MainView mainView;
    private final int epochs;

    public Viewer(Grid grid, int epochs) {
        this.grid = grid;
        this.epochs = epochs;

        mainView = new MainViewConsole(); // Here u can change view output
    }

    @Override
    public void run() {
        showEpochs();
    }

    private void showEpochs() {
        for (int i = 0; i < epochs; i++) {
            mainView.showNext(grid);
            try {
                Thread.sleep(TIME_OF_FRAME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
