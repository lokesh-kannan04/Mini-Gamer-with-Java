package com.lokesh.games.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import com.lokesh.mainmenu.MainMenu;

public class Snake extends JFrame implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;
    private static final int TILE_SIZE = 20;
    private static final int GRID_WIDTH = 25;
    private static final int GRID_HEIGHT = 25;
    private static final int SCREEN_WIDTH = GRID_WIDTH * TILE_SIZE;
    private static final int SCREEN_HEIGHT = GRID_HEIGHT * TILE_SIZE;
    private static final int DELAY = 150;

    private Timer timer;
    private ArrayList<Point> snake;
    private Point food;
    private String direction;
    private boolean gameOver;
    private int score;
    private JPanel gamePanel;

    public Snake() {
        setTitle("Snake Game");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT + 50);
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

        initializeGame();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Snake().setVisible(true);
            }
        });
    }

    private void initializeGame() {
        snake = new ArrayList<>();
        snake.add(new Point(GRID_WIDTH / 2, GRID_HEIGHT / 2));
        direction = "RIGHT";
        gameOver = false;
        score = 0;
        spawnFood();
    }

    private void spawnFood() {
        Random random = new Random();
        food = new Point(random.nextInt(GRID_WIDTH), random.nextInt(GRID_HEIGHT));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            moveSnake();
            checkCollision();
            gamePanel.repaint();
        }
    }

    private void moveSnake() {
        Point head = snake.get(0);
        Point newHead = new Point(head);

        switch (direction) {
            case "UP": newHead.y--; break;
            case "DOWN": newHead.y++; break;
            case "LEFT": newHead.x--; break;
            case "RIGHT": newHead.x++; break;
        }

        snake.add(0, newHead);

        if (newHead.equals(food)) {
            score++;
            spawnFood();
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    private void checkCollision() {
        Point head = snake.get(0);

        if (head.x < 0 || head.x >= GRID_WIDTH || head.y < 0 || head.y >= GRID_HEIGHT) {
            gameOver = true;
        }

        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver = true;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (!direction.equals("DOWN")) direction = "UP";
                break;
            case KeyEvent.VK_DOWN:
                if (!direction.equals("UP")) direction = "DOWN";
                break;
            case KeyEvent.VK_LEFT:
                if (!direction.equals("RIGHT")) direction = "LEFT";
                break;
            case KeyEvent.VK_RIGHT:
                if (!direction.equals("LEFT")) direction = "RIGHT";
                break;
            case KeyEvent.VK_R:
                if (gameOver) {
                    initializeGame();
                    timer.start();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    class GamePanel extends JPanel {
        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.RED);
            g.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

            g.setColor(Color.GREEN);
            for (Point point : snake) {
                g.fillRect(point.x * TILE_SIZE, point.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }

            if (gameOver) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 40));
                g.drawString("Game Over", getWidth() / 2 - 100, getHeight() / 2);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("Press 'R' to restart", getWidth() / 2 - 75, getHeight() / 2 + 40);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + score, getWidth() - 120, 30);
        }
    }
}
