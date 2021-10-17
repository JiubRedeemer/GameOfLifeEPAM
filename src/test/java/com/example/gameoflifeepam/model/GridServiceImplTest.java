package com.example.gameoflifeepam.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridServiceImplTest {

    @Test
    void testCheckNeighbors_shouldBe8() {
        GridServiceImpl gridService = new GridServiceImpl();

        Grid grid = new Grid(20, 20);
        grid.getCells()[0][0] = new Cell(true);
        grid.getCells()[0][1] = new Cell(true);
        grid.getCells()[0][2] = new Cell(true);
        grid.getCells()[1][0] = new Cell(true);
        grid.getCells()[1][1] = new Cell(true);
        grid.getCells()[1][2] = new Cell(true);
        grid.getCells()[2][0] = new Cell(true);
        grid.getCells()[2][1] = new Cell(true);
        grid.getCells()[2][2] = new Cell(true);

        assertEquals(8, gridService.checkNeighbors(grid, 1, 1));
    }

    @Test
    void testCheckNeighbors_shouldBe0() {
        GridServiceImpl gridService = new GridServiceImpl();

        Grid grid = new Grid(20, 20);
        grid.getCells()[0][0] = new Cell(false);
        grid.getCells()[0][1] = new Cell(false);
        grid.getCells()[0][2] = new Cell(false);
        grid.getCells()[1][0] = new Cell(false);
        grid.getCells()[1][1] = new Cell(false);
        grid.getCells()[1][2] = new Cell(false);
        grid.getCells()[2][0] = new Cell(false);
        grid.getCells()[2][1] = new Cell(false);
        grid.getCells()[2][2] = new Cell(false);

        assertEquals(0, gridService.checkNeighbors(grid, 1, 1));
    }

    @Test
    void testCheckNeighbors_topLeftCornerShouldBe3() {
        GridServiceImpl gridService = new GridServiceImpl();

        Grid grid = new Grid(20, 20);
        grid.getCells()[0][0] = new Cell(true);
        grid.getCells()[0][1] = new Cell(true);
        grid.getCells()[0][2] = new Cell(true);
        grid.getCells()[1][0] = new Cell(true);
        grid.getCells()[1][1] = new Cell(true);
        grid.getCells()[1][2] = new Cell(true);
        grid.getCells()[2][0] = new Cell(true);
        grid.getCells()[2][1] = new Cell(true);
        grid.getCells()[2][2] = new Cell(true);

        assertEquals(3, gridService.checkNeighbors(grid, 0, 0));
    }

    @Test
    void testCheckNeighbors_topRightCornerShouldBe3() {
        GridServiceImpl gridService = new GridServiceImpl();

        Grid grid = new Grid(20, 20);

        grid.getCells()[0][19] = new Cell(true);
        grid.getCells()[0][18] = new Cell(true);
        grid.getCells()[0][17] = new Cell(true);
        grid.getCells()[1][19] = new Cell(true);
        grid.getCells()[1][18] = new Cell(true);
        grid.getCells()[1][17] = new Cell(true);
        grid.getCells()[2][19] = new Cell(true);
        grid.getCells()[2][18] = new Cell(true);
        grid.getCells()[2][17] = new Cell(true);

        assertEquals(3, gridService.checkNeighbors(grid, 19, 0));
    }

    @Test
    void testCheckNeighbors_downLeftCornerShouldBe3() {
        GridServiceImpl gridService = new GridServiceImpl();

        Grid grid = new Grid(20, 20);

        grid.getCells()[19][0] = new Cell(true);
        grid.getCells()[18][0] = new Cell(true);
        grid.getCells()[17][0] = new Cell(true);
        grid.getCells()[19][1] = new Cell(true);
        grid.getCells()[18][1] = new Cell(true);
        grid.getCells()[17][1] = new Cell(true);
        grid.getCells()[19][2] = new Cell(true);
        grid.getCells()[18][2] = new Cell(true);
        grid.getCells()[17][2] = new Cell(true);

        assertEquals(3, gridService.checkNeighbors(grid, 0, 19));
    }

    @Test
    void testCheckNeighbors_downRightCornerShouldBe3() {
        GridServiceImpl gridService = new GridServiceImpl();

        Grid grid = new Grid(20, 20);

        grid.getCells()[19][19] = new Cell(true);
        grid.getCells()[18][19] = new Cell(true);
        grid.getCells()[17][19] = new Cell(true);
        grid.getCells()[19][18] = new Cell(true);
        grid.getCells()[18][18] = new Cell(true);
        grid.getCells()[17][18] = new Cell(true);
        grid.getCells()[19][17] = new Cell(true);
        grid.getCells()[18][17] = new Cell(true);
        grid.getCells()[17][17] = new Cell(true);

        assertEquals(3, gridService.checkNeighbors(grid, 19, 19));
    }

    @Test
    void testFillByRandom_gridShouldBeNotEmpty() {
        Grid grid = new Grid(10, 10);
        GridServiceImpl gridService = new GridServiceImpl();
        gridService.fillByRandom(grid);

        assertFalse(gridService.isEmpty(grid));
    }

    @Test
    void testIsEmpty_shouldBeNotEmptyWhenOneCellIsNotNull() {
        Grid grid = new Grid(10, 10);
        grid.getCells()[2][4] = new Cell(true);
        GridServiceImpl gridService = new GridServiceImpl();

        assertFalse(gridService.isEmpty(grid));
    }

    @Test
    void testIsEmpty_shouldBeEmpty() {
        Grid grid = new Grid(10, 10);
        GridServiceImpl gridService = new GridServiceImpl();

        assertTrue(gridService.isEmpty(grid));
    }

    @Test
    void testIsEmpty_shouldBeNotEmptyWhenAllCellsAreNotNullAndAlive() {
        Grid grid = new Grid(10, 10);
        GridServiceImpl gridService = new GridServiceImpl();
        for (int y = 0; y < grid.getSizeY(); y++) {
            for (int x = 0; x < grid.getSizeX(); x++) {
                grid.getCells()[y][x] = new Cell(true);
            }
        }
        assertFalse(gridService.isEmpty(grid));
    }

    @Test
    void testIsEmpty_shouldBeNotEmptyWhenAllCellsAreNotNullAndDie() {
        Grid grid = new Grid(10, 10);
        GridServiceImpl gridService = new GridServiceImpl();
        for (int y = 0; y < grid.getSizeY(); y++) {
            for (int x = 0; x < grid.getSizeX(); x++) {
                grid.getCells()[y][x] = new Cell(false);
            }
        }

        assertFalse(gridService.isEmpty(grid));
    }

    @Test
    void testKillAllinGrid_shouldBeAllDie() {
        Grid grid = new Grid(10, 10);
        GridServiceImpl gridService = new GridServiceImpl();

        gridService.fillByRandom(grid);
        gridService.killAllInGrid(grid);

        for (int y = 0; y < grid.getSizeY(); y++) {
            for (int x = 0; x < grid.getSizeX(); x++) {
                assertFalse(grid.getCells()[y][x].isAlive());
            }
        }
    }
}