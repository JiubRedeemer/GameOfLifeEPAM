package com.example.gameoflifeepam.model;

import java.util.ArrayList;

public interface HistoryInterface {

    public void add(Grid grid);

    public ArrayList<Grid> getHistory();
}
