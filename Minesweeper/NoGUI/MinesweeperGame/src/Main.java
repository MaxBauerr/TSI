import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Minesweeper"); // Game start message
        System.out.println("Please input the size of the board"); // Request size of the board, single sized which will used for both Array length and Array quantity
        Scanner sc = new Scanner(System.in); // Scanner initialization
        int boardSize = sc.nextInt(); // ADD ERROR EXCEPTIONS
        System.out.println("Please input the amount of mines");
        int minesCount = sc.nextInt(); // ADD ERROR EXCEPTIONS
        System.out.println("Loading...");
        Minesweeper game = new Minesweeper(boardSize, minesCount); // Create the Minesweeper Object and start the game
        game.play(); // Run game
    }
}