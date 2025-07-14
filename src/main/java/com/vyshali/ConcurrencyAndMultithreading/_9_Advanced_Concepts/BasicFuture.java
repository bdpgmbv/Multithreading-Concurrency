package com.vyshali.ConcurrencyAndMultithreading._9_Advanced_Concepts;

import java.util.concurrent.CompletableFuture;

/*
What it does:
Runs a simple task in a background thread and waits for the result.

Concurrency notes:
supplyAsync uses the default thread pool (ForkJoinPool)
get() blocks the main thread until result is ready
 */

public class BasicFuture {
    public static void main(String[] args) throws Exception {
        // Create a future that runs in background thread
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task running in supplyAsync(): " + Thread.currentThread().getName());
            return "Hello from Future!";
        });

        // Block and wait for result (main thread pauses here)
        String result = future.get();
        System.out.println("Main() thread - " +  Thread.currentThread().getName() + " got: " + result );
    }
}
