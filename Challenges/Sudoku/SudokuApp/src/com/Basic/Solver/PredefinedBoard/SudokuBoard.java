package com.Basic.Solver.PredefinedBoard;

public class SudokuBoard {
    private final int[][] board;

    public SudokuBoard() {
        this.board = new int[9][9];
    }

    public int getValue(int row, int col) {
        return board[row][col];
    }

    public void setValue(int row, int col, int value) {
        if (value < 0 || value > 9) {
            throw new IllegalArgumentException("Value must be between 0 and 9.");
        }
        board[row][col] = value;
    }

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
        System.out.println("---------------------------------------------------------------------------------------------");
    }
}
