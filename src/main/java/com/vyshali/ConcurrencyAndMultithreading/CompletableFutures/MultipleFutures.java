package com.vyshali.ConcurrencyAndMultithreading.CompletableFutures;

import java.util.concurrent.CompletableFuture;

/*
What it does:
Demonstrates waiting for all futures vs. responding to first completed future.

Concurrency notes:
allOf creates barrier for multiple futures
anyOf reacts to fastest result
Different threads may complete different tasks
 */

public class MultipleFutures {
    public static void main(String[] args) {
        CompletableFuture<String> db = CompletableFuture.supplyAsync(() -> {
            System.out.println("Inside db supplyAsync(): " + Thread.currentThread().getName());
            sleep(800); return "Database Data";
        });
        CompletableFuture<String> api = CompletableFuture.supplyAsync(() -> {
            System.out.println("Inside api supplyAsync(): " + Thread.currentThread().getName());
            sleep(500); return "API Data";
        });

        // Wait for ALL
        CompletableFuture.allOf(db, api)
                .thenRun(() -> System.out.println("Both completed!"));

        // Get first result
        CompletableFuture.anyOf(db, api)
                .thenAccept(first -> System.out.println("First: " + first));

        sleep(1500);
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
