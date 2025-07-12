package com.vyshali.ConcurrencyAndMultithreading.SynchronizationAndLocks;

import java.util.concurrent.locks.ReentrantLock;

/*
Explanation:
t2 is interrupted while waiting for the lock and exits gracefully.
 */

public class InterruptibleLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void interruptibleTask() {
        try {
            lock.lockInterruptibly(); // Responds to interrupts
            try {
                System.out.println("Inside try block: " + Thread.currentThread().getName());
                Thread.sleep(5000); // Long operation
            } finally {
                lock.unlock();
                System.out.println("Inside finally block: " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            System.out.println("Inside the catch block (Thread was interrupted): " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptibleLockExample ile = new InterruptibleLockExample();

        Thread t1 = new Thread(ile::interruptibleTask);
        Thread t2 = new Thread(ile::interruptibleTask);

        t1.start();
        Thread.sleep(500); // Ensure t1 acquires the lock first
        t2.start();
        t2.interrupt(); // Interrupt t2 while it's waiting
    }
}
