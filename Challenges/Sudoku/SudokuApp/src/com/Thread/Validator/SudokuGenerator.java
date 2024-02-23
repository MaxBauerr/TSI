package com.Thread.Validator;

import java.util.ArrayList;
import java.util.Collections;

public class SudokuGenerator {
    private final SudokuBoard board;
    private final SudokuSolver solver;
    private final SudokuValidator validator;

    public SudokuGenerator() {
        this.board = new SudokuBoard();
        this.solver = new SudokuSolver();
        this.validator = new SudokuValidator();
        generateSolution();
    }

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

    private boolean hasUniqueSolution() {
        SudokuBoard copyBoard = new SudokuBoard();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                copyBoard.setValue(r, c, board.getValue(r, c));
            }
        }

        return countSolutions(copyBoard, 0) == 1;
    }

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

    public SudokuBoard getBoard() {
        return this.board;
    }
}
