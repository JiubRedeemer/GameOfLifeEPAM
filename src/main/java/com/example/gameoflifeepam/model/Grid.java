package com.example.gameoflifeepam.model;


public class Grid {

    private final int sizeY;
    private final int sizeX;
    private final Cell[][] cells;

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
}
