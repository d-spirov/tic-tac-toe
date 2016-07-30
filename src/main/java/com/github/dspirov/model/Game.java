package com.github.dspirov.model;

import java.util.HashMap;
import java.util.Map;

import static com.github.dspirov.model.Board.COLUMNS_COUNT;
import static com.github.dspirov.model.Board.ROWS_COUNT;

/**
 * Game controller for the tic-tac-toe.
 *
 * Created by dspirov on 30/07/16.
 */
public class Game {

    private Seed currentPlayer;
    private Board board;
    private State currentState;
    private int moves;
    private Map<Seed, String> players;

    public Game(String player1, String player2) {
        this.board = new Board();
        players = new HashMap<>();
        players.put(Seed.X, player1);
        players.put(Seed.O, player2);
    }

    public String getPlayerName(Seed seed) {
        return players.get(seed);
    }

    /**
     * Initializes the game. Also it restarts the game.
     *
     * - X is always first
     * - sets the game status to PLAYING
     */
    public void initGame() {
        this.board.init();
        this.currentPlayer = Seed.X;
        this.currentState = State.PLAYING;
        this.moves = 0;
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
        if(currentState != State.PLAYING) {
            throw new IllegalStateException("Game is finished, cannot move!");
        }
        moves++;
        board.move(currentPlayer, row, col);
        updateGameState(currentPlayer, row, col);
        switchPlayers();
    }

    public int getMoves() {
        return moves;
    }

    private void switchPlayers() {
        currentPlayer = (currentPlayer == Seed.X) ? Seed.O : Seed.X;
    }

    private void updateGameState(Seed seed, int row, int col) {
        if (hasWon(seed, row, col)) {
            currentState = (seed == Seed.X) ? State.X_WON : State.O_WON;
        } else if (isDraw()) {
            currentState = State.DRAW;
        }
    }

    private boolean isDraw() {
        for (int row = 0; row < ROWS_COUNT; ++row) {
            for (int col = 0; col < COLUMNS_COUNT; ++col) {
                if (board.getCell(row, col).getSeed() == Seed.EMPTY) {
                    return false;
                }
            }
        }
        return true;
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
