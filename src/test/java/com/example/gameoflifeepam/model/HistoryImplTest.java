package com.example.gameoflifeepam.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryImplTest {
    @Test
    public void testNew_shouldBeEmpty() {
        HistoryImpl history = new HistoryImpl();
        assertTrue(history.getHistory().isEmpty());
    }

    @Test
    public void testAdd_whenHistoryIsEmpty() {
        HistoryImpl history = new HistoryImpl();
        GridServiceImpl gridService = new GridServiceImpl();
        Grid grid = new Grid(10, 10);
        gridService.fillByRandom(grid);
        history.add(grid, 0);

        assertFalse(history.getHistory().isEmpty());
    }

    @Test
    public void testAdd_whenHistoryIsFull() {
        HistoryImpl history = new HistoryImpl();
        GridServiceImpl gridService = new GridServiceImpl();

        Grid grid0 = new Grid(10, 10);
        Grid grid1 = new Grid(10, 10);
        Grid grid2 = new Grid(10, 10);
        Grid grid3 = new Grid(10, 10);
        Grid grid4 = new Grid(10, 10);
        Grid grid5 = new Grid(10, 10);

        gridService.fillByRandom(grid0);
        gridService.fillByRandom(grid1);
        gridService.fillByRandom(grid2);
        gridService.fillByRandom(grid3);
        gridService.fillByRandom(grid4);
        gridService.fillByRandom(grid5);

        history.add(grid0, 0);
        history.add(grid1, 1);
        history.add(grid2, 2);
        history.add(grid3, 3);
        history.add(grid4, 4);
        history.add(grid5, 5);

        for (int i = 0; i < history.getHistory().size(); i++) {
            assertNotNull(history.getHistory().get(i));
        }
    }
}