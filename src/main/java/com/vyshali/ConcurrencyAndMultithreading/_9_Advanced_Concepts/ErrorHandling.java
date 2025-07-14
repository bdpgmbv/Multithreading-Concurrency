package com.vyshali.ConcurrencyAndMultithreading._9_Advanced_Concepts;

import java.util.concurrent.CompletableFuture;

/*
What it does:
Simulates random failures and provides fallback value using exception handling.

Concurrency notes:
exceptionally catches exceptions from any previous stage
Recovery happens in same thread where exception occurred
 */

public class ErrorHandling {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Inside the supplyAsync(): " + Thread.currentThread().getName());
            if (Math.random() > 0.5) throw new RuntimeException("Database error!");
            return "Valid Data";
        }).exceptionally(ex -> { // Handle exception
            System.err.println("Please see the failed exception here: " + ex.getMessage());
            return "Fallback Data";
        }).thenAccept(data -> {
            System.out.println("Inside the thenAccept(): " + Thread.currentThread().getName());
            System.out.println("Final result: " + data);
        });

        sleep(1000);
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
