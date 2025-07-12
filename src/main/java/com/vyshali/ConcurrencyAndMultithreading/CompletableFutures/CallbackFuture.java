package com.vyshali.ConcurrencyAndMultithreading.CompletableFutures;

import java.util.concurrent.CompletableFuture;

/*
What it does:
Processes data asynchronously and handles result with callback without blocking main thread.

Concurrency notes:
thenAccept callback executes in same thread as supplier or default thread pool
Main thread continues immediately after setting up pipeline
 */

public class CallbackFuture {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Performing task in supplyAsync() with Thread " + Thread.currentThread().getName());
            sleep(1000); // Simulate work
            return "Processed Data";
        }).thenAccept(result -> { // Callback runs when future completes
            System.out.println("Received result in thenAccept() block: " + result + " in " + Thread.currentThread().getName());
        });

        System.out.println("Main thread free to do other work!");
        sleep(2000); // Keep JVM alive
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
