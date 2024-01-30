public class Main {
    public static void main(String[] args) {

        // While loops, condition applied if condition is met
        int counter = 0;

        while (counter < 6) {
            System.out.println(counter);
            counter++;
        }
        System.out.println(" ");


        // Do While loops, condition applied once regardless the condition, then re-done if condition is met
        counter = 0;

        do {
            System.out.println(counter);
            counter++;
        } while (counter < 6);

        System.out.println(" ");

        // For loops, 3 statement, initializer, condition and increment

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

        System.out.println(" ");

        // For Each Loop

        String[] items = {"item1", "item2"}
    }
}
