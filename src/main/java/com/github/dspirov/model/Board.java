package com.github.dspirov.model;

/**
 * Represents board for the game.
 *
 * Created by dspirov on 30/07/16.
 */
public class Board {

    public static final int ROWS_COUNT = 3;
    public static final int COLUMNS_COUNT = 3;

    private Cell[][] cells;

    Board() {
        this.cells = new Cell[ROWS_COUNT][COLUMNS_COUNT];
        for (int i = 0; i < ROWS_COUNT; i++) {
            for (int j = 0; j < COLUMNS_COUNT; j++) {
                this.cells[i][j] = new Cell(i, j);
            }
        }
    }

    void init() {
        for (int i = 0; i < ROWS_COUNT; i++) {
            for (int j = 0; j < COLUMNS_COUNT; j++) {
                this.cells[i][j].clear();
            }
        }
    }

    void move(Seed seed, int row, int col) {
        cells[row][col].setSeet(seed);
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }
}
