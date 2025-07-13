package com.vyshali.ConcurrencyAndMultithreading._5_Locking_Mechanisms;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class _6_ReentrantReadWriteLock {
    private static int balance = 100; // Shared bank balance
    private static final ReadWriteLock lock = new ReentrantReadWriteLock(true); // Fair lock

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Initial balance: $" + balance);

        // Create 3 reader threads (check balance)
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                lock.readLock().lock(); // 🔑 Get READ key
                try {
                    System.out.println(Thread.currentThread().getName() +
                            " sees balance: $" + balance);
                    Thread.sleep(500); // Reading takes time
                } catch (InterruptedException e) {
                } finally {
                    lock.readLock().unlock(); // 🔓 Return READ key
                }
            }, "Reader-" + i).start();
        }

        // Create 2 writer threads (update balance)
        for (int i = 1; i <= 2; i++) {
            final int amount = i * 50;
            new Thread(() -> {
                lock.writeLock().lock(); // 🔑 Get WRITE key (exclusive)
                try {
                    System.out.println("\n" + Thread.currentThread().getName() +
                            " depositing $" + amount);
                    int current = balance;
                    Thread.sleep(1000); // Writing takes time
                    balance = current + amount;
                    System.out.println(Thread.currentThread().getName() +
                            " done. New balance: $" + balance);
                } catch (InterruptedException e) {
                } finally {
                    lock.writeLock().unlock(); // 🔓 Return WRITE key
                }
            }, "Writer-" + i).start();
        }
    }
}
