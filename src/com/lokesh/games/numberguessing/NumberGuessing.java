package com.lokesh.games.numberguessing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import com.lokesh.mainmenu.MainMenu;

public class NumberGuessing extends JFrame {
    private static final long serialVersionUID = 1L;
    private int upperLimit;
    private int numberToGuess;
    private int attempts;
    private JTextField upperLimitField;
    private JTextField guessField;
    private JLabel messageLabel;
    private JButton setLimitButton;
    private JButton guessButton;
    private JButton backButton;

    public NumberGuessing() {
    	//System.out.print("hi");
        setTitle("Number Guessing");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel upperLimitLabel = new JLabel("Enter Upper Limit:");
        upperLimitLabel.setBounds(50, 50, 150, 30);
        add(upperLimitLabel);

        upperLimitField = new JTextField();
        upperLimitField.setBounds(200, 50, 150, 30);
        add(upperLimitField);

        setLimitButton = new JButton("Set Limit");
        setLimitButton.setBounds(150, 100, 100, 30);
        add(setLimitButton);
        setLimitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    upperLimit = Integer.parseInt(upperLimitField.getText());
                    if(upperLimit>0) {
                    initializeGame();
                    }
                    else {
                    	messageLabel.setText("Please enter a valid number.");
                    }
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Please enter a valid number.");
                }
            }
        });

        JLabel guessLabel = new JLabel("Enter your guess:");
        guessLabel.setBounds(50, 150, 150, 30);
        add(guessLabel);

        guessField = new JTextField();
        guessField.setBounds(200, 150, 150, 30);
        guessField.setEnabled(false);
        add(guessField);

        guessButton = new JButton("Guess");
        guessButton.setBounds(150, 200, 100, 30);
        guessButton.setEnabled(false);
        add(guessButton);
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int guess = Integer.parseInt(guessField.getText());
                    makeGuess(guess);
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Please enter a valid number.");
                }
            }
        });

        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 250, 300, 30);
        add(messageLabel);

        backButton = new JButton("Back to Main Menu");
        backButton.setBounds(150, 300, 200, 30);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
                dispose();
            }
        });
    }

    private void initializeGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(upperLimit + 1);
        attempts = 0;
        messageLabel.setText("You have 5 attempts to guess the number!");
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
    }

    private void makeGuess(int guess) {
        attempts++;
        if (guess == numberToGuess) {
            messageLabel.setText("Congratulations! You guessed the number.");
            guessField.setEnabled(false);
            guessButton.setEnabled(false);
        } else if (attempts >= 5) {
            messageLabel.setText("You've used all your attempts! The number was " + numberToGuess);
            guessField.setEnabled(false);
            guessButton.setEnabled(false);
        } else if (guess < numberToGuess) {
            messageLabel.setText("Too low! Try again.");
        } else {
            messageLabel.setText("Too high! Try again.");
        }
    }

    public static void main(String[] args) {
    	System.out.print(" ");
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessing().setVisible(true);
            }
        });
    }
}
