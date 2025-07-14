package com.vyshali.ConcurrencyAndMultithreading._9_Advanced_Concepts;

import java.util.concurrent.CompletableFuture;

/*
What it does:
Combines results from two independent futures (height/width) to calculate area.

Concurrency notes:
Both futures run concurrently in different threads
thenCombine waits for BOTH futures to complete before executing
 */

public class CombineFutures {
    public static void main(String[] args) {
        CompletableFuture<Integer> height = CompletableFuture.supplyAsync(() -> {
            System.out.println("Inside the supplyAsync() of height completable future: " + Thread.currentThread().getName());
            return 10;
        });
        CompletableFuture<Integer> width = CompletableFuture.supplyAsync(() -> {
            System.out.println("Inside the supplyAsync() of width completable future: " + Thread.currentThread().getName());
            return 5;
        });

        height.thenCombine(width, (h, w) -> {
            System.out.println("Calculating area in main(): " + Thread.currentThread().getName());
            return h * w;
        }).thenAccept(area -> System.out.println("Area: " + area));

        sleep(500); // Wait for async operations
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
