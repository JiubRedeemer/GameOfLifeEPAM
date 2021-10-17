package com.example.gameoflifeepam.model;

import java.util.List;

public interface History {

    /**
     * Add new element to history
     * @param grid grid added to history
     * @param index index of history element
     */
    void add(Grid grid, int index);

    /**
     * @return history list
     */
    List<Grid> getHistory();
}
