package com.locks;

public class Livelock {
    public static void main(String[] args) {
        final Object ahmedGreet = new Object();
        final Object saleemGreet = new Object();

        final boolean[] isAhmedGreeting = {true};
        final boolean[] isSaleemGreeting = {true};

        Thread ahmedThread = new Thread(() -> {
            while (isAhmedGreeting[0]) {
                synchronized (ahmedGreet) {
                    System.out.println("Ahmed is trying to greet Saleem.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isAhmedGreeting[0] = true;
                }

                if (isSaleemGreeting[0]) {
                    System.out.println("Ahmed noticed Saleem is also greeting. Waiting for Saleem to finish.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Ahmed finishes greeting.");
        });

        Thread saleemThread = new Thread(() -> {
            while (isSaleemGreeting[0]) {
                synchronized (saleemGreet) {
                    System.out.println("Saleem is trying to greet Ahmed.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isSaleemGreeting[0] = true;
                }

                if (isAhmedGreeting[0]) {
                    System.out.println("Saleem noticed Ahmed is also greeting. Waiting for Ahmed to finish.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Saleem finishes greeting.");
        });

        ahmedThread.start();
        saleemThread.start();
    }
}
