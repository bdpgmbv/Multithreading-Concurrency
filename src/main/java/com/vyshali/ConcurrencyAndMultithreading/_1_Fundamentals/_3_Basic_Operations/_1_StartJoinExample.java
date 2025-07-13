package com.vyshali.ConcurrencyAndMultithreading._1_Fundamentals._3_Basic_Operations;

public class _1_StartJoinExample {
    public static void main(String[] args) throws InterruptedException {
        // Create a new thread
        Thread worker = new Thread(() -> {
            System.out.println("Worker started");
            System.out.println("Worker finished");
        });

        System.out.println("Main thread starting worker");
        worker.start();  // Start worker thread (creates new execution path)

        worker.join();   // Main thread waits here for worker to finish
        System.out.println("Main thread resumes after worker completes");
    }
}
