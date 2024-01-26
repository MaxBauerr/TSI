public class Printer {
    static int moveCounter = 0; // Counts player action made in game, this is used to display the right message for first player action and further action

    public static class Invalid extends Printer{ // Includes all possible errors in the game.

        public static void boardSize(){ // Board size input is lower than minimum requirement
            System.out.println("\nInvalid input. Board size must be 10 or higher.");
        }

        public static void integerValue() { // Input value is below allowed minimum (1).
            System.out.println("\nInvalid input. Please enter a valid Integer.");
        }

        public static void optionRangeChoice() { // Input value is below or above limits
            System.out.println("\nInvalid choice. Please choose a number between 1 and 5.");
        }

        public static void position() { // Input value is out of bounds from grid
            System.out.println("\nInvalid position");
        }

        public static void discoveredCell() { // Chosen cell does not meet requirements to change to flag
            System.out.println("\nInvalid input. This cell cannot be flagged as its a discovered cell.");
        }

        public static void notFlaggedCell() { // Chosen cell does not meet requirements to remove flag
            System.out.println("\nInvalid input. This cell cannot be un-flagged as its not a flagged cell.");
        }

        public static void gameError(Exception e) { // Game crashed during runtime.
            System.out.println("An error occurred during the game: " + e.getMessage());
        }

    }

    public static class Options extends Printer { // Includes all player options in the game.

        public static void gameDifficulty() { // Give user choice for difficulty
            System.out.println("\nPlease choose game difficulty. Please see below details");
            System.out.println("\nOption 1    :   Very Easy   :   Mine count between 5% to 10% of Board Size");
            System.out.println("\nOption 2    :   Easy        :   Mine count between 10% to 20% of Board Size");
            System.out.println("\nOption 3    :   Medium      :   Mine count between 20% to 35% of Board Size");
            System.out.println("\nOption 4    :   Hard        :   Mine count between 35% to 55% of Board Size");
            System.out.println("\nOption 5    :   Very Hard   :   Mine count between 55% to 80% of Board Size");
        }

        public static void runningGameOptions(int hintCount) { // Give user in game option
            System.out.println("Choose an option:");
            System.out.println("1. Next Move");
            System.out.println("2. Place a Flag");
            System.out.println("3. Remove a Flag");
            System.out.println("4. Give a Hint. Hints Available : " + hintCount);
            System.out.println("5. Surrender");
        }

    }

    public static class Requests extends Printer { // Include all requests made to the player in game.

        public static void minimumBoardSize() { // Request user to input a number equal to 10 or higher
            System.out.println("\nPlease input the size of the board. Minimum size is 10.");
        }

        public static void rowEntry() { // Request user for position of Row
            System.out.println("\nPlease enter Row : ");
        }

        public static void columnEntry() { // Request user for position of Column
            System.out.println("\nPlease enter Column : ");
        }
    }

    public static class Statement extends Printer { // Includes all game statements.

        public static void welcomeMessage() { // Display welcome message when application is run.
            System.out.println("\nWelcome to Minesweeper");
        }

        public static void moveEntry() { // Display move message depending on current player move count.
            moveCounter += 1;
            if (moveCounter == 1) {
                System.out.println("\nEnter your first move below");
            } else {
                System.out.println("\nEnter your next move below");
            }
        }

        public static void gameOver(boolean playerChoice) { // Game over message when player has lost.
            if (playerChoice) {
                System.out.println("\nGame Over! You dog!");
            } else {
                System.out.println("\nGame Over! You hit a mine!");
            }
        }

        public static void safeMove() { // Game continuation message as player move was safe.
            System.out.println("\nSafe move!");
            System.out.println("Updating board...");
        }

        public static void gameWin() { // Congratulation message when player has won.
            System.out.println("\nCongratulations! You won the game!");
        }

        public static void gameStats(int safeCellCount, int minesCount, int flagCount, int hintMinesCount) { // Game statistics
            System.out.println("\nRemaining safe cells : " + safeCellCount);
            System.out.println("Remaining mines : " + (minesCount - hintMinesCount));
            System.out.println("Flagged cells : " + flagCount);
        }

        public static void timeStats(long time) { // Display time when game ends to show user time spent.
            time = time / 1000;
            if (time<60) {
                System.out.println("\nIt took you " + time + " seconds to finish the game!");
            } else {
                int minutes = 0;
                for(int x = 60; x < time; x+=60) {
                    minutes += 1;
                }
                int seconds = (int) (time - (minutes*60));
                System.out.println("\nIt took you " + minutes + " minutes and " + seconds + " to finish the game!");
            }
        }

        public static void hints(int hintCount) { // Display remaining hints after hint has been used.
            if (hintCount > 0) {
                System.out.println("\nRemaining Hints : " + hintCount);
            } else {
                System.out.println("\nNo more Hints Available!");
            }
        }

    }

    public static class Format extends Printer {

        public static String whiteSafeCell() { // Standard Formatting
            return "[-]";
        }

        public static String safeCell(int minesCount, boolean hinted) {
            if (hinted) {
                return "\033[95m[" + minesCount + "]\033[0m"; // Format Purple
            } else{
                if (minesCount == 0) {
                    return "\033[32m[" + minesCount + "]\033[0m"; // Format Green
                } else {
                    return "\033[33m[" + minesCount + "]\033[0m"; // Format Yellow
                }
            }
        }

        public static String[] hintedSafeCell() {
            String leftFormat = "\033[35m["; //
            String rightFormat = "]\033[0m"; //
            return new String[]{leftFormat, rightFormat}; // Return an array of Strings
        }

        public static String minesNotHintCell() { // Format Red
            return "\033[31m[*]\033[0m";
        }

        public static String correctNonDiscoveredCell() { // Format Blue
            return "\033[34m[+]\033[0m";
        }

        public static String redFlag() { // Format Red
            return "\033[31m[?]\033[0m";
        }

        public static String hintedMines() { // Format Purple
            return "\033[95m[*]\033[0m";
        }
    }
}
