package best_practices;
import java.util.Scanner;
import static java.lang.Math.floor;

/*
This class has a super bloated method that does too many things at once. Refactor the describeYear method and
separate out the responsibilities into other methods to improve readability and maintainability.
 */

/*

 */
public class YearDescriber {

    static Scanner fromUser = new Scanner(System.in);

    private YearDescriber(){}; //static class

    public static void describeYear() {
        System.out.println("What year would you like to learn about?");
        int year;
        while(true) {
            try {
                year = fromUser.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Please just input a year in the format YYYY!");
            }
        }
        String printMessage = year + " was ";
        if((year%4 == 0) || ((year%100 == 0) && !(year%400 == 0))) {
            printMessage += "a leap year,";
        } else {
            printMessage += "not a leap year,";
        }
        printMessage += " and started on a ";
        int startDay = (1 + (int)floor((2.6*11) -0.2) - (2*(year/100)) + ((year%100)-1) + (int)floor((double)((year%100)-1) / 4) + (int)floor((double)(year/100) / 4));
        /*
            int/int uses integer division e.g 11/5 = 2 (discard the remainder)
            double/int uses true division e.g. (double)(11)/5 = 2.2
        */
        startDay = (startDay % 7 + 7) % 7;
        /*
            % is remainder function, NOT modulus division operator.
            Line 41 implements true modulus division.
            https://cs.uwaterloo.ca/~alopez-o/math-faq/node73.html for more info on the equation used.
        */
        switch(startDay) {
            case 0:
                printMessage += "Sunday";
                break;
            case 1:
                printMessage += "Monday";
                break;
            case 2:
                printMessage += "Tuesday";
                break;
            case 3:
                printMessage += "Wednesday";
                break;
            case 4:
                printMessage += "Thursday";
                break;
            case 5:
                printMessage += "Friday";
                break;
            case 6:
                printMessage += "Saturday";
        } //psst...this can be much more concisely implemented using an array...

        printMessage += "!";

        System.out.println(printMessage);
    }
}
