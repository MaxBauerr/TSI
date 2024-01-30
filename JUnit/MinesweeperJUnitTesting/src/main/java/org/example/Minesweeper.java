package org.example;

import java.util.Scanner;

public class Minesweeper {
    private final Board board; // Make Board final as it cannot be changed once game is started
    private boolean gameStatus; // To check if the game is still active or over

    public Minesweeper(int squareSize, int numMines, int difficultyChoice) {
        this.gameStatus = true; // Game status true indicates active loop
        int hints = Logics.hintCount(difficultyChoice); // Calculate available hints depending on game difficulty
        board = new Board(squareSize, numMines, hints);  // Create board
    }

    public void play(Scanner sc) {
        int[] coordinates = getRowAndColumn(sc); // Request user initial inputs to flip cells
        long startTime = System.currentTimeMillis(); // Initialize time to check length of the game
        board.placeMines(coordinates[0], coordinates[1]); // Initialize mines after the first move
        playMove((coordinates[0]), (coordinates[1]), startTime); // First move gets played along size mine creation
        winCondition(startTime); // Check if the first move caused the game to end. Parse time just in case game ended.
        while (gameStatus) { // While the game is true (running), run the loop
            board.displayBoard(); // Display initial board with first move done and mines placed
            Printer.Options.runningGameOptions(board.getHintCount()); // Show player the in game options
            int inGameChoice = Logics.chooseGameChoice(sc);
            switch (inGameChoice) { // Depending on user input, game will act differently
                case 1 -> {
                    // Standard move play condition
                    coordinates = getRowAndColumn(sc);
                    playMove((coordinates[0]), (coordinates[1]), startTime);
                    winCondition(startTime);
                }
                case 2 -> optionFlagOrUnflag(true, sc); // Request user coordinates to flag an unused cell. Error handling implemented.
                case 3 -> optionFlagOrUnflag(false, sc); // Request user coordinates to remove existing flag. Error handling implemented.
                case 4 -> board.giveHint(); // Randomly allocate non-flipped cell and show true value, acts the same as the player choose the cell in option 1.
                case 5 -> gameEnd(startTime, true);
                default -> Printer.Invalid.optionRangeChoice(); // Error handling in case out of bounds
            }
        }
    }

    private void optionFlagOrUnflag(boolean flag, Scanner sc) {
        int[] coordinates = getRowAndColumn(sc);
        if(flag) { // IF flag request is true
            board.cellFlag(coordinates[0], coordinates[1]);
        } else { // IF remove flag request is true
            board.cellUnflag(coordinates[0], coordinates[1]);
        }
    }

    private void playMove(int row, int col, long startTime) {
        if (board.isMineHere(row, col)) { // Check if player move cause a mine to appear, if TRUE, END GAME (L).
            gameEnd(startTime, false);
        }  else { // IF mine was not found, run standard logic.
            Printer.Statement.safeMove();
            board.changeBoard(row, col);
        }
    }

    private void winCondition(long startTime) {
        if (board.getSafeCells() == 0) { // IF total amount of safe cells drops to 0 as all have been found. END GAME (W).
            gameEnd(startTime, false);
        }
    }

    private int[] getRowAndColumn(Scanner sc) { // Request User game coordinates. As program starts with 0 on list counts, it automatically adjusts user input to -1.
        Printer.Statement.moveEntry();
        Printer.Requests.rowEntry();
        int row = Logics.chooseCoordinates(sc);
        Printer.Requests.columnEntry();
        int col = Logics.chooseCoordinates(sc);
        return new int[]{row-1, col-1};
    }

    private void gameEnd(long startTime, boolean playerChoice) {
        gameStatus = false;
        long endTime = System.currentTimeMillis();
        Printer.Statement.gameOver(playerChoice);
        board.displayEndBoard(gameStatus);
        Logics.timeCount(startTime, endTime);
    }
}
