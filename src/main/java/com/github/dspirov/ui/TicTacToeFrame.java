package com.github.dspirov.ui;

import com.github.dspirov.model.Board;
import com.github.dspirov.model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Main frame of the application.
 *
 * Created by dspirov on 30/07/16.
 */
public class TicTacToeFrame extends JFrame {

    private GamePanel gamePanel;
    private Game game;
    private JLabel statusBar;

    public TicTacToeFrame() throws HeadlessException {
        this.game = new Game("Player1", "Player2");

        statusBar = new JLabel("  ");
        statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));

        this.gamePanel = new GamePanel(game, statusBar);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(gamePanel, BorderLayout.CENTER);
        cp.add(statusBar, BorderLayout.PAGE_END);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setTitle("Tic Tac Toe");
        setVisible(true);
        game.initGame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new TicTacToeFrame();
            }
        });
    }
}
