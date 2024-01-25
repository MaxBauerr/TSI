import java.util.Scanner;

public class Minesweeper {
    private final Board board;
    private boolean gameStatus;

    public Minesweeper(int squareSize, int numMines) {
        this.gameStatus = true;
        board = new Board(squareSize, numMines);
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        while (gameStatus) {
            board.displayBoard();
            System.out.println("Enter your move");
            System.out.println("Please enter Row : ");
            int row = sc.nextInt();
            System.out.println("Please enter Column : ");
            int col = sc.nextInt();
            playMove((row-1), (col-1));
            checkGameStatus();
        }
    }

    private void playMove(int row, int col) {
        if (board.isMineHere(row, col)) {
            gameStatus = false;
            System.out.println("Game Over! You hit a mine.");
        } else {
            System.out.println("Safe move!");
            System.out.println("Updating board...");
            board.changeBoard(row, col);;
        }
    }

    private boolean returnStatus(){
        return this.gameStatus;
    }

    private void setStatusFalse(){
        this.gameStatus = false;
    }

    private void checkGameStatus() {
        if (!returnStatus()) {
            setStatusFalse();
        }
    }
}
