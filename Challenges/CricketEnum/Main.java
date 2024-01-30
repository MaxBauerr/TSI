import java.util.Random;

public class Main {

    // Define the Score enum with possible values
    public enum Score {
        W, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX;

        // Method to get a Score value from an integer
        public static Score fromInt(int score) {
            switch (score) {
                case -1: return W;
                case 0: return ZERO;
                case 1: return ONE;
                case 2: return TWO;
                case 3: return THREE;
                case 4: return FOUR;
                case 5: return FIVE;
                case 6: return SIX;
                default: throw new IllegalArgumentException("Invalid score: " + score);
            }
        }
    }

    // Generate a cricket score and return as a Score enum
    public static Score generateCricketScore() {
        Random random = new Random();
        // Generate a random number between -1 and 6, inclusive
        int score = random.nextInt(8) - 1;
        return Score.fromInt(score);
    }

    public static void main(String[] args) {
        Score score = generateCricketScore();
        System.out.println("Score: " + score);
    }
}
