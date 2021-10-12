package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.model.History;
import com.example.gameoflifeepam.model.HistoryInterface;
import com.example.gameoflifeepam.view.MainView;

import java.util.concurrent.CyclicBarrier;

public class SimulationController {

    private final Thread creatorThread;
    private final Thread killerThread;

    //private final History history = new History();

    public SimulationController(Grid grid, int epochs, int timeOfFrame, MainView mainView) {

        Renderer renderer = new Renderer(grid, mainView, timeOfFrame);
        CyclicBarrier barrier = new CyclicBarrier(2, renderer);
        Creator creator = new Creator(grid, epochs, barrier);
        Killer killer = new Killer(grid, epochs, barrier, mainView);

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
    }

 //   public History getHistory() {
  //      return history;
  //  }
}
