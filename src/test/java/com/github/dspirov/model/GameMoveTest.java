package com.github.dspirov.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.github.dspirov.model.State.PLAYING;
import static com.github.dspirov.model.State.X_WON;
import static org.testng.Assert.assertEquals;

/**
 * Tests moves/ game state in the game.
 *
 * Created by dspirov on 30/07/16.
 */
public class GameMoveTest {

    private Game game;

    @BeforeMethod
    public void setUp() throws Exception {
        this.game = new Game("Player1", "Player2");
    }

    @Test
    public void testOneMove_playing() throws Exception {
        game.initGame();
        game.moveCurrentPlayer(1, 1);
        assertEquals(game.getCurrentState(), PLAYING);
    }

    @Test
    public void testMoves() throws Exception {
        game.initGame();
        game.moveCurrentPlayer(1, 1);
        assertEquals(game.getCurrentState(), PLAYING);
        assertEquals(game.getMoves(), 1);
    }

    @Test
    public void testReset() throws Exception {
        game.initGame();
        game.moveCurrentPlayer(1, 1);
        assertEquals(game.getCurrentState(), PLAYING);
        game.initGame();
        assertEquals(game.getMoves(), 0);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void test9Moves_gameFinished() throws Exception {
        game.initGame();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game.moveCurrentPlayer(i, j);
            }
        }
    }

    @Test
    public void test7Moves_xWon() throws Exception {
        int moves = 0;
        game.initGame();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game.moveCurrentPlayer(i, j);
                if(moves++ == 6) {
                    break;
                }
            }
        }
        assertEquals(game.getCurrentState(), X_WON);
    }
}
