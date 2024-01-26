import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Printer.Statement.welcomeMessage(); // Game start message
        try (Scanner sc = new Scanner(System.in)) { // Scanner initialization with try-with-resources for automatic closing
            int boardSize = Logics.getValidBoardSize(sc); // Pass scanner as argument to method
            int difficultyChoice = Logics.chooseDifficulty(sc); // Pass scanner as argument to method
            int minesCount = Logics.calculateMines(boardSize, difficultyChoice);
            Minesweeper game = new Minesweeper(boardSize, minesCount, difficultyChoice); // Create the Minesweeper game instance
            game.play(sc); // Start the game
        } catch (Exception e) {
            System.out.println("An error occurred during the game: " + e.getMessage());
        }
    }
}

/*
        *** SOLVE THESE ISSUES ***
    * 1. Game stats needs to a have a Flag count added
    * 2. Game stats are incorrectly reading safe cells as they are not taking in account flags on the screen. Solve by adding the present flags to safe cell count.
    * 3. Game stats are incorrectly reading remaining mines cells as they are not taking in account flags on the screen. Solve by removing the present flags to mines cell count.
    * 4. During move inputs, for both row and column need to implement Error Handling as it crashes the game. Add this to the Logics for the game.
    * 5. In Game Menu crashes on wrong input given, like a char. Implement Error handling. Add this to the Logics of the game.
 */