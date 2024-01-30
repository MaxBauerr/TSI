package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Printer.Statement.welcomeMessage(); // Game start message
        try (Scanner sc = new Scanner(System.in)) { // Scanner initialization with try-with-resources for automatic closing
            int boardSize = Logics.getValidBoardSize(sc); // Initialize board size with Error Handling
            int difficultyChoice = Logics.chooseDifficulty(sc); // Pass scanner as argument to method
            int minesCount = Logics.calculateMines(boardSize, difficultyChoice); // Initialize mines count
            Minesweeper game = new Minesweeper(boardSize, minesCount, difficultyChoice); // Create the Minesweeper game instance
            game.play(sc); // Start the game
        } catch (Exception e) { // Catch any error if game does not start
            Printer.Invalid.gameError(e);
        }
    }
}
