package com.Basic.SolverValidator;

public class SudokuValidator {

    boolean isBoardValid(SudokuBoard board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                //System.out.println("Checking if " + board.getValue(row,col) + " is valid at position ["+(row+1)+"]["+(col+1)+"]");
                if(!isValid(board, row, col, board.getValue(row,col))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(SudokuBoard board, int currentRow, int currentCol, int numberToCheck) {
        return !isInRow(board, currentRow, numberToCheck, currentCol) &&
                !isInCol(board, currentCol, numberToCheck, currentRow) &&
                !isInBox(board, currentRow, currentCol, numberToCheck);
    }


    private boolean isInRow(SudokuBoard board, int row, int num, int currentCol) {
        for (int col = 0; col < 9; col++) {
            if (!(col==currentCol)) {
                if (board.getValue(row, col) == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isInCol(SudokuBoard board, int col, int num, int currentRow) {
        for (int row = 0; row < 9; row++) {
            if(!(row==currentRow)) {
                if (board.getValue(row, col) == num) {
                    return true;
                }
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
                if (!(actualRow == currentRow && actualCol == currentCol)) {
                    if (board.getValue(subgridStartRow + rowOffset, subgridStartCol + colOffset) == numberToCheck) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
