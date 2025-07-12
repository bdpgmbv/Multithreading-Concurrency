package com.vyshali.ConcurrencyAndMultithreading.ThreadPoolsAndExecutors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorsDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Runnable task
        executor.execute(() -> System.out.println("Running task by thread - " + Thread.currentThread().getName()));

        // Callable task with Future
        Future<Integer> future = executor.submit(() -> {
            System.out.println("Running task by thread - " + Thread.currentThread().getName());
            Thread.sleep(1000);
            return 42;
        });

        executor.execute(() -> System.out.println("Running task by thread - " + Thread.currentThread().getName()));

        System.out.println("Result: " + future.get()); // Blocks until done

        executor.shutdown();
    }
}
