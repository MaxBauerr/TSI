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
        createBoard(); // Create board based on initial game rules.
    }

    public void createBoard() { // Creates a 2D array that is by default [-] on all cells.
        for (int x = 0; x < squareSize; x++) {
            for (int y = 0; y < squareSize; y++) {
                board[x][y] = Printer.Format.whiteSafeCell();
            }
        }
    }

    public void placeMines(int firstRow, int firstCol) {
        Random random = new Random(); // Randomly allocate mines in the game taking in account first player coordinates
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

    private boolean isAdjacent(int row, int col, int firstRow, int firstCol) { // Check if current position and mines position are adjacent to each other
        return Math.abs(row - firstRow) <= 1 && Math.abs(col - firstCol) <= 1;
    }

    public void displayBoard() { // Display initial board after creation, first player move and mines allocation.
        int countDisplay = getSafeCells();
        Printer.Statement.gameStats(countDisplay, getNumMines(), getFlaggedCells(), getHintedMineCells());
        for (int r = 0; r < squareSize; r++) {
            for (int c = 0; c < squareSize; c++) {
                System.out.print(board[r][c] + " "); // Separate cells with an empty space for readability
            }
            System.out.println();
        }
    }

    public boolean isMineHere(int row, int col) { // Check if current coordinates are also present within the mines list
        if (row >= 0 && row < squareSize && col >= 0 && col < squareSize) {
            return mines[row][col];
        } else {
            Printer.Invalid.position();
            return false;
        }
    }

    public void changeBoard(int row, int col) { // Update board to new one after each player action
        if (!isInBounds(row, col) || !Printer.Format.whiteSafeCell().equals(board[row][col])) {
            return;
        }
        int adjacentMines = countAdjacentMines(row, col); // Check how many mines are adjacent (max 8) to the current checked cell.
        if (adjacentMines == 0) {
            board[row][col] = Printer.Format.safeCell(adjacentMines, false);
            uncoverAdjacentCells(row, col);
        } else {
            board[row][col] = Printer.Format.safeCell(adjacentMines, false);
        }
    }

    public void displayEndBoard(boolean isWin) { // Board fully displayed and disclosed once the game is over.
        int countDisplay = getSafeCells();
        Printer.Statement.gameStats(countDisplay, getNumMines(), getFlaggedCells(), getHintedMineCells());
        for (int r = 0; r < squareSize; r++) {
            for (int c = 0; c < squareSize; c++) {
                if (Objects.equals(board[r][c], Printer.Format.whiteSafeCell())) {
                    if (mines[r][c] && !isWin) {
                        board[r][c] = Printer.Format.minesNotHintCell(); // If the player has lost, display mines that are "not-hints" in red
                    } else if (!mines[r][c] && !isWin) {
                        board[r][c] = Printer.Format.correctNonDiscoveredCell(); // If the player has lost, display "not-discovered" "non-mines" "non-hints" cells in blue
                    }
                }
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    private void uncoverAdjacentCells(int row, int col) { // If current cell is a safe cell, show adjacent cell value except for mine. If adjacent cell value is 0, repeat process for that cell.
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isInBounds(row + i, col + j) && Printer.Format.whiteSafeCell().equals(board[row + i][col + j])) {
                    changeBoard(row + i, col + j);
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) { // Count total mines adjacent to current position, max 8 possible.
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

    private boolean isInBounds(int row, int col) { // Check if the current location checked is within the grid area
        return row >= 0 && row < squareSize && col >= 0 && col < squareSize;
    }

    public int getSafeCells() { // Count current amount of safe cells left in the game, keeps consideration of flagged cells.
        return (gridSearcher(Printer.Format.whiteSafeCell()) - getNumMines() + getFlaggedCells() + getHintedSafeCell() + getHintedMineCells());
    }

    public int getFlaggedCells() { // Count current amount of flagged cells placed in the game
        return gridSearcher(Printer.Format.redFlag());
    }

    public int getHintedMineCells() { // Count current amount of hinter mine cells
        return gridSearcher(Printer.Format.hintedMines());
    }

    public int getHintedSafeCell() {
        int totalHintedSafeCellCount = 0;
        for (int x = 1; x < 9; x++) {
            String[] formatArray = Printer.Format.hintedSafeCell();
            String format = formatArray[0] + x + formatArray[1]; // Concatenate the strings with x in the middle
            totalHintedSafeCellCount += gridSearcher(format);
        }
        return totalHintedSafeCellCount; // Return the total count
    }

    public int gridSearcher(String format) { // Grid search required format in the grid
        int count = 0;
        for (int x = 0; x < squareSize; x++) {
            for (int y = 0; y < squareSize; y++) {
                if (Objects.equals(board[x][y], (format) )) {
                    ++count;
                }
            }
        }
        return count;
    }

    public int getNumMines() {
        return numMines;
    } // Count current numbers of possible mines left in game

    public void cellFlag(int row, int col) { // Overwrites current non-discovered cell with a Flag
        if (Objects.equals(board[row][col], Printer.Format.whiteSafeCell())) {
            board[row][col] = Printer.Format.redFlag();
        } else {
            Printer.Invalid.discoveredCell();
        }
    }

    public void cellUnflag(int row, int col) { // Overwrites current flagged cell with a non-discovered cell
        if (Objects.equals(board[row][col], Printer.Format.redFlag())) {
            board[row][col] = Printer.Format.whiteSafeCell();
        } else {
            Printer.Invalid.notFlaggedCell();
        }
    }

    public void giveHint() { // Randomly select a non-discovered cell and show true Value. If Hint count is down to 0, decline operation.
        if (hintCount > 0) {
            List<int[]> unopenedCells = new ArrayList<>();
            for (int row = 0; row < squareSize; row++) {
                for (int col = 0; col < squareSize; col++) {
                    if (Printer.Format.whiteSafeCell().equals(board[row][col])) {
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
                board[selectedCell[0]][selectedCell[1]] = Printer.Format.hintedMines(); // Mine in purple
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
    } // Check remaining available hints.

    private void revealHintedSafeCell(int row, int col) { // If hint randomly selects a safe cells, apply safe rules as if player has played that move.
        if (!isInBounds(row, col) || !Printer.Format.whiteSafeCell().equals(board[row][col])) {
            return;
        }
        int adjacentMines = countAdjacentMines(row, col);
        if (adjacentMines == 0) {
            board[row][col] = Printer.Format.safeCell(adjacentMines, true);
            uncoverAdjacentCells(row, col);
        } else {
            board[row][col] = Printer.Format.safeCell(adjacentMines, true);
        }
    }

}
