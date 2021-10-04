package com.example.gameoflifeepam.model;


public class Grid {
    private final int sizeY;
    private final int sizeX;

    private Cell[][] cells;

    public Grid(int sizeX, int sizeY) {
        cells = new Cell[sizeY][sizeX];
        this.sizeY = sizeY;
        this.sizeX = sizeX;
    }

    public Grid(Grid otherGrid) {

        this.sizeY = otherGrid.getSizeY();
        this.sizeX = otherGrid.getSizeX();
        cells = new Cell[sizeY][sizeX];
        for (int i = 0; i < this.getCells().length; i++)
            for (int j = 0; j < this.getCells()[i].length; j++)
                this.getCells()[i][j] = otherGrid.getCells()[i][j].clone();
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public boolean isEmpty() {
        for (Cell[] array : cells) {
            for (Cell val : array) {
                if (val != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void cleanGrid() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                cells[y][x].setAlive(false);
            }
        }
    }

    public int checkNeighborsOfCell(int x, int y) {
        int neighbors = 0;

        // Check neighbors in forward directions
        if (y > 0) {
            if (cells[y - 1][x].isAlive()) {
                neighbors++;
            }
        }
        if (y < sizeY - 1) {
            if (cells[y + 1][x].isAlive()) {
                neighbors++;
            }
        }
        if (x > 0) {
            if (cells[y][x - 1].isAlive()) {
                neighbors++;
            }
        }
        if (x < sizeX - 1) {
            if (cells[y][x + 1].isAlive()) {
                neighbors++;
            }
        }

        // Check diagonals in zeros
        if (x > 0 && y > 0) {
            if (cells[y - 1][x - 1].isAlive()) {
                neighbors++;
            }
        }
        if (x < sizeX - 1 && y < sizeY - 1) {
            if (cells[y + 1][x + 1].isAlive()) {
                neighbors++;
            }
        }
        if (x > 0 && y < sizeY - 1) {
            if (cells[y + 1][x - 1].isAlive()) {
                neighbors++;
            }
        }
        if (x < sizeX - 1 && y > 0) {
            if (cells[y - 1][x + 1].isAlive()) {
                neighbors++;
            }
        }


        return neighbors;
    }

}
