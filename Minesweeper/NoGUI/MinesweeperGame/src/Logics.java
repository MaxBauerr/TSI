import java.util.Random;
import java.util.Scanner;

public class Logics {
    private static final Random rand = new Random();

    public static int calculateMines(int boardSize, int gameDifficulty) {
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

    public static int getValidBoardSize(Scanner scanner) { // Use passed scanner
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

    public static int chooseDifficulty(Scanner scanner) { // Use passed scanner
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

    public static int hintCount(int difficultyChoice) {
        return difficultyChoice*3;
    }
}
