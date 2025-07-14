package com.vyshali.ConcurrencyAndMultithreading._8_Synchronizers;

import java.util.concurrent.*;

public class SynchronizersDemo {
    public static void main(String[] args) throws Exception {
        // 1. COUNTDOWN LATCH - One-time event waiting
        System.out.println("=== CountDownLatch ===");
        CountDownLatch startLatch = new CountDownLatch(3); // Needs 3 countdowns

        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " is ready");
                startLatch.countDown(); // ⏱️ Signal ready
                try {
                    startLatch.await(); // 🛑 Wait for all to be ready
                    System.out.println(Thread.currentThread().getName() + " is running!");
                } catch (InterruptedException e) {}
            }, "Runner-" + i).start();
        }
        Thread.sleep(1000); // Wait for runners

        // 2. CYCLIC BARRIER - Reusable meeting point
        System.out.println("\n=== CyclicBarrier ===");
        CyclicBarrier meetingPoint = new CyclicBarrier(3,
                () -> System.out.println("All arrived! Let's continue..."));

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " reached barrier");
                    meetingPoint.await(); // ⛳ Wait for others
                    System.out.println(Thread.currentThread().getName() + " crossed barrier");
                } catch (Exception e) {}
            }).start();
        }
        Thread.sleep(1000);

        // 3. SEMAPHORE - Resource access control
        System.out.println("\n=== Semaphore ===");
        Semaphore parkingLot = new Semaphore(2); // 2 parking spots
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    parkingLot.acquire(); // 🚗 Try to park
                    System.out.println(Thread.currentThread().getName() + " parked");
                    Thread.sleep(1000); // Stay parked
                } catch (InterruptedException e) {
                } finally {
                    parkingLot.release(); // 🚗 Leave spot
                    System.out.println(Thread.currentThread().getName() + " left");
                }
            }, "Car-" + i).start();
        }
        Thread.sleep(3000);

        // 4. PHASER - Dynamic coordination
        System.out.println("\n=== Phaser ===");
        Phaser racePhaser = new Phaser(1); // Start with 1 party (main thread)

        for (int i = 1; i <= 3; i++) {
            racePhaser.register(); // ⚡ Add new runner
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " at start line");
                racePhaser.arriveAndAwaitAdvance(); // 🚩 Wait for all

                System.out.println(Thread.currentThread().getName() + " running...");
                racePhaser.arriveAndDeregister(); // 🏁 Finish race
            }, "Athlete-" + i).start();
        }
        Thread.sleep(500);
        racePhaser.arriveAndDeregister(); // 🚦 Start race!
        Thread.sleep(1000);

        // 5. EXCHANGER - Swap data between threads
        System.out.println("\n=== Exchanger ===");
        Exchanger<String> parcelExchange = new Exchanger<>();

        new Thread(() -> {
            try {
                String fromMain = parcelExchange.exchange("Parcel A");
                System.out.println("Thread received: " + fromMain);
            } catch (InterruptedException e) {}
        }, "Delivery-Thread").start();

        String fromThread = parcelExchange.exchange("Parcel B");
        System.out.println("Main received: " + fromThread);
    }
}
