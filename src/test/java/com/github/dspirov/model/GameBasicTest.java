package com.github.dspirov.model;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.github.dspirov.model.Seed.O;
import static com.github.dspirov.model.Seed.X;
import static com.github.dspirov.model.State.PLAYING;
import static org.testng.Assert.assertEquals;

/**
 * Basic logic of the game tests. Mostly about initialization.
 *
 * Created by dspirov on 30/07/16.
 */
public class GameBasicTest {

    private Game game;

    @BeforeMethod
    public void setUp() throws Exception {
        this.game = new Game("Player1", "Player2");
    }

    @Test
    public void testPlayers() {
        Assert.assertEquals(game.getPlayerName(X), "Player1");
        Assert.assertEquals(game.getPlayerName(O), "Player2");
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

    @Test
    public void testSwitchPlayers() throws Exception {
        game.initGame();
        assertEquals(game.getCurrentPlayer(), X);
        game.moveCurrentPlayer(1, 1);
        assertEquals(game.getCurrentPlayer(), O);
    }
}