package design_patterns.singleton_exercise;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberDependencyInjection {

    private static GuessTheNumberDependencyInjection instance;
    private final Random generator;
    private Scanner fromUser;

    // Constructor with essential dependencies
    private GuessTheNumberDependencyInjection(Random generator) {
        this.generator = generator;
    }

    public static GuessTheNumberDependencyInjection getInstance(Random generator) {
        if (instance == null) {
            instance = new GuessTheNumberDependencyInjection(generator);
        }
        return instance;
    }

    // Setter for optional or changeable dependency
    public void setFromUser(Scanner fromUser) {
        this.fromUser = fromUser;
    }

    public void playGame() {
        // Ensure that fromUser is initialized before playing
        if (fromUser == null) {
            throw new IllegalStateException("Scanner (fromUser) must be set before playing.");
        }

        int targetNumber = generator.nextInt(101);
        System.out.println("I'm thinking of a number between 1 and 100. Can you guess what it is?");
        int guess;

        while (true) {
            guess = fromUser.nextInt();
            if (guess == targetNumber) {
                break;
            } else {
                System.out.print("That guess is too ");
                if (guess > targetNumber) {
                    System.out.print("high");
                } else {
                    System.out.print("low");
                }
                System.out.println(", try again!");
            }
        }

        System.out.println("You got it! It was " + targetNumber + " all along!");
    }
}
