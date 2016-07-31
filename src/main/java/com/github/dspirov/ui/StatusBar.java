package com.github.dspirov.ui;

import com.github.dspirov.model.Game;
import com.github.dspirov.model.Seed;
import com.github.dspirov.model.State;

import javax.swing.*;
import java.awt.*;

import static com.github.dspirov.model.State.DRAW;
import static com.github.dspirov.model.State.O_WON;
import static com.github.dspirov.model.State.PLAYING;
import static com.github.dspirov.model.State.X_WON;

/**
 * Status bar component displayed on the main frame.
 *
 * Created by dspirov on 31/07/16.
 */
public class StatusBar extends JLabel{

    private final Game game;

    public StatusBar(Game game) {
        super(" ");
        this.game = game;
    }

    public void update() {
        State currentState = game.getCurrentState();
        if (currentState == PLAYING) {
            setForeground(Color.BLACK);
            if (game.getCurrentPlayer() == Seed.X) {
                setText(game.getPlayerName(Seed.X) + "'s Turn");
            } else {
                setText(game.getPlayerName(Seed.O) + "'s Turn");
            }
        } else if (currentState == DRAW) {
            setForeground(Color.RED);
            setText("It's a Draw! Click to play again.");
        } else if (currentState == X_WON) {
            setForeground(Color.RED);
            setText(game.getPlayerName(Seed.X) + " Won! Click to play again.");
        } else if (currentState == O_WON) {
            setForeground(Color.RED);
            setText(game.getPlayerName(Seed.O) + " Won! Click to play again.");
        }

    }
}
