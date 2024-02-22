package com.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LivelockSolution {
    public static void main(String[] args) {
        final Lock ahmedGreetLock = new ReentrantLock();
        final Lock saleemGreetLock = new ReentrantLock();

        final boolean[] isAhmedGreeting = {true};
        final boolean[] isSaleemGreeting = {true};

        Thread ahmedThread = new Thread(() -> {
            while (isAhmedGreeting[0] && isSaleemGreeting[0]) {
                System.out.println("Ahmed is waiting to greet Saleem.");

                if (ahmedGreetLock.tryLock()) {
                    try {
                        if (isSaleemGreeting[0]) {
                            System.out.println("Ahmed starts greeting Saleem.");
                            isAhmedGreeting[0] = false;
                        }
                    } finally {
                        ahmedGreetLock.unlock();
                    }
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Ahmed greeted Saleem and continues.");
        });

        Thread saleemThread = new Thread(() -> {
            while (isSaleemGreeting[0] && isAhmedGreeting[0]) {
                System.out.println("Saleem is waiting to greet Ahmed.");

                if (saleemGreetLock.tryLock()) {
                    try {
                        if (isAhmedGreeting[0]) {
                            System.out.println("Saleem starts greeting Ahmed.");
                            isSaleemGreeting[0] = false;
                        }
                    } finally {
                        saleemGreetLock.unlock();
                    }
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Saleem greeted Ahmed and continues.");
        });

        ahmedThread.start();
        saleemThread.start();
    }
}
