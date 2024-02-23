package com.Thread.MultiInstanceValidator;

/**
 * Implements the logic to solve a given Sudoku puzzle using a backtracking algorithm.
 */
public class SudokuSolver {

    SudokuValidator validator = new SudokuValidator();

    /**
     * Attempts to solve the provided Sudoku board. It uses a backtracking algorithm that
     * tries to fill each cell with a valid number (1-9) and recursively checks if this leads
     * to a solution. If a number does not lead to a solution, it backtracks and tries the next
     * number.
     * @param board The SudokuBoard instance that needs to be solved.
     * @return {@code true} if the board is successfully solved, {@code false} if no solution exists.
     */
    public boolean solve(SudokuBoard board) {
        for (int row = 0; row < 9; row++) { // For each row
            for (int col = 0; col < 9; col++) { // For each column
                if (board.getValue(row, col) == 0) { // Check if cell is empty

                    for (int num = 1; num <= 9; num++) { // Try all possible numbers
                        if (validator.isValid(board, row, col, num)) { // Check if num is valid
                            board.setValue(row, col, num); // Place num
                            if (solve(board)) { // Recursively solve
                                return true; // If solved, return true
                            }
                            board.setValue(row, col, 0); // Backtrack
                        }
                    }
                    return false; // No valid number found, backtrack
                }
            }
        }
        return true; // Solved
    }
}
