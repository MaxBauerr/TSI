import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Enable user input
        PaintManager paintManager = new PaintManager(); // Paint dictionary
        Map<Integer, Paint> paintOptions = paintManager.getPaintOptions(); // Save all available paint options
        ArrayList<Double> myWallCostsList = new ArrayList<>(); // Save Wall costs in this List *** LIST ADDED *** Could do Vector
        // Request wall count + Error Handling
        int numberOfWalls = 0; // Initilize walls to 0 to loop until a number is inserted.
        System.out.print("Enter the number of walls to paint: ");
        while (numberOfWalls <= 0 ) { // *** WHILE LOOP ADDED ***
            while (!scanner.hasNextInt()) { // Until input is not valid as in chars
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input to restart process
            }
            numberOfWalls = scanner.nextInt();
            if (numberOfWalls <= 0) { // *** IF STATEMENT ADDED ***
                System.out.println("Invalid input. Please enter an integer higher than 0.");
            }
        }
        double totalCost = 0;
        for (int i = 0; i < numberOfWalls; i++) { // *** FOR LOOP ADDED ***
            System.out.println("Wall " + (i + 1) + ":");
            // Height for Wall + Error Handling
            double height = -1;
            while (height <= 0) {
                System.out.print("Enter height (meters): ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next();
                }
                height = scanner.nextDouble();
                if (height <= 0) {
                    System.out.println("Height must be a positive number.");
                }
            }
            // Lenght for Wall + Error Handling
            double length = -1;
            while (length <= 0) {
                System.out.print("Enter length (meters): ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next();
                }
                length = scanner.nextDouble();
                if (length <= 0) {
                    System.out.println("Length must be a positive number.");
                }
            }
            Wall wall = new Wall(height, length); // Create Wall Object to store Wall details
            double totalNonPaintingArea; // Initilize Total Non Painting Area to calculate invalid space
            do { // *** DO WHILE LOOP ADDED ***
                totalNonPaintingArea = 0; // Total Non Paintable Area initilized to 0
                // Non Paintable Section Code + Error Handling
                int numberOfSections = -1; // Initialize to -1 to ensure the loop runs at least once
                System.out.print("How many non-painting sections are there? ");
                while (numberOfSections < 0) {
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        scanner.next();
                        continue; // Skip remaining part of the While Loop as it is not required if this occurs
                    }
                    numberOfSections = scanner.nextInt();
                    if (numberOfSections < 0) { // Negative count check, if sections are negative, restart
                        System.out.println("Invalid input. The number of sections cannot be negative. Please enter an integer higher or equal to 0.");
                    }
                }
                // Non Paintable Area Shapes + Error Handling
                for (int j = 0; j < numberOfSections; j++) {
                    String shapeType;
                    boolean validInput = false; // **** BOOLEAN ADDED ***
                    Shape shape = null;
                    while (!validInput) {
                        System.out.print("Enter shape of section (rectangle/circle): ");
                        shapeType = scanner.next().toLowerCase(); // *** FORMATTING ADDED ***
                        switch (shapeType) { // *** SWITCH ADDED ***
                            case "rectangle": // IF Shape is a Rectangle
                                // LENGHT
                                double rectLength = -1; // Negative initiliazer for looping
                                while (rectLength <= 0) {
                                    System.out.print("Enter length of the rectangle: ");
                                    while (!scanner.hasNextDouble()) {
                                        System.out.println("Invalid input. Please enter a valid number.");
                                        scanner.next();
                                    }
                                    rectLength = scanner.nextDouble();
                                    if (rectLength <= 0) {
                                        System.out.println("Length must be a positive number.");
                                    }
                                }
                                // WIDTH
                                double rectWidth = -1;
                                while (rectWidth <= 0) {
                                    System.out.print("Enter width of the rectangle: ");
                                    while (!scanner.hasNextDouble()) {
                                        System.out.println("Invalid input. Please enter a valid number.");
                                        scanner.next();
                                    }
                                    rectWidth = scanner.nextDouble();
                                    if (rectWidth <= 0) {
                                        System.out.println("Width must be a positive number.");
                                    }
                                }
                                shape = new Rectangle(rectLength, rectWidth); // Create Object Shape Recatangle
                                validInput = true;
                                break; // Break loop
                            case "circle": // IF Shape is Circle
                                double radius = -1;
                                while (radius <= 0) {
                                    System.out.print("Enter radius of the circle: ");
                                    while (!scanner.hasNextDouble()) {
                                        System.out.println("Invalid input. Please enter a valid number.");
                                        scanner.next();
                                    }
                                    radius = scanner.nextDouble();
                                    if (radius <= 0) { // Radius cannot be negative
                                        System.out.println("Radius must be a positive number.");
                                    }
                                }
                                shape = new Circle(radius); // Create Object Shape Circle
                                validInput = true;
                                break;
                            default: // IF other option chosen which are not present
                                System.out.println("Invalid shape type. Please enter 'rectangle' or 'circle'.");
                                break;
                        }
                    }
                    if (shape != null) { // IF shape is created, add it to Wall Non Paintable Area
                        wall.addNonPaintableShape(shape);
                    }
                }
                // IF total non paintable area is higher than wall area, restart request
                if (totalNonPaintingArea > wall.getPaintableArea()) { // Check if Non Paintable Area is higher than total Wall Area. If yes, restart.
                    System.out.println("Error: The total non-painting area (" + totalNonPaintingArea +
                            " sq meters) exceeds the wall's area (" + wall.getPaintableArea() +
                            " sq meters). Please re-enter the non-painting sections.");
                    wall.resetNonPaintableShapes(); // Delete all existing non paintable area as process has restarted
                } else {
                    break; // Exit loop if the total non-painting area is within limits
                }
            } while (true); // END of Do While Loop
            // Print current Wall total paintable Area
            double paintableArea = wall.getPaintableArea();
            System.out.println("Paintable area of Wall " + (i + 1) + ": " + paintableArea + " square meters");
            // Display available paint options + Error Handling
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
            // WALL COST CALCULATION SECTION
            double wallArea = wall.getPaintableArea(); // Retrieve total Paintable Area
            double standardCoverageRate = 10; // Fixed standard coverage per litre for paint
            double bagSize = wall.getPaint().getSizeInLitres(); // Retrieve quantity in litres in one bag
            double bagCoverage = bagSize * standardCoverageRate; // Calculate total coverage available with all bags
            double bagCost = wall.getPaint().getCostPerContainer(); // Retrive cost per bag
            double initialBagsNeeded = wallArea / bagCoverage; // Calculate the initial number of bags needed
            int bagsRequired = (int) Math.ceil(initialBagsNeeded); // Round up to the nearest whole number to ensure complete coverage
            double wallCost = bagsRequired * bagCost; // Calcute wall cost with bag cost times total required bags
            myWallCostsList.add(wallCost); // Add wall cost to List - Could change to Vector
            //totalCost += wallCost; // Add wall cost to global total cost variable
        } // END OF WALL AND COST SECTION
        System.out.println(" ");
        for (int i = 0; i < myWallCostsList.size(); i++) { // Print all Walls cost
            System.out.println("Cost for Wall Number " + (i+1) + " is : " + myWallCostsList.get(i)); // Print Wall Number and than Wall cost
            totalCost += myWallCostsList.get(i);
        }
        System.out.println(" ");
        System.out.print("The total cost for this job is: £" + totalCost); // Print total cost for the whole job
        scanner.close(); // Close scanner
    }
}
/*
   ******************** POSSIBLE ADD ********************
   * 1. IF 0 walls are inserted WHEN wall count requested THEN Terminate program
   * 2. INSTEAD of manual printing shapes WHEN requesting non paintable shape type DO check all available shapes in the Shape Class THEN display all available
   * 3. ADD more paint options, if more options have the same color, REPROGRAM paint dictionary to save 1 color only once in another Array SO once color is chosen, save all available
   *    options for that color in a list which gets sent to wall as listOfPaints rather than Paint.
   * 4. IF more options are available for 1 single color WHEN calculating the wall cost CONSIDER cost efficiency intregration SO the program will find the best buy value. To do this
   *    implement Linear Function with a minimizer.
   * 5. ADD more shapes
   * 6. ADD more specification for details printed at the end. Can be done by setting cost to the Wall.
   * 7. ADD Exception Class for user inputs
 */
