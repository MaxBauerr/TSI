import org.example.Board;
import org.junit.jupiter.api.*;


import static org.example.Logics.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilsTest {

    @Nested
    class mineCountPerDifficultyTest {

        @Test
        void testCalculateMinesForDifficulty1() {
            int boardSize = 10; // Example board size
            int gameDifficulty = 1;
            int mines = calculateMines(boardSize, gameDifficulty);
            // For difficulty 1, minMines should be 5% and maxMines should be 10% of total cells
            int minMines = boardSize * boardSize * 5 / 100;
            int maxMines = boardSize * boardSize * 10 / 100;
            assertTrue(mines >= minMines && mines <= maxMines, "Incorrect amount of mines");
        }

        @Test
        void testCalculateMinesForDifficulty2() {
            int boardSize = 10; // Example board size
            int gameDifficulty = 2;
            int mines = calculateMines(boardSize, gameDifficulty);
            // For difficulty 2, minMines should be 10% and maxMines should be 20% of total cells
            int minMines = boardSize * boardSize * 10 / 100;
            int maxMines = boardSize * boardSize * 20 / 100;
            assertTrue(mines >= minMines && mines <= maxMines, "Incorrect amount of mines");
        }

        @Test
        void testCalculateMinesForDifficulty3() {
            int boardSize = 10; // Example board size
            int gameDifficulty = 3;
            int mines = calculateMines(boardSize, gameDifficulty);
            // For difficulty 3, minMines should be 20% and maxMines should be 35% of total cells
            int minMines = boardSize * boardSize * 20 / 100;
            int maxMines = boardSize * boardSize * 35 / 100;
            assertTrue(mines >= minMines && mines <= maxMines, "Incorrect amount of mines");
        }

        @Test
        void testCalculateMinesForDifficulty4() {
            int boardSize = 10; // Example board size
            int gameDifficulty = 4;
            int mines = calculateMines(boardSize, gameDifficulty);
            // For difficulty 4, minMines should be 35% and maxMines should be 55% of total cells
            int minMines = boardSize * boardSize * 35 / 100;
            int maxMines = boardSize * boardSize * 55 / 100;
            assertTrue(mines >= minMines && mines <= maxMines, "Incorrect amount of mines");
        }

        @Test
        void testCalculateMinesForDifficulty5() {
            int boardSize = 10; // Example board size
            int gameDifficulty = 5;
            int mines = calculateMines(boardSize, gameDifficulty);
            // For difficulty 5, minMines should be 55% and maxMines should be 80% of total cells
            int minMines = boardSize * boardSize * 55 / 100;
            int maxMines = boardSize * boardSize * 80 / 100;
            assertTrue(mines >= minMines && mines <= maxMines, "Incorrect amount of mines");
        }

    }

    @Nested
    class testHintCount {

        @Test
        void testHintCountForDifficulty1() {
            int difficultyChoice = 1; // Difficulty used to count hint
            int expectedHints = 3; // Since 1 * 3 = 3
            Assertions.assertEquals(expectedHints, hintCount(difficultyChoice), "Incorrect amount of available hints");
        }

        @Test
        void testHintCountForDifficulty2() {
            int difficultyChoice = 2;
            int expectedHints = 6;
            Assertions.assertEquals(expectedHints, hintCount(difficultyChoice), "Incorrect amount of available hints");
        }

        @Test
        void testHintCountForDifficulty3() {
            int difficultyChoice = 3;
            int expectedHints = 9;
            Assertions.assertEquals(expectedHints, hintCount(difficultyChoice), "Incorrect amount of available hints");
        }

        @Test
        void testHintCountForDifficulty4() {
            int difficultyChoice = 4;
            int expectedHints = 12;
            Assertions.assertEquals(expectedHints, hintCount(difficultyChoice), "Incorrect amount of available hints");
        }

        @Test
        void testHintCountForDifficulty5() {
            int difficultyChoice = 5;
            int expectedHints = 15;
            Assertions.assertEquals(expectedHints, hintCount(difficultyChoice), "Incorrect amount of available hints");
        }

        /*
        @Test
        void testHintCountForDifficulty1Failure() {
            int difficultyChoice = 1;
            int expectedHints = 2; // Test should fail as expected is 2 but actual is 3
            Assertions.assertEquals(expectedHints, hintCount(difficultyChoice), "Incorrect amount of available hints");
        }
        */
    }

    @Nested
    class testBoard {

        @Test
        public void testIsBoardEmptyTrue() { // Check if initial state of the board when initialized is empty as [-]
            int squareSize = 5;
            int numMines = 3;
            int hints = 2;
            Board board = new Board(squareSize, numMines, hints);
            // Execute & Verify
            Assertions.assertTrue( board.isBoardEmpty(), "Board is not empty");
        }

        @Test
        public void testIsBoardEmptyFalse() {
            int squareSize = 5;
            int numMines = 3;
            int hints = 2;
            Board board = new Board(squareSize, numMines, hints);
            // Execute & Verify
            board.getBoard()[1][0] = "Hello";
            Assertions.assertFalse( board.isBoardEmpty(), "Board is empty");
        }
    }

    @Nested
    class testIsMineHere {

        @Test
        public void testMinePresent() {
            Board board = new Board(5, 3, 2);
            board.getMines()[1][1] = true; // Place a mine at (1,1) for this test
            boolean result = board.isMineHere(1, 1);
            Assertions.assertTrue(result, "Expected a mine at (1,1)");
        }

        @Test
        public void testMineNotPresent() {
            Board board = new Board(5, 3, 2); // No mines are manually placed for this test
            boolean result = board.isMineHere(0, 0);
            Assertions.assertFalse(result, "Expected no mine at (0,0)");
        }

        @Test
        public void testOutOfBoundsNegative() {
            Board board = new Board(5, 3, 2); // Board setup, no need to place mines for this test
            boolean result = board.isMineHere(-1, -1);
            Assertions.assertFalse(result, "Out-of-bounds positions should not be considered as having a mine");
        }

        @Test
        public void testOutOfBoundsPositive() {
            Board board = new Board(5, 3, 2); // Board setup, no need to place mines for this test
            boolean result = board.isMineHere(board.getSquareSize(), board.getSquareSize());
            Assertions.assertFalse(result, "Out-of-bounds positions should not be considered as having a mine");
        }
    }

    @Nested
    class testNumMines {

        @Test
        public void testMineCountCorrect() { // Check if the mine count is as expected
            Board board = new Board(5,3,2);
            int mineCount = board.getNumMines();
            int expectedMineCount = 3;
            Assertions.assertEquals(expectedMineCount, mineCount, "Mine count should be the same");
        }

        @Test
        public void testMineCountIncorrect() { // Check if the mine count is as expected
            Board board = new Board(5,3,2);
            int mineCount = board.getNumMines();
            int expectedMineCount = 4; // Expected changed, this should say the values are not equal
            Assertions.assertNotEquals(expectedMineCount, mineCount, "Mine count should not be the same");
        }
    }

    @Nested
    class testAdjacent {

        @Test
        public void testSamePosition() {
            Board board = new Board(5, 5, 5);
            boolean result = board.isAdjacent(2, 2, 2, 2);
            Assertions.assertFalse(result, "Same position are not adjacent");
        }

        @Test
        public void testDirectlyAdjacentHorizontal() {
            Board board = new Board(5, 5, 5);
            boolean result = board.isAdjacent(2, 2, 2, 3);
            Assertions.assertTrue(result, "The inputs are not adjacent.");
        }

        @Test
        public void testDirectlyAdjacentVertical() {
            Board board = new Board(5, 5, 5);
            boolean result = board.isAdjacent(2, 2, 3, 2);
            Assertions.assertTrue(result, "The inputs are not adjacent.");
        }

        @Test
        public void testDiagonallyAdjacent() {
            Board board = new Board(5, 5, 5);
            boolean result = board.isAdjacent(2, 2, 3, 3);
            Assertions.assertTrue(result, "The inputs are not adjacent.");
        }

        @Test
        public void testNotAdjacent() {
            Board board = new Board(5, 5, 5);
            boolean result = board.isAdjacent(2, 2, 4, 4);
            Assertions.assertFalse(result, "The inputs are adjacent.");
        }

    }
}
