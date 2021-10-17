package com.example.gameoflifeepam.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void testGetSizeY() {
        Grid grid= new Grid(10,20);
        assertEquals(10, grid.getSizeX());
    }

    @Test
    void testGetSizeX() {
        Grid grid= new Grid(10,20);
        assertEquals(20, grid.getSizeY());
    }
}