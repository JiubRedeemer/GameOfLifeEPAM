package com.example.gameoflifeepam.model;

import java.util.ArrayList;

public class History implements HistoryInterface {
    public final static int CAPACITY = 5;

    private final ArrayList<Grid> history = new ArrayList<Grid>(CAPACITY);

    @Override
    public void add(Grid grid, int index) {
        if (history.size() >= CAPACITY) {
            history.remove(0);
        }
        try {
            history.add(index, new Grid(grid));
        } catch (IndexOutOfBoundsException e) {
            history.add(new Grid(grid));
        }
    }

    public ArrayList<Grid> getHistory() {
        return history;
    }
}
