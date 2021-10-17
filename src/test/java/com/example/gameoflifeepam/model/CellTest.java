package com.example.gameoflifeepam.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CellTest {

    @Test
    void testIsAlive() {
        Cell cell = new Cell(true);
        assertTrue(cell.isAlive());
        cell.setAlive(false);
        assertFalse(cell.isAlive());
    }

}