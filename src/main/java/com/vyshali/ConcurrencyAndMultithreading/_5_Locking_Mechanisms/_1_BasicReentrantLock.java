package com.vyshali.ConcurrencyAndMultithreading._5_Locking_Mechanisms;

import java.util.concurrent.locks.ReentrantLock;

/*
Explanation:
Only one thread executes the criticalSection() at a time. The lock() and unlock() calls ensure mutual exclusion.
 */

public class _1_BasicReentrantLock {
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
        _1_BasicReentrantLock brl = new _1_BasicReentrantLock();
        for(int i = 0; i < 3; i++) {
            new Thread(brl::criticalSection).start();
        }
    }
}
