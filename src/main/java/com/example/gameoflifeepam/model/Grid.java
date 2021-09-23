package com.example.gameoflifeepam.model;

import java.util.Random;

public class Grid{
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


}
