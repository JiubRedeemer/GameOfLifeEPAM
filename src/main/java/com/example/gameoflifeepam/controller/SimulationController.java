package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.view.MainView;

import java.util.concurrent.CyclicBarrier;

public class SimulationController implements Runnable {

    private final Thread creatorThread;
    private final Thread killerThread;
    private CyclicBarrier barrier;

    public SimulationController(Grid grid, int epochs, int timeOfFrame, MainView mainView) {

        Renderer renderer = new Renderer(grid, mainView, timeOfFrame);
        barrier = new CyclicBarrier(2, renderer);
        Creator creator = new Creator(grid, epochs, barrier);
        Killer killer = new Killer(grid, epochs, barrier);

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

    public void stopAll() {
        killerThread.interrupt();
        creatorThread.interrupt();
        Thread.currentThread().interrupt();
    }
}
