package com.locks;

public class Starvation {
    public static void main(String[] args) {
        final Object arcadeMachine = new Object();

        Thread danThread = new Thread(() -> {
            while (true) {
                synchronized (arcadeMachine) {
                    System.out.println("Dan is playing the arcade grabbing machine game.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread mohitThread = new Thread(() -> {
            while (true) {
                synchronized (arcadeMachine) {
                    System.out.println("Mohit is trying to play the arcade grabbing machine game.");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        danThread.start();
        mohitThread.start();
    }
}
