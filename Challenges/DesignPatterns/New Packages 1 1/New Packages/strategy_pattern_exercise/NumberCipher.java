package design_patterns.strategy_pattern_exercise;

public class NumberCipher implements CipherStrategy {

    @Override
    public String encode(String input) {
        input = input.toLowerCase();
        String returnString = "";

        for(int i = 0; i < input.length(); i++) {
            returnString += (input.charAt(i)-48);
            returnString += " ";
        }

        return returnString;
    }
}
