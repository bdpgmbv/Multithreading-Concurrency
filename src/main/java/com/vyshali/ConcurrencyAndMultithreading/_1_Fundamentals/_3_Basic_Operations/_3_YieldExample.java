package com.vyshali.ConcurrencyAndMultithreading._1_Fundamentals._3_Basic_Operations;

public class _3_YieldExample {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Thread A: " + i);
                Thread.yield();  // Suggest giving CPU to other threads
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Thread B: " + i);
            }
        }).start();
    }
}
