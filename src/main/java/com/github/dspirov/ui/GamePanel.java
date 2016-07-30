package com.github.dspirov.ui;

import com.github.dspirov.model.Board;
import com.github.dspirov.model.Game;
import com.github.dspirov.model.Seed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.github.dspirov.model.Board.COLUMNS_COUNT;
import static com.github.dspirov.model.Board.ROWS_COUNT;
import static com.github.dspirov.model.State.PLAYING;

/**
 * Created by dspirov on 30/07/16.
 */
class GamePanel extends JPanel {

    public static final int CELL_SIZE = 100;
    private static final int CELL_PADDING = CELL_SIZE / 5;
    private static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;
    private final int CANVAS_WIDTH = 150;
    private final int CANVAS_HEIGHT = 150;
    private final int GRID_WIDTH = 8;
    private final int SYMBOL_STRIKE_WIDTH = 8;
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(CELL_SIZE * COLUMNS_COUNT, CELL_SIZE * ROWS_COUNT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.GRAY);
        drawGrid(g);
        drawCells(g);
        addMouseListener();
    }

    void addMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int rowSelected = x / CELL_SIZE;
                int colSelected = y / CELL_SIZE;
                System.out.println("Clicked on the " + rowSelected + " " + colSelected);

                if (game.getCurrentState() == PLAYING) {
                    if (rowSelected >= 0 && rowSelected < ROWS_COUNT && colSelected >= 0
                            && colSelected < COLUMNS_COUNT && game.getBoard().getCell(rowSelected, colSelected).getSeed() == Seed.EMPTY) {
                        game.moveCurrentPlayer(rowSelected, colSelected);
                    }
                } else {
                    // game over
                    game.initGame(); // restart the game
                }
                // Refresh the drawing canvas
                repaint();  // Call-back paintComponent().

            }
        });
    }

    private void drawCells(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(SYMBOL_STRIKE_WIDTH, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        for (int row = 0; row < ROWS_COUNT; row++) {
            for (int col = 0; col < COLUMNS_COUNT; col++) {
                int x1 = col * CELL_SIZE + CELL_PADDING;
                int y1 = row * CELL_SIZE + CELL_PADDING;
                Seed currentSeed = game.getBoard().getCell(row, col).getSeed();
                if (currentSeed == Seed.X) {
                    g2d.setColor(Color.RED);
                    int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
                    int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
                    g2d.drawLine(x1, y1, x2, y2);
                    g2d.drawLine(x2, y1, x1, y2);
                } else if(currentSeed == Seed.O) {
                    g2d.setColor(Color.BLUE);
                    g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
                }
            }
        }
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.BLUE);
        for (int i = 0; i < ROWS_COUNT; i++) {
            g.fillRoundRect(0, CELL_SIZE * i - GRID_WIDTH / 2, CANVAS_WIDTH, GRID_WIDTH,
                    GRID_WIDTH, GRID_WIDTH);
        }
        for (int j = 0; j < COLUMNS_COUNT; j++) {
            g.fillRoundRect(CELL_SIZE * j - GRID_WIDTH / 2, 0, GRID_WIDTH, CANVAS_HEIGHT,
                    GRID_WIDTH, GRID_WIDTH);
        }
    }

}
