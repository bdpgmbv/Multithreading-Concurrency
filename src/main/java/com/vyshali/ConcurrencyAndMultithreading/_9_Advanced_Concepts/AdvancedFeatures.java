package com.vyshali.ConcurrencyAndMultithreading._9_Advanced_Concepts;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/*
What it does:
Shows timeout handling and composing complex async workflows.

Concurrency notes:
completeOnTimeout forces completion if time expires
thenCompose flattens nested futures (monadic bind)
Timeouts use ForkJoinPool's scheduler thread
 */

public class AdvancedFeatures {
    public static void main(String[] args) {
        Supplier<String> slowTask = () -> {
            sleep(2000);
            return "Slow Result";
        };

        // Timeout after 1 second
        CompletableFuture.supplyAsync(slowTask)
                .completeOnTimeout("Timeout Fallback", 1, TimeUnit.SECONDS)
                .thenAccept(result -> System.out.println("Result: " + result));

        // Compose async operations
        CompletableFuture.supplyAsync(() -> "UserID")
                .thenCompose(id -> getUserDetails(id))
                .thenAccept(System.out::println);

        sleep(3000);
    }

    static CompletableFuture<String> getUserDetails(String id) {
        return CompletableFuture.supplyAsync(() -> "Details for " + id);
    }

    private static void sleep(int ms) { /* same as before */ }
}
