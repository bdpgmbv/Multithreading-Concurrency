package com.vyshali.ConcurrencyAndMultithreading.SynchronizationAndLocks;

import java.util.concurrent.locks.ReentrantLock;

/*
Explanation:
Only one thread executes the criticalSection() at a time. The lock() and unlock() calls ensure mutual exclusion.
 */

public class BasicReentrantLock {
    private final ReentrantLock lock = new ReentrantLock();

    public void criticalSection() {
        lock.lock(); // Acquire the lock
        try {
            System.out.println("Inside criticalSection try: " + Thread.currentThread().getName());
            Thread.sleep(1000); // Simulate work
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Always release in finally block
            System.out.println("Inside criticalSection finally: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        BasicReentrantLock brl = new BasicReentrantLock();
        for(int i = 0; i < 3; i++) {
            new Thread(brl::criticalSection).start();
        }
    }
}
