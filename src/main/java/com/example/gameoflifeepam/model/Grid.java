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
        return this.cells[0][0] == null;
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

//        // Check diagonals of corners
//        if (x == 0 && y == 0) {
//            if (cells[y + 1][x + 1].isAlive()) {
//                neighbors++;
//            }
//        }
//        else if (x == 0 && y == sizeY) {
//            if (cells[y - 1][x + 1].isAlive()) {
//                neighbors++;
//            }
//        }
//        else if (x == sizeX && y == sizeY) {
//            if (cells[y - 1][x - 1].isAlive()) {
//                neighbors++;
//            }
//        }
//        else if (x == sizeX && y == 0){
//            if (cells[y + 1][x - 1].isAlive()) {
//                neighbors++;
//            }
//        }

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
