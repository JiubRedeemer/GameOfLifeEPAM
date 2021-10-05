package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.model.GridService;
import com.example.gameoflifeepam.model.GridServiceInterface;
import com.example.gameoflifeepam.view.MainView;

import java.util.concurrent.CyclicBarrier;

public class SimulationController implements Runnable {

    private final Grid grid;
    private final GridServiceInterface gridService = new GridService();

    private final Renderer renderer;
    private final Creator creator;
    private final Killer killer;

    private final CyclicBarrier barrier;
    private final Thread creatorThread;
    private final Thread killerThread;

    public SimulationController(Grid grid, int epochs, int timeOfFrame, MainView mainView) {
        this.grid = grid;
        Grid prevGrid = this.grid;
        if (!gridService.isEmpty(this.grid)) {
            prevGrid = new Grid(grid);
        }

        renderer = new Renderer(grid, mainView, timeOfFrame);
        barrier = new CyclicBarrier(2, renderer);
        creator = new Creator(grid, prevGrid, epochs, barrier);
        killer = new Killer(grid, prevGrid, epochs, barrier);

        creatorThread = new Thread(creator);
        killerThread = new Thread(killer);

    }

    public void run() {
        try {
            control();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void control() throws InterruptedException {
        killerThread.start();
        creatorThread.start();
    }
}
