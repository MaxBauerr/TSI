
public class Printer {
    static int moveCounter = 0;

    public static class Invalid extends Printer{

        public static void boardSize(){
            System.out.println("\nInvalid input. Board size must be 10 or higher.");
        }

        public static void integerValue() {
            System.out.println("\nInvalid input. Please enter a valid Integer.");
        }

        public static void optionRangeChoice() {
            System.out.println("\nInvalid choice. Please choose a number between 1 and 5.");
        }

        public static void position() {
            System.out.println("\nInvalid position");
        }

        public static void discoveredCell() {
            System.out.println("\nInvalid input. This cell cannot be flagged as its discovered cell.");
        }

        public static void notFlaggedCell() {
            System.out.println("\nInvalid input. This cell cannot be un-flagged as its not a flagged cell.");
        }

    }

    public static class Options extends Printer {

        public static void gameDifficulty() {
            System.out.println("\nPlease choose game difficulty. Please see below details");
            System.out.println("\nOption 1    :   Very Easy   :   Mine count between 5% to 10% of Board Size");
            System.out.println("\nOption 2    :   Easy        :   Mine count between 10% to 20% of Board Size");
            System.out.println("\nOption 3    :   Medium      :   Mine count between 20% to 35% of Board Size");
            System.out.println("\nOption 4    :   Hard        :   Mine count between 35% to 55% of Board Size");
            System.out.println("\nOption 5    :   Very Hard   :   Mine count between 55% to 80% of Board Size");
        }

        public static void runningGameOptions(int hintCount) {
            System.out.println("Choose an option:");
            System.out.println("1. Next Move");
            System.out.println("2. Place a Flag");
            System.out.println("3. Remove a Flag");
            System.out.println("4. Give a Hint. Hints Available : " + hintCount);
        }

    }

    public static class Requests extends Printer {

        public static void minimumBoardSize() {
            System.out.println("\nPlease input the size of the board. Minimum size is 10.");
        }

        public static void rowEntry() {
            System.out.println("\nPlease enter Row : ");
        }

        public static void columnEntry() {
            System.out.println("\nPlease enter Column : ");
        }
    }

    public static class Statement extends Printer {

        public static void welcomeMessage() {
            System.out.println("\nWelcome to Minesweeper");
        }

        public static void moveEntry() {
            moveCounter += 1;
            if (moveCounter == 1) {
                System.out.println("\nEnter your first move below");
            } else {
                System.out.println("\nEnter your next move below");
            }
        }

        public static void gameOver() {
            System.out.println("\nGame Over! You hit a mine!");
        }

        public static void safeMove() {
            System.out.println("\nSafe move!");
            System.out.println("Updating board...");
        }

        public static void gameWin() {
            System.out.println("\nCongratulations! You won the game!");
        }

        public static void gameStats(int safeCellCount, int minesCount) {
            System.out.println("\nRemaining safe cells : " + safeCellCount);
            System.out.println("Remaining mines : " + minesCount);
        }

        public static void hints(int hintCount) {
            if (hintCount > 0) {
                System.out.println("\nRemaining Hints : " + hintCount);
            } else {
                System.out.println("\nNo more Hints Available!");
            }
        }

    }
}
