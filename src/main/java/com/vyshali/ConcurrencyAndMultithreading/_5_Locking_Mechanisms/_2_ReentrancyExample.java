package com.vyshali.ConcurrencyAndMultithreading._5_Locking_Mechanisms;

import java.util.concurrent.locks.ReentrantLock;

/*
Explanation:
The same thread acquires the lock in outer(), then reacquires it in inner() without deadlocking.
 */

public class _2_ReentrancyExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void outer() {
        lock.lock();
        try {
            System.out.println("Inside try block - outer function: " + Thread.currentThread().getName());
            Thread.sleep(1000);
            inner(); // Call inner method while holding the lock
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("Inside finally block - outer function: " + Thread.currentThread().getName());
        }
    }

    public void inner() {
        lock.lock(); // Reacquire the same lock
        try {
            System.out.println("Inside try block - inner function: " + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("Inside finally block - inner function: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        _2_ReentrancyExample re = new _2_ReentrancyExample();
        re.outer();
    }
}
