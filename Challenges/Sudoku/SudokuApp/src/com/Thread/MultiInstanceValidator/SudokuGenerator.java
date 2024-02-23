package com.Thread.MultiInstanceValidator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Responsible for generating Sudoku puzzles. This includes generating a complete solution
 * and then removing a certain number of clues to create a puzzle with a unique solution.
 * Please note, the removal of clues is not guaranteed as it will affect the solution count.
 */
public class SudokuGenerator {
    private final SudokuBoard board;
    private final SudokuSolver solver;
    private final SudokuValidator validator;

    /**
     * Initializes a new SudokuGenerator instance. This includes initializing a new SudokuBoard,
     * SudokuSolver, and SudokuValidator, and generating a complete Sudoku solution.
     */
    public SudokuGenerator() {
        this.board = new SudokuBoard();
        this.solver = new SudokuSolver();
        this.validator = new SudokuValidator();
        generateSolution();
    }

    /**
     * Generates a complete solution for the Sudoku board. This method fills the board with valid
     * values ensuring a valid Sudoku configuration.
     */
    private void generateSolution() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.getValue(row, col) == 0) {
                    ArrayList<Integer> numbers = new ArrayList<>();
                    for (int i = 1; i <= 9; i++) {
                        numbers.add(i);
                    }
                    Collections.shuffle(numbers);

                    for (int num : numbers) {
                        if (validator.isValid(board, row, col, num)) {
                            board.setValue(row, col, num);

                            if (solver.solve(board)) {
                                return;
                            } else {
                                board.setValue(row, col, 0); // Backtrack
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Generates a Sudoku puzzle by removing a specified number of clues from a complete solution,
     * ensuring that the resulting puzzle has a unique solution. Please note removal of cells is not
     * guaranteed if it will affect the solution quantity or the resolvability of the puzzle.
     * @param clues The number of clues to remain in the puzzle. Must be less than the total number
     *              of cells (81) for a standard Sudoku puzzle.
     */
    public void generatePuzzle(int clues) {
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = 0; i < 81; i++) positions.add(i);
        Collections.shuffle(positions);

        while (positions.size() > clues) {
            int position = positions.removeLast();
            int row = position / 9;
            int col = position % 9;
            int temp = board.getValue(row, col);
            board.setValue(row, col, 0);

            if (!hasUniqueSolution()) {
                board.setValue(row, col, temp);
            }
        }
    }

    /**
     * Checks if the current board configuration has a unique solution by attempting to solve it
     * and ensuring only one solution exists.
     * @return {@code true} if the board has a unique solution, {@code false} otherwise.
     */
    private boolean hasUniqueSolution() {
        SudokuBoard copyBoard = new SudokuBoard();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                copyBoard.setValue(r, c, board.getValue(r, c));
            }
        }

        return countSolutions(copyBoard, 0) == 1;
    }

    /**
     * Recursively counts the number of solutions for a given Sudoku board starting from a
     * specified index. Used to determine if a puzzle has a unique solution.
     * @param board The Sudoku board to check.
     * @param index The current index being checked, from 0 to 80 (inclusive) for a standard
     *              9x9 Sudoku board.
     * @return The number of solutions found for the board configuration.
     */
    private int countSolutions(SudokuBoard board, int index) {
        if (index == 81) return 1;

        int row = index / 9;
        int col = index % 9;

        if (board.getValue(row, col) != 0) {
            return countSolutions(board, index + 1);
        }

        int count = 0;
        for (int num = 1; num <= 9 && count < 2; num++) {
            if (validator.isValid(board, row, col, num)) {
                board.setValue(row, col, num);
                count += countSolutions(board, index + 1);
                board.setValue(row, col, 0);
            }
        }

        return count;
    }

    /**
     * Returns the current Sudoku board.
     * @return The current instance of {@link SudokuBoard} used by the generator.
     */
    public SudokuBoard getBoard() {
        return this.board;
    }
}
