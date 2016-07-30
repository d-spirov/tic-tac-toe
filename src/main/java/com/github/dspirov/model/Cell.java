package com.github.dspirov.model;

/**
 * Created by dspirov on 30/07/16.
 */
public class Cell {

    private int row;
    private int column;
    private Seed seed;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        clear();
    }

    void clear() {
        this.seed = Seed.EMPTY;
    }

    void setSeet(Seed seed) {
        this.seed = seed;
    }

    public Seed getSeed() {
        return seed;
    }
}
