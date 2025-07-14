package com.vyshali.ConcurrencyAndMultithreading._7_Atomic_Classes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.atomic.LongAdder;

public class AtomicDemo {
    // 1. AtomicInteger example
    private static AtomicInteger atomicCounter = new AtomicInteger(0);

    // 2. AtomicReference example
    private static AtomicReference<String> atomicMessage = new AtomicReference<>("Hello");

    // 3. LongAdder example (better for high contention)
    private static LongAdder adderCounter = new LongAdder();

    // 4. AtomicReferenceFieldUpdater example
    private static class BankAccount {
        volatile int balance; // Must be volatile for updater
    }
    private static BankAccount account = new BankAccount();
    private static final AtomicReferenceFieldUpdater<BankAccount, Integer> balanceUpdater =
            AtomicReferenceFieldUpdater.newUpdater(BankAccount.class, Integer.class, "balance");

    public static void main(String[] args) throws InterruptedException {
        // 1. ATOMIC INTEGER DEMO
        System.out.println("=== AtomicInteger ===");
        ExecutorService pool = Executors.newFixedThreadPool(10);

        // Start 10 threads to increment atomic counter
        for (int i = 0; i < 10; i++) {
            pool.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    atomicCounter.incrementAndGet(); // 🔄 Safe without locks
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("AtomicInteger result: " + atomicCounter.get());

        // 2. ATOMIC REFERENCE DEMO
        System.out.println("\n=== AtomicReference ===");
        new Thread(() -> {
            // Try change message if still "Hello"
            atomicMessage.compareAndSet("Hello", "World"); // 🔄 Check-and-swap
            System.out.println("Thread1: " + atomicMessage.get());
        }).start();

        new Thread(() -> {
            atomicMessage.compareAndSet("Hello", "Universe"); // Will fail
            System.out.println("Thread2: " + atomicMessage.get());
        }).start();

        Thread.sleep(100);

        // 3. LONGADDER DEMO (better for frequent updates)
        System.out.println("\n=== LongAdder ===");
        ExecutorService adderPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            adderPool.submit(() -> {
                for (int j = 0; j < 500; j++) {
                    adderCounter.increment(); // ➕ Add without contention
                }
            });
        }

        adderPool.shutdown();
        adderPool.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("LongAdder result: " + adderCounter.sum());

        // 4. ATOMIC FIELD UPDATER DEMO
        System.out.println("\n=== AtomicReferenceFieldUpdater ===");
        balanceUpdater.set(account, 100); // Start with $100

        new Thread(() -> {
            balanceUpdater.accumulateAndGet(account, 50, Integer::sum);
            System.out.println("Deposit $50: " + account.balance);
        }).start();

        new Thread(() -> {
            balanceUpdater.accumulateAndGet(account, 30, Integer::sum);
            System.out.println("Deposit $30: " + account.balance);
        }).start();
    }
}
