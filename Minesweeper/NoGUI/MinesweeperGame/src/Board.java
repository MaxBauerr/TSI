import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Board {
    private final String[][] board;
    private final boolean[][] mines;
    private final int squareSize;
    private final int numMines;
    private int loopCounter = 0;

    public Board(int squareSize, int numMines) {
        this.squareSize = squareSize;
        this.numMines = numMines;
        board = new String[squareSize][squareSize];
        mines = new boolean[squareSize][squareSize];
        createBoard();
        placeMines();
    }

    public void createBoard() {
        for (int x = 0; x < squareSize; x++) {
            for (int y = 0; y < squareSize; y++) {
                board[x][y] = "[-]";
            }
        }
    }

    private void placeMines() {
        int counter = 0;
        Random random = new Random();
        int minesCount = 0;
        while (minesCount < numMines) {
            int row = random.nextInt(squareSize);
            int col = random.nextInt(squareSize);
            if (!mines[row][col]) {
                mines[row][col] = true;
                minesCount++;
            }
        }
    }

    public void displayBoard() {
        if (loopCounter >= 1) {
            int countDisplay = getSafeCells();
            System.out.println("The total remaining cells that are safe is : " + countDisplay);
        }
        loopCounter += 1;
        for (int r = 0; r < squareSize; r++) {
            for (int c = 0; c < squareSize; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    public boolean isMineHere(int row, int col) {
        if (row >= 0 && row < squareSize && col >= 0 && col < squareSize) {
            return mines[row][col];
        } else {
            System.out.println("Invalid position");
            return false;
        }
    }

    public void changeBoard(int row, int col) {
        if (!isInBounds(row, col) || !"[-]".equals(board[row][col])) {
            return;
        }
        int adjacentMines = countAdjacentMines(row, col);
        if (adjacentMines == 0) {
            board[row][col] = "\033[32m[0]\033[0m";
            uncoverAdjacentCells(row, col);
        } else {
            board[row][col] = "\033[33m[" + Integer.toString(adjacentMines) + "]\033[0m";
        }
    }

    public void displayEndBoard() {
        if (loopCounter >= 1) {
            int countDisplay = getSafeCells();
            System.out.println("The total remaining cells that are safe is : " + countDisplay);
        }
        loopCounter += 1;
        for (int r = 0; r < squareSize; r++) {
            for (int c = 0; c < squareSize; c++) {
                if (Objects.equals(board[r][c], "[-]")) {
                    board[r][c] = "\033[31m[*]\033[0m";
                }
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    private void uncoverAdjacentCells(int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isInBounds(row + i, col + j) && "[-]".equals(board[row + i][col + j])) {
                    changeBoard(row + i, col + j);
                }
            }
        }
    }


    private int countAdjacentMines(int row, int col) {
        int mineCount = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isInBounds(row + i, col + j) && mines[row + i][col + j]) {
                    mineCount++;
                }
            }
        }
        return mineCount;
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < squareSize && col >= 0 && col < squareSize;
    }

    public int getSafeCells() {
        int safeCells = 0;
        for (int x = 0; x < squareSize; x++) {
            for (int y = 0; y < squareSize; y++) {
                if (Objects.equals(board[x][y], "[-]")) {
                    safeCells += 1;
                };
            }
        }
        return safeCells-numMines;
    }
}
