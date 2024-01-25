import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Enable user input
        Scanner scanner = new Scanner(System.in);

        // Paint dictionary
        PaintManager paintManager = new PaintManager();
        Map<Integer, Paint> paintOptions = paintManager.getPaintOptions();

        // Request wall count
        int numberOfWalls = 0;
        System.out.print("Enter the number of walls to paint: ");
        while (numberOfWalls <= 0 ) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // clear the invalid input
            }
            numberOfWalls = scanner.nextInt();
            if (numberOfWalls <= 0) {
                System.out.println("Invalid input. Please enter an integer higher than 0.");
            }
        }


        double totalCost = 0;

        for (int i = 0; i < numberOfWalls; i++) {
            System.out.println("Wall " + (i + 1) + ":");

            double height = -1;
            while (height <= 0) {
                System.out.print("Enter height (meters): ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); // clear the invalid input
                }
                height = scanner.nextDouble();
                if (height <= 0) {
                    System.out.println("Height must be a positive number.");
                }
            }

            double length = -1;
            while (length <= 0) {
                System.out.print("Enter length (meters): ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); // clear the invalid input
                }
                length = scanner.nextDouble();
                if (length <= 0) {
                    System.out.println("Length must be a positive number.");
                }
            }

            Wall wall = new Wall(height, length);
            double totalNonPaintingArea;

            do {
                totalNonPaintingArea = 0;
                // Non painting section handling
                int numberOfSections = -1; // Initialize to -1 to ensure the loop runs at least once
                System.out.print("How many non-painting sections are there? ");

                while (numberOfSections < 0) {
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        scanner.next(); // clear the invalid input
                        continue; // Skip the remaining part of the loop and ask for input again
                    }

                    numberOfSections = scanner.nextInt();

                    if (numberOfSections < 0) {
                        System.out.println("Invalid input. The number of sections cannot be negative. Please enter an integer higher or equal to 0.");
                    }
                }

                // Check if any non-paintable area are present, if yes check shape. Add error handling.
                for (int j = 0; j < numberOfSections; j++) {
                    String shapeType;
                    boolean validInput = false;
                    Shape shape = null;

                    while (!validInput) {
                        System.out.print("Enter shape of section (rectangle/circle): ");
                        shapeType = scanner.next().toLowerCase();

                        switch (shapeType) {
                            case "rectangle":
                                double rectLength = -1;
                                while (rectLength <= 0) {
                                    System.out.print("Enter length of the rectangle: ");
                                    while (!scanner.hasNextDouble()) {
                                        System.out.println("Invalid input. Please enter a valid number.");
                                        scanner.next(); // clear the invalid input
                                    }
                                    rectLength = scanner.nextDouble();
                                    if (rectLength <= 0) {
                                        System.out.println("Length must be a positive number.");
                                    }
                                }

                                double rectWidth = -1;
                                while (rectWidth <= 0) {
                                    System.out.print("Enter width of the rectangle: ");
                                    while (!scanner.hasNextDouble()) {
                                        System.out.println("Invalid input. Please enter a valid number.");
                                        scanner.next(); // clear the invalid input
                                    }
                                    rectWidth = scanner.nextDouble();
                                    if (rectWidth <= 0) {
                                        System.out.println("Width must be a positive number.");
                                    }
                                }

                                shape = new Rectangle(rectLength, rectWidth);
                                validInput = true;
                                break;

                            case "circle":
                                double radius = -1; // Initialize radius to -1 to ensure the loop runs at least once
                                while (radius <= 0) {
                                    System.out.print("Enter radius of the circle: ");
                                    while (!scanner.hasNextDouble()) {
                                        System.out.println("Invalid input. Please enter a valid number.");
                                        scanner.next(); // clear the invalid input
                                    }
                                    radius = scanner.nextDouble();

                                    if (radius <= 0) {
                                        System.out.println("Radius must be a positive number.");
                                    }
                                }

                                shape = new Circle(radius);
                                validInput = true;
                                break;

                            default:
                                System.out.println("Invalid shape type. Please enter 'rectangle' or 'circle'.");
                                break;
                        }
                    }

                    if (shape != null) {
                        wall.addNonPaintableShape(shape);
                    }
                }
                // Check if total non-painting area exceeds the wall's area
                if (totalNonPaintingArea > wall.getPaintableArea()) { // Assuming getArea() method exists for Wall
                    System.out.println("Error: The total non-painting area (" + totalNonPaintingArea +
                            " sq meters) exceeds the wall's area (" + wall.getPaintableArea() +
                            " sq meters). Please re-enter the non-painting sections.");

                    wall.resetNonPaintableShapes(); // Assuming a method to reset non-paintable shapes
                } else {
                    break; // Exit loop if the total non-painting area is within limits
                }
            } while (true);

            double paintableArea = wall.getPaintableArea();
            System.out.println("Paintable area of Wall " + (i + 1) + ": " + paintableArea + " square meters");

            // Display available paint options
            System.out.println("Available paint options:");
            int optionNumber = 1;
            for (Paint paint : paintOptions.values()) {
                System.out.println(optionNumber++ + ": " + paint.getColor());
            }

            int paintChoice = 0;
            boolean validChoice = false;

            while (!validChoice) {
                System.out.print("Select the color for Wall " + (i + 1) + " (enter the number): ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); // Clear the invalid input
                    continue;
                }

                paintChoice = scanner.nextInt();

                if (paintOptions.containsKey(paintChoice)) {
                    Paint selectedPaint = paintOptions.get(paintChoice);
                    wall.setPaint(selectedPaint); // Assuming Wall class has a method setPaint()
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice. Please select a valid paint option.");
                }
            }

            // Calculate cost to paint this wall, the available methods to retrieve cost are quantity in 1 container that can be done with
            // getSizeInLitres() and cost of 1 container with getCostPerContainer(). Then use the paintable area to calculate how many container
            // we need to fulfill the request. In conclusion calculate the total cost for this wall and save it in a variable called wallCost

            double wallArea = wall.getPaintableArea();
            double standardCoverageRate = 10; // Standard coverage per litre
            double bagSize = wall.getPaint().getSizeInLitres();
            double bagCoverage = bagSize * standardCoverageRate;
            double bagCost = wall.getPaint().getCostPerContainer();

            // Calculate the initial number of bags needed
            double initialBagsNeeded = wallArea / bagCoverage;

            // Round up to the nearest whole number to ensure complete coverage
            int bagsRequired = (int) Math.ceil(initialBagsNeeded);

            double wallCost = bagsRequired * bagCost;
            totalCost += wallCost;

        }

        System.out.print("The total cost for this job is: £" + totalCost);

        // Riccardo test

        
        scanner.close();
    }
}
