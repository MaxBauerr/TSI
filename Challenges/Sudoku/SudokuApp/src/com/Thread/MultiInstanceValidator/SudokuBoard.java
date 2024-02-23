package com.Thread.MultiInstanceValidator;

/**
 * Represents a 9x9 Sudoku board. This class provides methods to manipulate and display the board's state.
 */
public class SudokuBoard {
    private final int[][] board;

    /**
     * Initializes a new Sudoku board with a 9x9 grid. All cells are initially empty, indicated by the value 0.
     */
    public SudokuBoard() {
        this.board = new int[9][9];
    }

    /**
     * Retrieves the value from a specific cell on the board.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return The value stored at the specified cell. A value of 0 indicates an empty cell.
     */
    public int getValue(int row, int col) {
        return board[row][col];
    }

    /**
     * Sets a value in a specific cell of the board. The value must be between 0 and 9, inclusive.
     * A value of 0 indicates that the cell is empty.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @param value The value to set in the specified cell. Must be between 0 and 9, inclusive.
     * @throws IllegalArgumentException If the provided value is outside the range of 0 to 9.
     */
    public void setValue(int row, int col, int value) {
        if (value < 0 || value > 9) {
            throw new IllegalArgumentException("Value must be between 0 and 9.");
        }
        board[row][col] = value;
    }

    /**
     * Prints the current state of the Sudoku board to the console. Each row is printed on a new line,
     * with a '|' character visually separating each 3x3 subgrid within the row. Rows are separated by
     * lines to visually indicate 3x3 subgrids. Empty cells are represented by '.'.
     */
    public void printBoard() {
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][col] == 0 ? "." : board[row][col]);
            }
            System.out.println();
        }
    }
}
