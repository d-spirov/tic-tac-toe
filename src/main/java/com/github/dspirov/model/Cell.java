package com.github.dspirov.model;

/**
 * Represents one cell on the board.
 *
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (row != cell.row) return false;
        return column == cell.column;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
