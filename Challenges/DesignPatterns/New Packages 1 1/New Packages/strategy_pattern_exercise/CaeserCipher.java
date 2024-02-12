package design_patterns.strategy_pattern_exercise;

public class CaeserCipher implements CipherStrategy {

    private static final int SHIFT = 1;

    @Override
    public String encode(String input) {
        input = input.toLowerCase();
        StringBuilder encoded = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                char shifted = (char) (((c - 'a' + SHIFT) % 26) + 'a');
                encoded.append(shifted);
            } else {
                encoded.append(c); // Non-alphabetic characters are not encoded
            }
        }

        return encoded.toString();
    }
}
