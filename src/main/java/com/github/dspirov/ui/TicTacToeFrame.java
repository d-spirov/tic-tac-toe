package com.github.dspirov.ui;

import com.github.dspirov.model.Board;
import com.github.dspirov.model.Game;
import com.github.dspirov.model.Seed;
import com.github.dspirov.model.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.github.dspirov.model.State.PLAYING;
import static com.github.dspirov.ui.GamePanel.CELL_SIZE;

/**
 * Created by dspirov on 30/07/16.
 */
public class TicTacToeFrame extends JFrame {

    private GamePanel gamePanel;
    private Game game;
    private JLabel statusBar;  // Status Bar

    public TicTacToeFrame() throws HeadlessException {
        Board board = new Board();
        this.game = new Game(board);
        this.gamePanel = new GamePanel(game);

        statusBar = new JLabel("  ");
        statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(gamePanel, BorderLayout.CENTER);
        cp.add(statusBar, BorderLayout.PAGE_END); // same as SOUTH

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();  // pack all the components in this JFrame
        setTitle("Tic Tac Toe");
        setVisible(true);  // show this JFrame
        game.initGame();
    }

    public static void main(String[] args) {
        // Run GUI codes in the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToeFrame(); // Let the constructor do the job
            }
        });
    }
}
