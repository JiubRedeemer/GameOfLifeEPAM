package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.model.GridServiceImpl;
import com.example.gameoflifeepam.model.GridService;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Creator implements Runnable {
    private final Grid grid;
    private final GridService gridService = new GridServiceImpl();
    private final int epochs;
    private final CyclicBarrier barrier;


    public Creator(Grid grid, int epochs, CyclicBarrier barrier) {
        this.grid = grid;
        this.epochs = epochs;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        for (int i = 0; i < epochs; i++) {
            Grid prevGrid = new Grid(grid);
            for (int y = 0; y < grid.getSizeY(); y++) {
                for (int x = 0; x < grid.getSizeX(); x++) {
                    int neighbors = gridService.checkNeighbors(prevGrid, x, y);
                    if (neighbors == 3) {
                        grid.getCells()[y][x].setAlive(true);
                    }
                }
            }
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                break;
            }
        }
    }


}
