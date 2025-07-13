package com.vyshali.ConcurrencyAndMultithreading._1_Fundamentals._3_Basic_Operations;

public class _2_SleepExample {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Thread: Starting task");
            try {
                Thread.sleep(2000);  // Pause thread for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread: Finished task");
        }).start();
    }
}
