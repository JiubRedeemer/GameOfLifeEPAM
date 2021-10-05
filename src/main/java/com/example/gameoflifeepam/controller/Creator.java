package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.model.GridService;
import com.example.gameoflifeepam.model.GridServiceInterface;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Creator implements Runnable {
    private final Grid grid;
    private final GridServiceInterface gridService = new GridService();
    private final int epochs;
    private final CyclicBarrier barrier;

    private Grid prevGrid;


    public Creator(Grid grid, Grid prevGrid, int epochs, CyclicBarrier barrier) {
        this.grid = grid;
        this.prevGrid = prevGrid;
        this.epochs = epochs;
        this.barrier = barrier;

    }

    @Override
    public void run() {
        for (int i = 0; i < epochs; i++) {
            for (int y = 0; y < grid.getSizeY(); y++) {
                for (int x = 0; x < grid.getSizeX(); x++) {
                    int neighbors = gridService.checkNeighbors(prevGrid, x, y);
                    if (neighbors == 3) {
                        grid.getCells()[y][x].setAlive(true);
                    }
                }
            }
            synchronized (prevGrid) {
                prevGrid = new Grid(grid);
            }
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
