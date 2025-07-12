package com.vyshali.ConcurrencyAndMultithreading._1_Fundamentals._2_ThreadCreation;

// Method 1: Extend Thread
class MyThread extends Thread {
    public void run() {
        System.out.println("MyThread is running: " + Thread.currentThread().getName());
    }
}

// Method 2: Implement Runnable
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable is running: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        // Start thread 1
        new MyThread().start();

        // Start thread 2
        new Thread(new MyRunnable()).start();

        // Lambda thread
        new Thread(() -> {
            System.out.println("Thread with lambda is running: " + Thread.currentThread().getName());
        }).start();
    }
}
