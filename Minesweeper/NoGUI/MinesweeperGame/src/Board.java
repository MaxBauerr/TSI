import java.util.*;

public class Board {
    private final String[][] board;
    private final boolean[][] mines;
    private final int squareSize;
    private final int numMines;
    private int hintCount;

    public Board(int squareSize, int numMines, int hints) {
        this.squareSize = squareSize;
        this.numMines = numMines;
        this.hintCount = hints;
        board = new String[squareSize][squareSize];
        mines = new boolean[squareSize][squareSize];
        createBoard();
    }

    public void createBoard() {
        for (int x = 0; x < squareSize; x++) {
            for (int y = 0; y < squareSize; y++) {
                board[x][y] = "[-]";
            }
        }
    }

    public void placeMines(int firstRow, int firstCol) {
        Random random = new Random();
        int minesCount = 0;
        while (minesCount < numMines) {
            int row = random.nextInt(squareSize);
            int col = random.nextInt(squareSize);
            if (!mines[row][col] && !(row == firstRow && col == firstCol) && !isAdjacent(row, col, firstRow, firstCol)) { // Check if the random mine position is not the first move or adjacent to it
                mines[row][col] = true;
                minesCount++;
            }
        }
    }

    private boolean isAdjacent(int row, int col, int firstRow, int firstCol) {
        return Math.abs(row - firstRow) <= 1 && Math.abs(col - firstCol) <= 1;
    }

    public void displayBoard() {
        int countDisplay = getSafeCells();
        Printer.Statement.gameStats(countDisplay, getNumMines());
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
            Printer.Invalid.position();
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

    public void displayEndBoard(boolean isWin) {
        int countDisplay = getSafeCells();
        Printer.Statement.gameStats(countDisplay, getNumMines());
        for (int r = 0; r < squareSize; r++) {
            for (int c = 0; c < squareSize; c++) {
                if (Objects.equals(board[r][c], "[-]")) {
                    if (mines[r][c] && !isWin) {
                        board[r][c] = "\033[31m[*]\033[0m"; // If the player has lost, display mines in red
                    } else if (!mines[r][c] && !isWin) {
                        board[r][c] = "\033[34m[+]\033[0m"; // If the player has lost, display not-discovered non-mines cells in blue
                    }
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

    public int getNumMines() {
        return numMines;
    }

    public void cellFlag(int row, int col) {
        if (Objects.equals(board[row][col], "[-]")) {
            board[row][col] = "\033[31m[?]\033[0m";
        } else {
            Printer.Invalid.discoveredCell();
        }
    }

    public void cellUnflag(int row, int col) {
        if (Objects.equals(board[row][col], "\033[31m[?]\033[0m")) {
            board[row][col] = "[-]";
        } else {
            Printer.Invalid.notFlaggedCell();
        }
    }

    public void giveHint() {
        if (hintCount > 0) {
            List<int[]> unopenedCells = new ArrayList<>();
            for (int row = 0; row < squareSize; row++) {
                for (int col = 0; col < squareSize; col++) {
                    if ("[-]".equals(board[row][col])) {
                        unopenedCells.add(new int[]{row, col});
                    }
                }
            }

            if (unopenedCells.isEmpty()) {
                Printer.Statement.hints(hintCount);
                return;
            }

            Random random = new Random();
            int[] selectedCell = unopenedCells.get(random.nextInt(unopenedCells.size()));

            if (mines[selectedCell[0]][selectedCell[1]]) {
                board[selectedCell[0]][selectedCell[1]] = "\033[95m[*]\033[0m"; // Mine in purple
            } else {
                revealHintedSafeCell(selectedCell[0], selectedCell[1]); // Reveals the cell in blue
            }
            hintCount -= 1;
            Printer.Statement.hints(hintCount);
        } else {
            Printer.Statement.hints(hintCount);
        }

    }

    public int getHintCount() {
        return hintCount;
    }

    private void revealHintedSafeCell(int row, int col) {
        if (!isInBounds(row, col) || !"[-]".equals(board[row][col])) {
            return;
        }
        int adjacentMines = countAdjacentMines(row, col);
        if (adjacentMines == 0) {
            board[row][col] = "\033[95m[0]\033[0m";
            uncoverAdjacentCells(row, col);
        } else {
            board[row][col] = "\033[95m[" + Integer.toString(adjacentMines) + "]\033[0m";
        }
    }

}
