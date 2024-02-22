package com.Basic.Solver.PredefinedBoard;

public class SudokuApp {

    public static void main(String[] args) throws InterruptedException { // Remove Exception after testing
        SudokuBoard sudokuBoard = new SudokuBoard();

        int[][] predefinedBoard = {
                {1, 0, 0, 0, 2, 0, 3, 0, 4},
                {0, 4, 0, 5, 6, 0, 7, 8, 0},
                {0, 9, 0, 0, 8, 0, 0, 0, 2},
                {0, 0, 3, 0, 0, 8, 0, 0, 7},
                {0, 0, 7, 0, 0, 0, 6, 0, 0},
                {8, 0, 0, 2, 0, 0, 9, 0, 0},
                {6, 0, 0, 0, 1, 0, 0, 3, 0},
                {0, 5, 8, 0, 9, 3, 0, 7, 0},
                {2, 0, 1, 0, 4, 0, 0, 0, 6}
        };

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                sudokuBoard.setValue(row, col, predefinedBoard[row][col]);
            }
        }

        System.out.println("Original Sudoku board:");
        sudokuBoard.printBoard();

        SudokuSolver solver = new SudokuSolver();
        if (solver.solve(sudokuBoard)) {
            System.out.println("Solved Sudoku board:");
            sudokuBoard.printBoard();
        } else {
            System.out.println("No solution exists for the given Sudoku board.");
        }
    }
}

