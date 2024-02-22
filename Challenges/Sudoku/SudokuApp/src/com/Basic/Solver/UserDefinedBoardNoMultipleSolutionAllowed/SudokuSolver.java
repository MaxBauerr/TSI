package com.Basic.Solver.UserDefinedBoardNoMultipleSolutionAllowed;

public class SudokuSolver {

    private SudokuBoard solution = null;

    public boolean solve(SudokuBoard board) {
        for (int row = 0; row < 9; row++) { // For each row in the current stated board
            for (int col = 0; col < 9; col++) { // For each column in the current stated board
                if (board.getValue(row, col) == 0) { // Check if in the coordinates {row,column} value = 0


                    for (int num = 1; num <= 9; num++) { // For each possible number between 1 and 9, try to fill the X number
                                                        // and recursively call to solve ont the new board
                        if (isValid(board, row, col, num)) {
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

    private  boolean isValid(SudokuBoard board, int currentRow, int currentCol, int numberToCheck) {
        return !isInRow(board, currentRow, numberToCheck) &&
                !isInCol(board, currentCol, numberToCheck) &&
                !isInBox(board, currentRow, currentCol, numberToCheck);
    }


    private boolean isInRow(SudokuBoard board, int row, int num) {
        for (int col = 0; col < 9; col++) {
            if (board.getValue(row, col) == num) {
                return true;
            }
        }
        return false;
    }

    private boolean isInCol(SudokuBoard board, int col, int num) {
        for (int row = 0; row < 9; row++) {
            if (board.getValue(row, col) == num) {
                return true;
            }
        }
        return false;
    }

    private boolean isInBox(SudokuBoard board, int currentRow, int currentCol, int numberToCheck) {
        int subgridStartRow = currentRow - currentRow % 3;
        int subgridStartCol = currentCol - currentCol % 3;
        for (int rowOffset = 0; rowOffset < 3; rowOffset++) {
            for (int colOffset = 0; colOffset < 3; colOffset++) {
                if (board.getValue(subgridStartRow + rowOffset, subgridStartCol + colOffset) == numberToCheck) {
                    return true;
                }
            }
        }
        return false;
    }
}
