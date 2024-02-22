package com.Basic.Validator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SudokuApp {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        SudokuBoard sudokuBoard = new SudokuBoard();
        int[][] userDefinedBoard = new int[9][9];

        System.out.println("Enter your Sudoku board you would like to validate):");

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int cellValue = 0;
                while (cellValue < 1 || cellValue > 9) {
                    System.out.printf("Enter value for cell [%d,%d] (1-9): ", row + 1, col + 1);
                    try {
                        cellValue = scanner.nextInt();
                        if (cellValue < 1 || cellValue > 9) {
                            System.out.println("Invalid input: Numbers must be between 1 and 9. Please try again.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input: Please enter a valid integer between 1 and 9.");
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

        System.out.println("Requested Validation for this Sudoku board:");
        sudokuBoard.printBoard();
        SudokuValidator validator = new SudokuValidator();
        if (validator.isBoardValid(sudokuBoard)) {
            System.out.println("This board is valid");
        } else {
            System.out.println("This board is invalid");
        }
        scanner.close();
    }
}
