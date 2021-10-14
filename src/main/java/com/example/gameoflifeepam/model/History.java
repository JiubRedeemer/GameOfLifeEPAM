package com.example.gameoflifeepam.model;

import java.util.List;

public interface History {

    public void add(Grid grid, int index);

    public List<Grid> getHistory();
}
