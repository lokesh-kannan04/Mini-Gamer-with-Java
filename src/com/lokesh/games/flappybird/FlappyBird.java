package com.lokesh.games.flappybird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.lokesh.mainmenu.MainMenu;

public class FlappyBird extends JFrame implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;
    private Timer timer;
    private int birdY = 250;
    private int birdVelocity = 0;
    private int gravity = 1;
    private boolean gameOver = false;
    private int score = 0;
    private final int BIRD_X = 100;
    private final int BIRD_SIZE = 20;
    private final int PIPE_WIDTH = 50;
    private final int PIPE_GAP = 150;
    private int pipeX = 400;
    private int pipeHeight = 200;
    private JPanel gamePanel;

    public FlappyBird() {
        setTitle("Flappy Bird");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gamePanel = new GamePanel();
        add(gamePanel);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
                dispose();
            }
        });
        add(backButton, BorderLayout.SOUTH);

        addKeyListener(this);
        setFocusable(true);

        timer = new Timer(20, this);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FlappyBird().setVisible(true);
            }
        });
    }
    
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            birdVelocity += gravity;
            birdY += birdVelocity;
            pipeX -= 5;

            if (pipeX < -PIPE_WIDTH) {
                pipeX = getWidth();
                pipeHeight = (int) (Math.random() * (getHeight() - PIPE_GAP));
                score++;
            }

            if (birdY > getHeight() || birdY < 0 || (birdY < pipeHeight && BIRD_X + BIRD_SIZE > pipeX && BIRD_X < pipeX + PIPE_WIDTH)
                    || (birdY + BIRD_SIZE > pipeHeight + PIPE_GAP && BIRD_X + BIRD_SIZE > pipeX && BIRD_X < pipeX + PIPE_WIDTH)) {
                gameOver = true;
            }

            gamePanel.repaint();
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            birdVelocity = -10;
        } else if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            resetGame();
        }
    }

    
    public void keyReleased(KeyEvent e) {}
    
    public void keyTyped(KeyEvent e) {}

    private void resetGame() {
        birdY = 250;
        birdVelocity = 0;
        pipeX = 400;
        pipeHeight = 200;
        score = 0;
        gameOver = false;
        timer.start();
    }

    class GamePanel extends JPanel {
        private static final long serialVersionUID = 1L;

        
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.ORANGE);
            g.fillRect(BIRD_X, birdY, BIRD_SIZE, BIRD_SIZE);

            g.setColor(Color.GREEN);
            g.fillRect(pipeX, 0, PIPE_WIDTH, pipeHeight);
            g.fillRect(pipeX, pipeHeight + PIPE_GAP, PIPE_WIDTH, getHeight() - pipeHeight - PIPE_GAP);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + score, 10, 20);

            if (gameOver) {
                g.setFont(new Font("Arial", Font.BOLD, 40));
                g.drawString("Game Over", getWidth() / 2 - 100, getHeight() / 2);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("Press 'R' to restart", getWidth() / 2 - 75, getHeight() / 2 + 40);
            }
        }
    }
}
