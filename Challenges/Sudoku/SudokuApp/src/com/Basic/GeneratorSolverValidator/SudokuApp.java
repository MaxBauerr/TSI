package com.Basic.GeneratorSolverValidator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SudokuApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Sudoku Solver/Validator");
        System.out.println("Please select an option:");
        System.out.println("1. Test Validate a Sudoku board");
        System.out.println("2. Validate a Sudoku board");
        System.out.println("3. Test Solve a Sudoku board");
        System.out.println("4. Solve a Sudoku board");
        System.out.println("5. Generate a Sudoku board");
        System.out.print("Enter your choice (1-5: ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                testValidateBoard();
                break;
            case 2:
                validateBoard(scanner);
                break;
            case 3:
                testSolveBoard();
                break;
            case 4:
                solveBoard(scanner);
                break;
            case 5:
                generateBoard();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                break;
        }
        scanner.close();
    }

    private static void testValidateBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        int[][] testValidationBoard = {
                {1, 8, 5, 9, 2, 7, 3, 6, 4},
                {3, 4, 2, 5, 6, 1, 7, 8, 9},
                {7, 9, 6, 3, 8, 4, 5, 1, 2},
                {9, 6, 3, 4, 5, 8, 1, 2, 7},
                {5, 2, 7, 1, 3, 9, 6, 4, 8},
                {8, 1, 4, 2, 7, 6, 9, 5, 3},
                {6, 7, 9, 8, 1, 2, 4, 3, 5},
                {4, 5, 8, 6, 9, 3, 2, 7, 1},
                {2, 3, 1, 7, 4, 5, 8, 9, 6}
        };

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                sudokuBoard.setValue(row, col, testValidationBoard[row][col]);
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

    }

    private static void validateBoard(Scanner scanner) {
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

    private static void testSolveBoard() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        int[][] testSolveBoard = {
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
                sudokuBoard.setValue(row, col, testSolveBoard[row][col]);
            }
        }

        System.out.println("Original Sudoku board:");
        sudokuBoard.printBoard();
        SudokuSolver solver = new SudokuSolver();
        if (solver.solve(sudokuBoard)) {
            System.out.println("Solved Sudoku board:");
            sudokuBoard.printBoard();
        } else {
            System.out.println("Invalid board, there are no solution or too many solutions for the given Sudoku board.");
        }
    }

    private static void solveBoard(Scanner scanner) {
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
            System.out.println("Invalid board, there are no solution or too many solutions for the given Sudoku board.");
        }
        scanner.close();
    }

    private static void generateBoard() {
        SudokuGenerator generator = new SudokuGenerator();
        generator.generatePuzzle(25);
        generator.getBoard().printBoard();
    }
}
