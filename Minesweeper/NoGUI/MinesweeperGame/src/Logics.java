import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Logics {
    private static final Random rand = new Random(); // Initialize randomness for mine count.

    public static int calculateMines(int boardSize, int gameDifficulty) { // Allocate mine count depending on grid size and game difficulty
        int minMines = 0, maxMines = 0;
        switch (gameDifficulty) {
            case 1 -> { minMines = boardSize * boardSize * 5 / 100; maxMines = boardSize * boardSize * 10 / 100; }
            case 2 -> { minMines = boardSize * boardSize * 10 / 100; maxMines = boardSize * boardSize * 20 / 100; }
            case 3 -> { minMines = boardSize * boardSize * 20 / 100; maxMines = boardSize * boardSize * 35 / 100; }
            case 4 -> { minMines = boardSize * boardSize * 35 / 100; maxMines = boardSize * boardSize * 55 / 100; }
            case 5 -> { minMines = boardSize * boardSize * 55 / 100; maxMines = boardSize * boardSize * 80 / 100; }
        }
        return rand.nextInt(maxMines - minMines + 1) + minMines;
    }

    public static int getValidBoardSize(Scanner scanner) { // Check if user input for board size meets minimum requirements
        Printer.Requests.minimumBoardSize();
        int boardSize;
        do {
            while (!scanner.hasNextInt()) {
                Printer.Invalid.integerValue();
                scanner.next();
            }
            boardSize = scanner.nextInt();
            if (boardSize < 10) {
                Printer.Invalid.boardSize();
            }
        } while (boardSize < 10);
        return boardSize;
    }



    public static int hintCount(int difficultyChoice) { // Calculate total available hints for the current game depending on the difficulty
        return difficultyChoice*3;
    }

    public static void timeCount(long start, long end) { // Calculate time spent for this game run.
        Printer.Statement.timeStats(end-start);
    }


    public static int chooseGameChoice(Scanner scanner) { // Allow user to choose next game action
        int choice;
        do {
            while (!scanner.hasNextInt()) {
                Printer.Invalid.integerValue();
                scanner.next();
            }
            choice = scanner.nextInt();
            if (choice < 1) {
                Printer.Invalid.optionRangeChoice();
            }
        } while (choice < 1);
        return choice;
    }

    public static int chooseDifficulty(Scanner scanner) { // Allow user to choose the difficulty of the game
        Printer.Options.gameDifficulty();
        int choice;
        do {
            while (!scanner.hasNextInt()) {
                Printer.Invalid.integerValue();
                scanner.next();
            }
            choice = scanner.nextInt();
            if (choice < 1 || choice > 5) {
                Printer.Invalid.optionRangeChoice();
            }
        } while (choice < 1 || choice > 5);
        return choice;
    }

    public static int chooseCoordinates(Scanner scanner) { // Allow user to choose next game action
        int move;
        do {
            while (!scanner.hasNextInt()) {
                Printer.Invalid.integerValue();
                scanner.next();
            }
            move = scanner.nextInt();
            if (move < 1) {
                Printer.Invalid.position();
            }
        } while (move < 1);
        return move;
    }
}
