package com.vyshali.ConcurrencyAndMultithreading._5_Locking_Mechanisms;

/*
Explanation:
Thread-0 acquires the lock and holds it for 3 seconds. Thread-1 times out after 2 seconds and fails to acquire it.
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class _3_TryLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void performTask() throws InterruptedException {
        if(lock.tryLock(2, TimeUnit.SECONDS)) { // Wait up to 2 seconds
            try {
                System.out.println("Inside try block: " + Thread.currentThread().getName());
                Thread.sleep(3000); // Hold lock for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("Inside finally block: " + Thread.currentThread().getName());
            }
        } else {
            System.out.println("Could not acquire lock: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        _3_TryLockExample tle = new _3_TryLockExample();

        new Thread(() -> {
            try {
                tle.performTask();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                tle.performTask();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
