package com.locks;

public class Deadlock {
    public static void main(String[] args) {
        final Object michalButton = new Object();
        final Object kevinButton = new Object();

        Thread michaelThread = new Thread(() -> {
            synchronized (michalButton) {
                System.out.println("Michal is waiting for Kevin to press the button.");
                synchronized (kevinButton) {
                    System.out.println("Michal goes through the door.");
                }
            }
        });

        Thread kevinThread = new Thread(() -> {
            synchronized (kevinButton) {
                System.out.println("Kevin is waiting for Michal to press the button.");
                synchronized (michalButton) {
                    System.out.println("Kevin goes through the door.");
                }
            }
        });
        michaelThread.start();
        kevinThread.start();
    }
}
