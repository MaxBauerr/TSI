package com.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockSolution {
    public static void main(String[] args) {
        final Lock michalButtonLock = new ReentrantLock();
        final Lock kevinButtonLock = new ReentrantLock();

        Thread michaelThread = new Thread(() -> {
            while (true) {
                if (michalButtonLock.tryLock()) {
                    try {
                        System.out.println("Michal is waiting for Kevin to press the button.");
                        if (kevinButtonLock.tryLock()) {
                            try {
                                System.out.println("Michal goes through the door.");
                                return;
                            } finally {
                                kevinButtonLock.unlock();
                            }
                        }
                    } finally {
                        michalButtonLock.unlock();
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread kevinThread = new Thread(() -> {
            while (true) {
                if (kevinButtonLock.tryLock()) {
                    try {
                        System.out.println("Kevin is waiting for Michal to press the button.");
                        if (michalButtonLock.tryLock()) {
                            try {
                                System.out.println("Kevin goes through the door.");
                                return;
                            } finally {
                                michalButtonLock.unlock();
                            }
                        }
                    } finally {
                        kevinButtonLock.unlock();
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        michaelThread.start();
        kevinThread.start();
    }
}
