package com.Basic.Solver.UserDefinedBoard;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SudokuApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SudokuBoard sudokuBoard = new SudokuBoard();
        int[][] userDefinedBoard = new int[9][9];

        System.out.println("Enter your Sudoku board (use 0 for empty cells):");

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int cellValue = -1;
                while (cellValue < 0 || cellValue > 9) {
                    System.out.printf("Enter value for cell [%d,%d] (0-9): ", row + 1, col + 1);
                    try {
                        cellValue = scanner.nextInt();
                        if (cellValue < 0 || cellValue > 9) {
                            System.out.println("Invalid input: Numbers must be between 0 and 9. Please try again.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input: Please enter a valid integer between 0 and 9.");
                        scanner.next();
                    }
                }
                userDefinedBoard[row][col] = cellValue;
            }
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                sudokuBoard.setValue(row, col, userDefinedBoard[row][col]);
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
        scanner.close();
    }
}
