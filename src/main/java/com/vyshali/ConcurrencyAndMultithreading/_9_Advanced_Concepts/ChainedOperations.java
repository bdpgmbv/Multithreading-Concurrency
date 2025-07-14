package com.vyshali.ConcurrencyAndMultithreading._9_Advanced_Concepts;

import java.util.concurrent.CompletableFuture;

/*
What it does:
Chains three operations where each step depends on previous result.

Concurrency notes:
thenApply executes in same thread as previous stage
thenApplyAsync forces next operation to run in new thread
Automatic thread switching between stages
 */

public class ChainedOperations {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Inside the supplyAsync() block: " + Thread.currentThread().getName());
            return "order-123";
        }).thenApply(orderId -> { // Transform result
            System.out.println("Inside the thenApply() block: " + Thread.currentThread().getName());
            return "Order Details";
        }).thenApplyAsync(details -> { // Run in new thread
            System.out.println("Processing in thenApplyAsync() block: " + Thread.currentThread().getName());
            return details.toUpperCase();
        }).thenAccept(System.out::println);

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
