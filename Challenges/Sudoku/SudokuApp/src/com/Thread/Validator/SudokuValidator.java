package com.Thread.Validator;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SudokuValidator {

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

    private boolean isInRow(SudokuBoard board, int row, int num, int currentCol) {
        for (int col = 0; col < 9; col++) {
            if (col != currentCol && board.getValue(row, col) == num) {
                return true;
            }
        }
        return false;
    }

    private boolean isInCol(SudokuBoard board, int col, int num, int currentRow) {
        for (int row = 0; row < 9; row++) {
            if (row != currentRow && board.getValue(row, col) == num) {
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
