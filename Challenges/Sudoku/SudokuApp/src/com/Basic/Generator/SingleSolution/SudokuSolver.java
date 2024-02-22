package com.Basic.Generator.SingleSolution;

public class SudokuSolver {

    SudokuValidator validator = new SudokuValidator();
    public boolean solve(SudokuBoard board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.getValue(row, col) == 0) {
                    for (int num = 1; num <= 9; num++) {
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
            }
        }
        return true;
    }
}