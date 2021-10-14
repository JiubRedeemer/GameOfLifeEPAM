package com.example.gameoflifeepam.model;

import java.util.ArrayList;
import java.util.List;

public class HistoryImpl implements History {


    public final static int CAPACITY = 5;
    private final List<Grid> history = new ArrayList<>(CAPACITY);

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
    public List<Grid> getHistory() {
        return history;
    }
}
