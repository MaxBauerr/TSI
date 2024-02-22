package com.Basic.SolverValidator;

public class SudokuSolver {

    SudokuValidator validator = new SudokuValidator();
    public boolean solve(SudokuBoard board) {
        for (int row = 0; row < 9; row++) { // For each row in the current stated board
            for (int col = 0; col < 9; col++) { // For each column in the current stated board
                if (board.getValue(row, col) == 0) { // Check if in the coordinates {row,column} value = 0


                    for (int num = 1; num <= 9; num++) { // For each possible number between 1 and 9, try to fill the X number
                        // and recursively call to solve ont the new board
                        if (validator.isValid(board, row, col, num)) {
                            board.setValue(row, col, num);
                            if (solve(board)) {
                                return true;
                            }
                            board.setValue(row, col, 0);
                        }
                    }
                    return false;
                }
                // If the value in the coordinates {row,column} does not equal to 0, carry on
            }
        }
        return true;
    }
}