package com.vyshali.ConcurrencyAndMultithreading.SynchronizationAndLocks;

import java.util.concurrent.locks.ReentrantLock;

/*
Explanation:
Fairness reduces thread starvation by granting the lock to the longest-waiting thread.
 */

public class FairLockExample {
    private final ReentrantLock lock = new ReentrantLock(true);

    // Create a fair lock (longest-waiting thread gets priority)
    public void fairMethod() {
        for(int i = 0; i < 2; i++) {
            lock.lock();
            try {
                System.out.println("Inside try block: " + Thread.currentThread().getName());
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("Inside finally block: " + Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        FairLockExample fle = new FairLockExample();

        // Create 3 threads
        for(int i = 0; i < 3; i++) {
            new Thread(fle::fairMethod).start();
        }
    }
}
