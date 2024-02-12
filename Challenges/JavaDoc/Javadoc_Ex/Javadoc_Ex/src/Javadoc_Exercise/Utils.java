package Javadoc_Exercise;

/** An un-instantiable (effectively static) class of utility functions for use in various projects.
 * @author Mohit Kumar @ The Software Institute
 * @since JDK 21.0.2
 * @version Feb 2024
 */
public class Utils {

    private Utils(){}

    /** A function to determine whether a given number is prime or not in O(n) time.
     * <b>Please note negative numbers cannot be prime.</b>
     * @param toCheck The number to determine the primeness of.
     * @return True if toCheck is prime, false otherwise.
     */
    public static boolean isPrime(int toCheck) {
        if(toCheck < 2) {
            return false;
        }
        for(int i = 2; i < toCheck/2; i++) {
            if(toCheck%i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeImproved(int toCheck) {
        if (toCheck < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(toCheck); i++) {
            if (toCheck % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static String reverse(String toReverse) {
        StringBuilder returnString = new StringBuilder();
        for(int i = toReverse.length()-1; i >= 0; i--) {
            returnString.append(toReverse.charAt(i));
        }
        return returnString.toString();
    }

    public static int binarySearch(int[] arr, int desiredElement) {
        int startIndex = 0;
        int endIndex = arr.length-1;
        int middle;
        while(true) {
            middle = startIndex+((endIndex-startIndex)/2);
            if(arr[middle] == desiredElement) {
                return middle;
            } else if(startIndex == endIndex) {
                return -1;
            } else {
                if(arr[middle] < desiredElement) {
                    startIndex = middle+1;
                } else {
                    endIndex = middle-1;
                }
            }
        }
    }

    /** An in-place implementation of bubble sort, an O(n^2) sorting algorithm. Sorts into ascending order.
     * @param toSort An array of integer values to be sorted.
     */
    //https://www.hackerearth.com/practice/algorithms/sorting/bubble-sort/visualize/
    public static void bubbleSortImproved(int[] toSort) {
        boolean swapped = true;
        int n = toSort.length;
        while (swapped) {
            swapped = false; // reset swapped flag to false, assuming no swaps will occur in this pass
            for (int i = 1; i < n; i++) {
                if (toSort[i - 1] > toSort[i]) {
                    // swap toSort[i - 1] and toSort[i]
                    int temp = toSort[i - 1];
                    toSort[i - 1] = toSort[i];
                    toSort[i] = temp;
                    swapped = true; // set flag to true indicating a swap occurred
                }
            }
            n--; // decrement n since the last element of the current pass is already in its correct position
        }
    }

    /**
     * An in-place implementation of bubble sort, an \(O(n^2)\) sorting algorithm. Sorts the array into ascending order.
     * @param toSort An array of integer values to be sorted.
     */
    public static void bubbleSort(int[] toSort) {
        boolean swapped;
        for (int i = 0; i < toSort.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < toSort.length - i - 1; j++) {
                if (toSort[j] > toSort[j + 1]) {
                    // swap toSort[j] and toSort[j+1]
                    int temp = toSort[j];
                    toSort[j] = toSort[j + 1];
                    toSort[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped by inner loop, then break
            if (!swapped) break;
        }
    }


    /** A function to determine the nth element of the fibonnaci-sequence.
     * @param toFind The number in the sequence we want to find (e.g. the 5th element - not the index).
     * @return The number at that position in the sequence.
     */
    //https://en.wikipedia.org/wiki/Fibonacci_sequence

    public static long fibonacci(int toFind) {
        if (toFind <= 1) return toFind;

        long prev = 0, next = 1;
        for (int i = 2; i <= toFind; i++) {
            long sum = prev + next;
            prev = next;
            next = sum;
        }
        return next;
    }
}
