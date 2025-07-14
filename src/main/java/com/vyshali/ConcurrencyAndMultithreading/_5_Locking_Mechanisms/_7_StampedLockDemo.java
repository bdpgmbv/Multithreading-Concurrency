package com.vyshali.ConcurrencyAndMultithreading._5_Locking_Mechanisms;

import java.util.concurrent.locks.StampedLock;

public class _7_StampedLockDemo {
    private static int inventory = 10; // Shared inventory count
    private static final StampedLock lock = new StampedLock();

    public static void main(String[] args) {
        // 1. OPTIMISTIC READER (fast but risky)
        new Thread(() -> {
            long stamp = lock.tryOptimisticRead(); // 😎 "I think no one is writing..."

            // Save value locally (might be changing now!)
            int localInventory = inventory;

            // Simulate reading delay
            try { Thread.sleep(200); } catch (InterruptedException e) {}

            // Check if data changed while reading
            if (!lock.validate(stamp)) {
                System.out.println("Optimistic read FAILED! Data changed during read.");

                // Fallback to safe read lock
                stamp = lock.readLock(); // 🔑 Get real read lock
                try {
                    localInventory = inventory; // Safe read
                    System.out.println("Safe read value: " + localInventory);
                } finally {
                    lock.unlockRead(stamp); // 🔓 Release lock
                }
            } else {
                System.out.println("Optimistic read SUCCESS! Value: " + localInventory);
            }
        }, "Optimistic-Reader").start();

        // 2. WRITER (updates inventory)
        new Thread(() -> {
            long stamp = lock.writeLock(); // 🔑 Get exclusive write lock
            try {
                System.out.println("\nWriter updating inventory...");
                inventory = 15; // Change value
                System.out.println("Inventory updated to: " + inventory);
            } finally {
                lock.unlockWrite(stamp); // 🔓 Release lock
            }
        }, "Writer").start();

        // 3. REGULAR READER (safe but slower)
        new Thread(() -> {
            long stamp = lock.readLock(); // 🔑 Get safe read lock
            try {
                System.out.println("\nRegular reader sees: " + inventory);
            } finally {
                lock.unlockRead(stamp); // 🔓 Release lock
            }
        }, "Regular-Reader").start();
    }
}
