package com.example.gameoflifeepam.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    public void testGetSizeY() {
        Grid grid= new Grid(10,20);
        assertEquals(10, grid.getSizeX());
    }

    @Test
    public void testGetSizeX() {
        Grid grid= new Grid(10,20);
        assertEquals(20, grid.getSizeY());
    }
}