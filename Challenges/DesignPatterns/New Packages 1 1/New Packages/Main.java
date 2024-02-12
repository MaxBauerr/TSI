package design_patterns;
import design_patterns.strategy_pattern_exercise.*;

public class Main {
    public static void main(String[] args) {
        // Message to encode
        String message = "ABC";

        // Using NumberCipher to encode the message
        CipherStrategy numberCipher = new NumberCipher();
        String encodedWithNumberCipher = Encoder.encodeMessage(numberCipher, message);
        System.out.println("Encoded with NumberCipher: " + encodedWithNumberCipher);

        // Using CaeserCipher to encode the message
        CipherStrategy caesarCipher = new CaeserCipher();
        String encodedWithCaesarCipher = Encoder.encodeMessage(caesarCipher, message);
        System.out.println("Encoded with CaeserCipher: " + encodedWithCaesarCipher);
    }
}
