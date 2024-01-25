import java.util.Scanner;

public class Minesweeper {
    private final Board board;
    private boolean gameStatus;

    public Minesweeper(int squareSize, int numMines) {
        this.gameStatus = true; // Game status true indicates active loop
        board = new Board(squareSize, numMines);  // Create board
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
            winCondition();
            checkGameStatus();
        }
    }

    private void playMove(int row, int col) {
        if (board.isMineHere(row, col)) {
            gameStatus = false;
            System.out.println("Game Over! You hit a mine!");
        }  else {
            System.out.println("Safe move!");
            System.out.println("Updating board...");
            board.changeBoard(row, col);;
        }
    }

    private void winCondition() {
        if (board.getSafeCells() == 0) {
            gameStatus = false;
            System.out.println("Congratulations! You won the game!");
            board.displayEndBoard();
        }
    }

    private boolean returnStatus(){
        return gameStatus;
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
