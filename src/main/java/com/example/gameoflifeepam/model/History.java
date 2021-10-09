package com.example.gameoflifeepam.model;

import java.util.ArrayList;
import java.util.Arrays;

public class History implements HistoryInterface {
    public final static int CAPACITY = 5;

    private final ArrayList<Grid> history = new ArrayList<Grid>(CAPACITY);

    @Override
    public void add(Grid grid) {
        if (history.size() >= CAPACITY) {
            history.remove(0);
        }
        history.add(new Grid(grid));
    }

    public ArrayList<Grid> getHistory() {
        return history;
    }
}
