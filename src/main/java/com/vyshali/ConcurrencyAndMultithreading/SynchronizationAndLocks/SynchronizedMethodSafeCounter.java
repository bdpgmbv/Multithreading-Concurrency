package com.vyshali.ConcurrencyAndMultithreading.SynchronizationAndLocks;

public class SynchronizedMethodSafeCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedMethodSafeCounter sc = new SynchronizedMethodSafeCounter();

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                sc.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                sc.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count: " + sc.count);
    }
}
