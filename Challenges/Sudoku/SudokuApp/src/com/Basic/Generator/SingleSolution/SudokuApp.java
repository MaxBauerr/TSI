package com.Basic.Generator.SingleSolution;

public class SudokuApp {
    public static void main(String[] args) {
        SudokuGenerator generator = new SudokuGenerator();
        generator.generatePuzzle(17);
        generator.getBoard().printBoard();
    }
}
