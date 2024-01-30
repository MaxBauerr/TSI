public class Main {
    public static void main(String[] args) {

        String thanks = "Thank you,";
        String name = "miguel!";
        String order = "Your order number is #";
        int previousOrder = 715;

        // Convert name to uppercase
        String caps = name.toUpperCase();

        System.out.println(thanks + " " + caps);
        System.out.println(order + " " + (++previousOrder));
    }
}
