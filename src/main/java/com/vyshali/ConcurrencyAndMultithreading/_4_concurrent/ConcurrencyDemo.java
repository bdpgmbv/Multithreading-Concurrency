package com.vyshali.ConcurrencyAndMultithreading._4_concurrent;

import java.util.concurrent.*;

public class ConcurrencyDemo {
    public static void main(String[] args) throws Exception {
        // 1. ExecutorService with FixedThreadPool (2 workers)
        ExecutorService fixedPool = Executors.newFixedThreadPool(2);
        // This pool uses 2 threads. If both are busy, tasks wait in a queue.

        // 2. Submit Callable tasks (they return results)
        Future<Integer> future1 = fixedPool.submit(new AddTask(5, 3)); // Task 1: 5+3
        Future<Integer> future2 = fixedPool.submit(new AddTask(10, 7)); // Task 2: 10+7

        // 3. Get results using Future (waits if task isn't done)
        System.out.println("Result 1: " + future1.get()); // Waits for Task 1 to finish
        System.out.println("Result 2: " + future2.get());

        // 4. ScheduledExecutorService (run a task after 2 seconds)
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> System.out.println("Scheduled task!"), 2, TimeUnit.SECONDS);

        // 5. Shut down pools (important!)
        fixedPool.shutdown();
        scheduler.shutdown();
    }

    // Callable task that adds two numbers
    static class AddTask implements Callable<Integer> {
        private int a, b;
        AddTask(int a, int b) { this.a = a; this.b = b; }
        public Integer call() {
            return a + b; // Returns result
        }
    }
}
