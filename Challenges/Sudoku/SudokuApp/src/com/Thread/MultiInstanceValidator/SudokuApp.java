/*
Sudoku Project

    Your task is to implement a Java project with the functionality listed below.
    You should use concurrency where possible to improve the performance of your application,
    making sure to apply best practices and to use appropriate design patterns throughout.

    1. Generate an unsolved Sudoku puzzle. (Done - SudokuGenerator.java)

    2. Check whether a certain number could go in a certain cell of an unsolved Sudoku puzzle. (Done - SudokuValidator.java)

    3. Validate an entire solved sudoku puzzle (i.e. return true if the sudoku is valid, or false otherwise) (Done - SudokuValidator.java)

    4. Take an unsolved sudoku puzzle, and return its solution. (Done - SudokuSolver.java)

    5. Support multiple threads requesting a new puzzle or the solution to an existing puzzle, simultaneously. (Kinda done - SudokuApp.java option 6)

    * Improvements needed or bugs that need fixing:
    *
    * 1.    P : Clues amount do not always get respect, most of the time the board created has higher amount of clues than what requested
    *       S : Change the way the board gets created, instead of going through each cell, randomize the initial cell coordinates
    *
    * 2.    P : Option 6 "Rule All 3", when printing each board, is creating a printing collision
    *       S : Integration of time delay could help (???????)
    *
    * 3.    P : Board generation performance has been drastically reduced after implementation of multi-threading in the validator
    *       S : No ideaaaaaaaaaaaaaaaaaaa
    *
    * 4.    P : Clues amount are predefined
    *       S : After solving the board creation problem, implement userCluesCount as parameter
    *
    * 5.    P : Solver currently does not have a multithreading functionality
    *       S : Add an Executor(?) with a shared pool (?) and for each possible number valid in a cell, Runnable/Callable (?) recursive function
    *
    * 6.    P : Be crazy and ask user board size
    *       S : Adjust the code to loop through the board according to board side size instead of a fixed number
    *
    * 7.    P : Solver is not able to determine if there is only one solution or more, at the moment its just printing the first working solution
    *       S : Instead of returning true straight away, let the recursive function keep running while saving the solved board in a list of solutions
    *           and at the end, instead of returning a boolean, return a board object if the length of the list if equal to 1, if more return a null
    *           Object suggesting invalid Sudoku board.
    *
    * 8.    P : Absolutely horrific main function
    *       S : Make the code cleaner by splitting to another file called for example SudokuFlow.java
    *
 */


package com.Thread.MultiInstanceValidator;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SudokuApp {

    /**
     * Main entry point of the application. Presents a menu to the user and processes the user's choice
     * to perform various Sudoku-related operations such as validation, solving, and generation.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Sudoku Solver/Validator");
        System.out.println("Please select an option:");
        System.out.println("1. Test Validate a Sudoku board");
        System.out.println("2. Validate a Sudoku board");
        System.out.println("3. Test Solve a Sudoku board");
        System.out.println("4. Solve a Sudoku board");
        System.out.println("5. Generate a Sudoku board");
        System.out.println("6. Run all 3 (Multi-Threading)");
        System.out.print("Enter your choice (1-6): ");

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
            case 6:
                runAllThree();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                break;
        }
        scanner.close();
    }

    /**
     * Tests the validation of a predefined Sudoku board. This method is used for demonstration
     * or testing purposes to show how validation functionality works without requiring user input.
     */
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

    /**
     * Validates a Sudoku board based on user input. The user will be prompted to enter the Sudoku board,
     * and this method validates whether the provided board is a valid Sudoku configuration.
     * @param scanner Scanner used for reading user input.
     */
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

    /**
     * Tests the solving of a predefined Sudoku board. Similar to testValidateBoard, this method is
     * intended for demonstration or testing, showing the solve functionality without user-provided puzzles.
     */
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

    /**
     * Solves a Sudoku board based on user input. The user provides an unsolved board, and this method
     * returns the solved board, if solvable.
     * @param scanner Scanner used for reading user input.
     */
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

    /**
     * Generates a random Sudoku board. This method provides the user with a new, randomly generated
     * unsolved Sudoku puzzle.
     */
    private static void generateBoard() {
        SudokuGenerator generator = new SudokuGenerator();
        generator.generatePuzzle(50);
        generator.getBoard().printBoard();
    }

    /**
     * Executes multiple Sudoku operations concurrently, demonstrating the application's multi-threading
     * capabilities. This may include generating, validating, and solving Sudoku puzzles in parallel.
     */
    private static void runAllThree() {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(() -> {
            System.out.println("\nStarting testValidateBoard...");
            testValidateBoard();
            System.out.println("\ntestValidateBoard completed.");
        });

        executor.submit(() -> {
            System.out.println("\nStarting testSolveBoard...");
            testSolveBoard();
            System.out.println("\ntestSolveBoard completed.");
        });

        executor.submit(() -> {
            System.out.println("\nStarting generateBoard...");
            generateBoard();
            System.out.println("\ngenerateBoard completed.");
        });

        executor.shutdown();
    }
}
