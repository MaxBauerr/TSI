package com.Basic.Generator.MultiSolution;

public class SudokuApp {
    public static void main(String[] args) {
        SudokuGenerator generator = new SudokuGenerator();
        generator.generatePuzzle(30);
        generator.getBoard().printBoard();
    }
}
