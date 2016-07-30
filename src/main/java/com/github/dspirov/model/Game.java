package com.github.dspirov.model;

import static com.github.dspirov.model.Board.COLUMNS_COUNT;
import static com.github.dspirov.model.Board.ROWS_COUNT;

/**
 * Created by dspirov on 30/07/16.
 */
public class Game {

    private Seed currentPlayer;
    private Board board;
    private State currentState;

    public Game(Board board) {
        this.board = board;
    }

    public void initGame() {
        this.board.init();
        this.currentPlayer = Seed.X;
        this.currentState = State.PLAYING;
    }

    public State getCurrentState() {
        return currentState;
    }

    public Seed getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void moveCurrentPlayer(int row, int col) {
        board.move(currentPlayer, row, col);
        updateGameState(currentPlayer, row, col);
        switchPlayers();
    }

    private void switchPlayers() {
        currentPlayer = (currentPlayer == Seed.X) ? Seed.O : Seed.X;
    }

    private void updateGameState(Seed seed, int row, int col) {
        if (hasWon(seed, row, col)) {  // check for win
            currentState = (seed == Seed.X) ? State.X_WON : State.O_WON;
        } else if (isDraw()) {  // check for draw
            currentState = State.DRAW;
        }
    }

    private boolean isDraw() {
        for (int row = 0; row < ROWS_COUNT; ++row) {
            for (int col = 0; col < COLUMNS_COUNT; ++col) {
                if (board.getCell(row, col).getSeed() == Seed.EMPTY) {
                    return false; // an empty cell found, not draw, exit
                }
            }
        }
        return true;  // no more empty cell, it's a draw
    }

    private boolean hasWon(Seed theSeed, int rowSelected, int colSelected) {
        return (board.getCell(rowSelected, 0).getSeed() == theSeed  // 3-in-the-row
                && board.getCell(rowSelected, 1).getSeed() == theSeed
                && board.getCell(rowSelected, 2).getSeed() == theSeed
                || board.getCell(0, colSelected).getSeed() == theSeed      // 3-in-the-column
                && board.getCell(1, colSelected).getSeed() == theSeed
                && board.getCell(2, colSelected).getSeed() == theSeed
                || rowSelected == colSelected            // 3-in-the-diagonal
                && board.getCell(0, 0).getSeed() == theSeed
                && board.getCell(1, 1).getSeed() == theSeed
                && board.getCell(2, 2).getSeed() == theSeed
                || rowSelected + colSelected == 2  // 3-in-the-opposite-diagonal
                && board.getCell(0, 2).getSeed() == theSeed
                && board.getCell(1, 1).getSeed() == theSeed
                && board.getCell(2, 0).getSeed() == theSeed);
    }
}
