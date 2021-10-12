package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.model.GridService;
import com.example.gameoflifeepam.model.GridServiceInterface;
import com.example.gameoflifeepam.view.MainView;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Killer implements Runnable {
    private final Grid grid;
    private final GridServiceInterface gridService = new GridService();
    private final int epochs;
    private final CyclicBarrier barrier;
    private final MainView mainView;


    public Killer(Grid grid, int epochs, CyclicBarrier barrier, MainView mainView) {
        this.grid = grid;
        this.epochs = epochs;
        this.barrier = barrier;
        this.mainView = mainView;
    }

    @Override
    public void run() {
        for (int i = 0; i < epochs; i++) {
            Grid prevGrid = new Grid(grid);
            for (int y = 0; y < grid.getSizeY(); y++) {
                for (int x = 0; x < grid.getSizeX(); x++) {
                    int neighbors = gridService.checkNeighbors(prevGrid, x, y);
                    if (neighbors < 2) {
                        grid.getCells()[y][x].setAlive(false);
                    } else if (neighbors > 3) {
                        grid.getCells()[y][x].setAlive(false);
                    }
                }
            }
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                break;
            }

        }
        mainView.endingOfSimulation();
    }
}
