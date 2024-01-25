import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Enable suer input
        Scanner scanner = new Scanner(System.in);

        // Ask for the number of walls
        System.out.print("Enter the number of walls to paint: ");
        int numberOfWalls = scanner.nextInt();

        // Initialize variables for total area
        double totalArea = 0;

        // Get dimensions for each wall and calculate total area
        for (int i = 1; i <= numberOfWalls; i++) {
            System.out.println("Wall " + i + ":");
            System.out.print("Enter height (meters): ");
            double height = scanner.nextDouble();
            System.out.print("Enter length (meters): ");
            double length = scanner.nextDouble();

            // Add the area of the current wall to the total area
            totalArea += height * length;
        }

        // Subtract the non-paintable area
        System.out.print("Enter the total area of non-paintable parts (square meters): ");
        double nonPaintableArea = scanner.nextDouble();

        // Substract the non-paintable area with the total paintable area
        totalArea -= nonPaintableArea;

        // Ask for paint requirements and color
        System.out.print("Enter the paint color: ");
        String color = scanner.next();
        System.out.print("Enter the amount of paint required per square meter (liters): ");
        double paintPerSquareMeter = scanner.nextDouble();
        System.out.print("Enter the cost per liter of paint: ");
        double costPerLiter = scanner.nextDouble();
        System.out.print("Enter how many liters of paint are in one container: ");
        double litersPerContainer = scanner.nextDouble();

        // Calculations
        double totalPaintRequired = totalArea * paintPerSquareMeter;
        double totalCost = totalPaintRequired * costPerLiter;
        int containersNeeded = (int) Math.ceil(totalPaintRequired / litersPerContainer);

        // Display results
        System.out.println("Paint color selected: " + color);
        System.out.println("Total paintable surface area: " + totalArea + " square meters");
        System.out.println("Total amount of paint required: " + totalPaintRequired + " liters");
        System.out.println("Number of paint containers needed: " + containersNeeded);
        System.out.println("Total cost: $" + totalCost);
    }
}
