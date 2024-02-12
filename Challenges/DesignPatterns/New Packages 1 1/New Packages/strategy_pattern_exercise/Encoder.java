package design_patterns.strategy_pattern_exercise;

public class Encoder {
    public static String encodeMessage(CipherStrategy strat, String inputString) {
        return strat.encode(inputString);
    }
}
