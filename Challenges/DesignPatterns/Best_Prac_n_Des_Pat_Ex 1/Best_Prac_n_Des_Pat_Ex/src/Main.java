import best_practices.*;
import design_patterns.singleton_exercise.*;
import design_patterns.adapter_exercise.*;
import design_patterns.factory_example.*;
import design_patterns.builder_example.*;

import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // Singleton Exercise
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("\nSingleton Exercise, Guess the Number! \n");
        GuessTheNumberNew game = GuessTheNumberNew.getInstance();
        game.playGame();

        // Factory Exercise
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("\nFactory Exercise, Drawing a Rectangle and a Triangle! \n");
        Shape rectangle = ShapeFactory.createShape("Rectangle");
        rectangle.draw();
        Shape triangle = ShapeFactory.createShape("Triangle");
        triangle.draw();

        // Builder Exercise
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("\nBuilder Exercise, Building Computers! \n");
        PCDirector director = new PCDirector();
        PCBuilder builder = new GamingPCBuilder();
        director.construct(builder);
        Computer gamingPC = builder.getResult();
        builder = new OfficePCBuilder();
        director.construct(builder);
        Computer officeLaptop = builder.getResult();
        gamingPC.getInfo();
        System.out.println();
        officeLaptop.getInfo();

        // Adapter Exercise
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("\nAdapter Exercise, Override toString method to user getInfo! \n");
        Pensioner pensioner = new Pensioner("Mohit Kumar", 24);
        PensionerAdapter pensionerAdapter = new PensionerAdapter(pensioner);
        System.out.println(pensionerAdapter);

        // Quadrilateral Exercise
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("\nQuadrilateral Exercise, Create a Quadrilateral! \n");
        QuadrilateralNew noData = new QuadrilateralNew();
        System.out.println("Default shape (10,10,Black)");
        noData.PrintData();
        QuadrilateralNew onlySide = new QuadrilateralNew(6);
        System.out.println("Only side lenght given (6)");
        onlySide.PrintData();
        QuadrilateralNew bothSide = new QuadrilateralNew(3,7);
        System.out.println("Both height and width given (3,7)");
        bothSide.PrintData();
        QuadrilateralNew allData = new QuadrilateralNew(12,2,"Red");
        System.out.println("Also colour given (12,2,Red)");
        allData.PrintData();

        // YearDescriberN Exercise
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("\nYearDescriber Exercise, Describe year if leap or not and starting day! \n");
        YearDescriberNew.describeYear();

        // GuessTheNumber with Dependencies Exercise
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("\nSingleton Exercise, Guess the Number with Dependencies! \n");
        Random randomGenerator = new Random();
        GuessTheNumberDependencyInjection dependencyGame = GuessTheNumberDependencyInjection.getInstance(randomGenerator);
        Scanner userInputScanner = new Scanner(System.in);
        dependencyGame.setFromUser(userInputScanner);
        dependencyGame.playGame();
        userInputScanner.close();
    }
}