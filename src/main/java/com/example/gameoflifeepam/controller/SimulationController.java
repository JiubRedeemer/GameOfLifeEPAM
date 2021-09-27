package com.example.gameoflifeepam.controller;

import com.example.gameoflifeepam.model.Cell;
import com.example.gameoflifeepam.model.Grid;
import com.example.gameoflifeepam.view.MainView;
import com.example.gameoflifeepam.view.MainViewConsole;

import java.util.Random;

public class SimulationController {

    private final Grid grid;
    private final int epochs;
    private final MainView mainView;
    private int timeOfFrame;

    public SimulationController(Grid grid, int epochs, int timeOfFrame, MainView mainView) {
        this.grid = grid;
        this.epochs = epochs;
        this.mainView = mainView;
        this.timeOfFrame = timeOfFrame;
    }

    public void run() {
        control();
    }

    public void control() {
        if (grid.isEmpty()) {
            fillGridByRandom();
        }
        Simulator creator = new Simulator(this.grid, this.mainView, SimulationAction.CREATE, this.timeOfFrame);
        Simulator killer = new Simulator(this.grid, this.mainView, SimulationAction.KILL, this.timeOfFrame);
        Simulator shower = new Simulator(this.grid, this.mainView, SimulationAction.SHOW, this.timeOfFrame);

        Thread creatorThread = new Thread(creator);
        Thread killerThread = new Thread(killer);
        Thread showerThread = new Thread(shower);
        for (int i = 0; i < epochs; i++) {
            try {
                Thread a = new Thread(creator);
                a.start();
                a.join();
                Thread b = new Thread(killer);
                b.start();
                b.join();
                Thread c = new Thread(shower);
                c.start();
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public void fillGridByRandom() {
        Random random = new Random();
        for (int y = 0; y < grid.getSizeY(); y++) {
            for (int x = 0; x < grid.getSizeX(); x++) {
                grid.getCells()[y][x] = new Cell(random.nextBoolean());
            }
        }
    }

    public Grid getGrid() {
        return this.grid;
    }
}
