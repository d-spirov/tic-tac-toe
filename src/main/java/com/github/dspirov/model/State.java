package com.github.dspirov.model;

/**
 * Created by dspirov on 30/07/16.
 */
public enum State {

    PLAYING(""),
    X_WON("X won"),
    O_WON("Y won"),
    DRAW("Draw");

    private String message;

    private State(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

}
