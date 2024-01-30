public class Main {
    public static void main(String[] args) {

        // Greater
        int x = 50;
        int y = 10;

        if (x>y) {
            System.out.println("Greater");
        }

        // Equal
        x = 50;
        y = 50;

        if (x==y) {
            System.out.println("Equal");
        }

        // Not Equal
        x = 51;
        y = 49;

        if (x!=y) {
            System.out.println("Unequal");
        }

        // Tenrary Operator
        String z = (x!=y) ? "Unequal" : "Equal";
        System.out.println(z);

        // Switch
        x = 1;
        switch (x) {
            case 1 -> {
                System.out.println("Case 1");
            }
            case 2 -> {
                System.out.println("Case 2");
            }
            default -> {
                System.out.println("No Cases");
            }
        }

    }
}
