package com.lokesh.mainmenu;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.lokesh.games.xo.XOGame;
import com.lokesh.games.flappybird.FlappyBird;
import com.lokesh.games.snake.Snake;
import com.lokesh.games.numberguessing.NumberGuessing;

public class MainMenu extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainMenu() {
        setTitle("Mini Gamer");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));
        
        //JPanel panel = new JPanel();
        //panel.setLayout(new GridLayout(1, 4));

        JButton xoButton = new JButton("Tic-Tac-Toe");	
        xoButton.setBounds(50, 50, 300, 50);
        add(xoButton);
        xoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open XO Game
                XOGame xoGame = new XOGame();
                xoGame.setVisible(true);
                dispose();
            }
        });

        JButton flappyBirdButton = new JButton("Flappy Bird");
        flappyBirdButton.setBounds(50, 120, 300, 50);
        add(flappyBirdButton);
        flappyBirdButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open Flappy Bird Game
                FlappyBird flappyBirdGame = new FlappyBird();
                flappyBirdGame.setVisible(true);
                dispose();
            }
        });

        JButton snakeButton = new JButton("Snake Game");
        snakeButton.setBounds(50, 190, 300, 50);
        add(snakeButton);
        snakeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open Snake Game
                Snake snakeGame = new Snake();
                snakeGame.setVisible(true);
                dispose();
            }
        });

        JButton numberGuessingButton = new JButton("Number Guessing Game");
        numberGuessingButton.setBounds(50, 260, 300, 50);
        add(numberGuessingButton);
        numberGuessingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open Number Guessing Game
                NumberGuessing numberGuessingGame = new NumberGuessing();
                numberGuessingGame.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

	
}
