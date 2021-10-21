package com.example.gameoflifeepam.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridServiceImplTest {

    public static final int GRID_SIZE_X = 20;
    public static final int GRID_SIZE_Y = 20;

    private Grid initGrid(boolean isAliveCells) {
        Grid grid = new Grid(GridServiceImplTest.GRID_SIZE_X, GridServiceImplTest.GRID_SIZE_Y);
        for (int y = 0; y < GridServiceImplTest.GRID_SIZE_Y; y++) {
            for (int x = 0; x < GridServiceImplTest.GRID_SIZE_X; x++) {
                grid.getCells()[y][x] = new Cell(isAliveCells);
            }
        }
        return grid;
    }

    @Test
    public void testCheckNeighbors_shouldBe8() {
        int expectedNeighbours = 8;
        int checkedXCoordinate = 1, checkedYCoordinate = 1;

        GridServiceImpl gridService = new GridServiceImpl();
        Grid grid = initGrid(true);

        assertEquals(expectedNeighbours, gridService.checkNeighbors(grid, checkedXCoordinate, checkedYCoordinate));
    }

    @Test
    public void testCheckNeighbors_shouldBe0() {
        int expectedNeighbours = 0;
        int checkedXCoordinate = 1, checkedYCoordinate = 1;

        GridServiceImpl gridService = new GridServiceImpl();
        Grid grid = initGrid(false);

        assertEquals(expectedNeighbours, gridService.checkNeighbors(grid, checkedXCoordinate, checkedYCoordinate));
    }

    @Test
    public void testCheckNeighbors_topLeftCornerShouldBe3() {
        int expectedNeighbours = 3;
        int checkedXCoordinate = 0, checkedYCoordinate = 0;

        GridServiceImpl gridService = new GridServiceImpl();
        Grid grid = initGrid(true);

        assertEquals(expectedNeighbours, gridService.checkNeighbors(grid, checkedXCoordinate, checkedYCoordinate));
    }

    @Test
    public void testCheckNeighbors_topRightCornerShouldBe3() {
        int expectedNeighbours = 3;
        int checkedXCoordinate = 19, checkedYCoordinate = 0;

        GridServiceImpl gridService = new GridServiceImpl();
        Grid grid = initGrid(true);

        assertEquals(expectedNeighbours, gridService.checkNeighbors(grid, checkedXCoordinate, checkedYCoordinate));
    }

    @Test
    public void testCheckNeighbors_downLeftCornerShouldBe3() {
        int expectedNeighbours = 3;
        int checkedXCoordinate = 0, checkedYCoordinate = 19;

        GridServiceImpl gridService = new GridServiceImpl();
        Grid grid = initGrid(true);

        assertEquals(expectedNeighbours, gridService.checkNeighbors(grid, checkedXCoordinate, checkedYCoordinate));
    }

    @Test
    public void testCheckNeighbors_downRightCornerShouldBe3() {
        int expectedNeighbours = 3;
        int checkedXCoordinate = 19, checkedYCoordinate = 19;

        GridServiceImpl gridService = new GridServiceImpl();
        Grid grid = initGrid(true);

        assertEquals(expectedNeighbours, gridService.checkNeighbors(grid, checkedXCoordinate, checkedYCoordinate));
    }

    @Test
    public void testFillByRandom_gridShouldBeNotEmpty() {
        Grid grid = new Grid(10, 10);
        GridServiceImpl gridService = new GridServiceImpl();
        gridService.fillByRandom(grid);

        assertFalse(gridService.isEmpty(grid));
    }

    @Test
    public void testIsEmpty_shouldBeNotEmptyWhenOneCellIsNotNull() {
        Grid grid = new Grid(10, 10);
        grid.getCells()[2][4] = new Cell(true);
        GridServiceImpl gridService = new GridServiceImpl();

        assertFalse(gridService.isEmpty(grid));
    }

    @Test
    public void testIsEmpty_shouldBeEmpty() {
        Grid grid = new Grid(10, 10);
        GridServiceImpl gridService = new GridServiceImpl();

        assertTrue(gridService.isEmpty(grid));
    }

    @Test
    public void testIsEmpty_shouldBeNotEmptyWhenAllCellsAreNotNullAndAlive() {
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
    public void testIsEmpty_shouldBeNotEmptyWhenAllCellsAreNotNullAndDie() {
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
    public void testKillAllinGrid_shouldBeAllDie() {
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