import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Minesweeper");
        System.out.println("Please input the size of the board");
        Scanner sc = new Scanner(System.in);
        int boardSize = sc.nextInt();
        System.out.println("Please input the amount of mines");
        int minesCount = sc.nextInt();
        System.out.println("Loading...");
        Minesweeper game = new Minesweeper(boardSize, minesCount);
        game.play();
    }
}