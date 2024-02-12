package best_practices;
import java.util.Scanner;

public class YearDescriberNew {

    private static final Scanner fromUser = new Scanner(System.in);

    private YearDescriberNew(){} //static class

    public static void describeYear() {
        int year = getYearFromUser();
        boolean isLeapYear = isLeapYear(year);
        int startDay = calculateStartDay(year);
        printYearDescription(year, isLeapYear, startDay);
    }

    private static int getYearFromUser() {
        System.out.println("What year would you like to learn about?");
        while(true) {
            try {
                return fromUser.nextInt();
            } catch (Exception e) {
                System.out.println("Please input a valid year in the format YYYY!");
                fromUser.next(); // clear the buffer
            }
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    private static int calculateStartDay(int year) {
        // Zeller's congruence algorithm for calculation of the day of the week
        int m = 11; // Month (March = 1, February = 12)
        int K = year % 100; // Year of the century
        int J = year / 100; // Zero-based century
        int day = 1; // Day of the month
        int h = (day + (int)((2.6 * m) - 0.2) - 2 * J + K + (K / 4) + (J / 4)) % 7;
        return (h + 7) % 7; // Ensure non-negative result
    }

    private static void printYearDescription(int year, boolean isLeapYear, int startDay) {
        String[] daysOfWeek = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String leapYearString = isLeapYear ? "a leap year," : "not a leap year,";
        String startDayString = daysOfWeek[startDay];
        System.out.println(year + " was " + leapYearString + " and started on a " + startDayString + "!");
    }
}
