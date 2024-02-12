import Javadoc_Exercise.Utils;

import java.rmi.server.UID;

public class Main {
    public static void main(String[] args) {

        /*
        // Timing the improved method
        long startTimeImproved = System.nanoTime();
        boolean resultImproved = Utils.isPrimeImproved(49919); //0.06752 seconds
        long endTimeImproved = System.nanoTime();
        double timeImproved = (endTimeImproved - startTimeImproved) / 1e7;
         */

        // Timing the original method
        long startTimeOriginal = System.nanoTime();
        boolean resultOriginal = Utils.isPrime(49919); //0.19996 seconds
        long endTimeOriginal = System.nanoTime();
        double timeOriginal = (endTimeOriginal - startTimeOriginal) / 1e7;


        System.out.println("Original method result: " + resultOriginal + ", Time taken: " + timeOriginal + " seconds");
        //System.out.println("Improved method result: " + resultImproved + ", Time taken: " + timeImproved + " seconds");

        // Reverse String
        String text = "Hello";
        System.out.println("String : " + text);
        System.out.println("Reverse : " + Utils.reverse(text));

        // Binary Search
        int[] myArr = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(Utils.binarySearch(myArr, 7)+1);

        // Fibonacci
        long toFind = Utils.fibonacci(11);
        System.out.println(toFind);

        // Bubble Sort Improved
        //int[] arrayToSort = {64, 34, 25, 12, 22, 11, 90}; //Not Sorted List
        int[] arrayToSort = {90, 64, 34, 25, 22, 12, 11}; // Descending Sorted List
        //int[] arrayToSort2 = {64, 34, 25, 12, 22, 11, 90};
        int[] arrayToSort2 = {90, 64, 34, 25, 22, 12, 11};
        System.out.println("Original Array:");
        for (int value : arrayToSort) {
            System.out.print(value + " ");
        }
        System.out.println();
        // Improved
//        long startTimeImprovedBS = System.nanoTime();
//        Utils.bubbleSortImproved(arrayToSort);
//        long endTimeImprovedBS = System.nanoTime();
//        double timeImprovedBS = (endTimeImprovedBS - startTimeImprovedBS) / 1e5;
        /*
        Not Sorted List = 0.065 seconds --> While Loop | 0.102 seconds --> Nested For Loop
        Descending Sorted List = 0.13953 seconds --> While Loop | 0.049 seconds --> Nested For Loop
         */
        // Original
        long startTimeOriginalBS = System.nanoTime();
        Utils.bubbleSort(arrayToSort);
        long endTimeOriginalBS = System.nanoTime();
        double timeOriginalBS = (endTimeOriginalBS - startTimeOriginalBS) / 1e5;

        System.out.println("Sorted Array:");
        for (int value : arrayToSort) {
            System.out.print(value + " ");
        }
//        System.out.println("Time taken Improved: " + timeImprovedBS + " seconds");
        System.out.println("Time taken Original: " + timeOriginalBS + " seconds");

        // Bubble Sort Original
    }
}