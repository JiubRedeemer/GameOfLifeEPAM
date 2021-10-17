package com.example.gameoflifeepam.model;

import java.util.List;

public interface History {

    void add(Grid grid, int index);

    List<Grid> getHistory();
}
