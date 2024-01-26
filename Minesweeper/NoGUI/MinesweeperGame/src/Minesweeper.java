import java.util.Scanner;

public class Minesweeper {
    private final Board board;
    private boolean gameStatus;

    public Minesweeper(int squareSize, int numMines, int difficultyChoice) {
        this.gameStatus = true; // Game status true indicates active loop
        int hints = Logics.hintCount(difficultyChoice);
        board = new Board(squareSize, numMines, hints);  // Create board
    }

    public void play(Scanner sc) {
        int[] coordinates = getRowAndColumn(sc);
        board.placeMines(coordinates[0], coordinates[1]); // Initialize mines after the first move
        playMove((coordinates[0]), (coordinates[1]));
        winCondition();
        while (gameStatus) {
            board.displayBoard();
            Printer.Options.runningGameOptions(board.getHintCount());
            int inGameChoice = sc.nextInt();
            switch (inGameChoice) {
                case 1 -> {
                    coordinates = getRowAndColumn(sc);
                    playMove((coordinates[0]), (coordinates[1]));
                    winCondition();
                }
                case 2 -> {
                    optionFlagOrUnflag(true, sc);
                }
                case 3 -> {
                    optionFlagOrUnflag(false, sc);
                }
                case 4 -> {
                    board.giveHint();
                }
                default -> {
                    Printer.Invalid.optionRangeChoice();
                }
            }
        }
    }

    private void optionFlagOrUnflag(boolean flag, Scanner sc) {
        int[] coordinates = getRowAndColumn(sc);
        if(flag) {
            board.cellFlag(coordinates[0], coordinates[1]);
        } else {
            board.cellUnflag(coordinates[0], coordinates[1]);
        }
    }

    private void playMove(int row, int col) {
        if (board.isMineHere(row, col)) {
            gameStatus = false;
            Printer.Statement.gameOver();
            board.displayEndBoard(gameStatus);
        }  else {
            Printer.Statement.safeMove();
            board.changeBoard(row, col);;
        }
    }

    private void winCondition() {
        if (board.getSafeCells() == 0) {
            gameStatus = false;
            Printer.Statement.gameWin();
            board.displayEndBoard(gameStatus);
        }
    }

    private int[] getRowAndColumn(Scanner sc) {
        Printer.Statement.moveEntry();
        Printer.Requests.rowEntry();
        int row = sc.nextInt();
        Printer.Requests.columnEntry();
        int col = sc.nextInt();
        return new int[]{row-1, col-1};
    }
}
