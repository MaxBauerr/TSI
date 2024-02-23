package com.Thread.MultiInstanceValidator;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Provides validation functionality for Sudoku puzzles. This includes checking if the entire board is valid
 * and if a particular number can be placed in a specific cell without violating Sudoku rules.
 */
public class SudokuValidator {

    /**
     * Validates the entire Sudoku board to ensure all placed numbers do not violate Sudoku rules.
     * @param board The SudokuBoard to be validated.
     * @return {@code true} if the board is valid, {@code false} otherwise.
     */
    boolean isBoardValid(SudokuBoard board) {


        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                final int num = board.getValue(row, col);

                if (!isValid(board, row, col, num)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Validates if a specific number can be placed in a specific cell of the Sudoku board without
     * violating Sudoku rules. This method uses concurrency to check row, column, and box constraints
     * in parallel.
     * @param board The SudokuBoard to be validated.
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @param num The number to be placed in the cell.
     * @return {@code true} if placing {@code num} in cell {@code row}, {@code col} does not violate any constraints, {@code false} otherwise.
     */
    public boolean isValid(SudokuBoard board, int row, int col, int num) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Callable<Boolean> rowCheck = () -> !isInRow(board, row, num, col);
        Callable<Boolean> colCheck = () -> !isInCol(board, col, num, row);
        Callable<Boolean> boxCheck = () -> !isInBox(board, row, col, num);

        try {
            Future<Boolean> rowResult = executor.submit(rowCheck);
            Future<Boolean> colResult = executor.submit(colCheck);
            Future<Boolean> boxResult = executor.submit(boxCheck);

            executor.shutdown();
            return rowResult.get() && colResult.get() && boxResult.get();
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if a given number is already present in the specified row, excluding the current cell.
     * @param board The SudokuBoard to be checked.
     * @param row The row to check.
     * @param num The number to check for.
     * @param currentCol The column index of the current cell, which is excluded from the check.
     * @return {@code true} if {@code num} is found in {@code row}, {@code false} otherwise.
     */
    private boolean isInRow(SudokuBoard board, int row, int num, int currentCol) {
        for (int col = 0; col < 9; col++) {
            if (col != currentCol && board.getValue(row, col) == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a given number is already present in the specified column, excluding the current cell.
     * @param board The SudokuBoard to be checked.
     * @param col The column to check.
     * @param num The number to check for.
     * @param currentRow The row index of the current cell, which is excluded from the check.
     * @return {@code true} if {@code num} is found in {@code col}, {@code false} otherwise.
     */
    private boolean isInCol(SudokuBoard board, int col, int num, int currentRow) {
        for (int row = 0; row < 9; row++) {
            if (row != currentRow && board.getValue(row, col) == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a given number is already present in the 3x3 subgrid that includes the current cell, excluding the cell itself.
     * @param board The SudokuBoard to be checked.
     * @param currentRow The row index of the current cell.
     * @param currentCol The column index of the current cell.
     * @param numberToCheck The number to check for in the subgrid.
     * @return {@code true} if {@code numberToCheck} is found in the subgrid, {@code false} otherwise.
     */
    private boolean isInBox(SudokuBoard board, int currentRow, int currentCol, int numberToCheck) {
        int subgridStartRow = currentRow - currentRow % 3;
        int subgridStartCol = currentCol - currentCol % 3;
        for (int rowOffset = 0; rowOffset < 3; rowOffset++) {
            for (int colOffset = 0; colOffset < 3; colOffset++) {
                int actualRow = subgridStartRow + rowOffset;
                int actualCol = subgridStartCol + colOffset;
                if (!(actualRow == currentRow && actualCol == currentCol) &&
                        board.getValue(actualRow, actualCol) == numberToCheck) {
                    return true;
                }
            }
        }
        return false;
    }
}
