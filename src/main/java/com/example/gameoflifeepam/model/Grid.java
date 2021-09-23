package com.example.gameoflifeepam.model;


public class Grid {
    private int sizeY;
    private int sizeX;
    private Cell[][] cells;

    public Grid(int sizeX, int sizeY) {
        cells = new Cell[sizeY][sizeX];
        this.sizeY = sizeY;
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public int checkNeighborsOfCell(int x, int y) {
        int neighbors = 0;
        if (y > 0) {
            if (cells[y - 1][x].isAlive()) {
                neighbors++;
            }
        }
        if (y < sizeY-1) {
            if (cells[y + 1][x].isAlive()) {
                neighbors++;
            }
        }
        if (x > 0) {
            if (cells[y][x - 1].isAlive()) {
                neighbors++;
            }
        }
        if (x < sizeX-1) {
            if (cells[y][x + 1].isAlive()) {
                neighbors++;
            }
        }
        return neighbors;
    }
}
