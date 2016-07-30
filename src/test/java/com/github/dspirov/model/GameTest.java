package com.github.dspirov.model;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.github.dspirov.model.Seed.X;
import static com.github.dspirov.model.State.PLAYING;
import static org.testng.Assert.*;

/**
 * Created by dspirov on 30/07/16.
 */
public class GameTest {

    private Game game;

    @BeforeMethod
    public void setUp() throws Exception {
        Board board = new Board();
        this.game = new Game(board);
    }

    @Test
    public void testInitGame() throws Exception {
        game.initGame();
        assertEquals(game.getCurrentPlayer(), X);
    }

    @Test
    public void testGetCurrentState() throws Exception {
        game.initGame();
        assertEquals(game.getCurrentState(), PLAYING);
    }

    @Test
    public void testGetCurrentPlayer() throws Exception {
        game.initGame();
        assertEquals(game.getCurrentPlayer(), X);
    }
}