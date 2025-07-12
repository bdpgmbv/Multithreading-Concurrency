### Thread Pools & Executors in Java
* Java's java.util.concurrent package provides a powerful framework for managing threads and asynchronous tasks.

1. ExecutorService
* An interface that manages thread execution. It decouples task submission from thread management.
* Key Methods:
   * execute(Runnable task): Executes a task. 
   * submit(Callable/Runnable): Submits a task and returns a Future. 
   * shutdown(): Stops accepting new tasks. 
   * awaitTermination(): Waits for running tasks to finish.
2. Thread Pools
   * Reuse a fixed set of threads to execute tasks. 
   * Avoids overhead of creating/destroying threads per task.
   * Common Types via Executors:
     * FixedThreadPool: Fixed number of threads.
     * CachedThreadPool: Dynamically scales threads (reuses idle threads).
     * SingleThreadExecutor: Single thread (sequential task execution).
     * ScheduledThreadPool: For delayed/periodic tasks.
     
     ```java
     ExecutorService fixedPool = Executors.newFixedThreadPool(4);
     
     ExecutorService cachedPool = Executors.newCachedThreadPool();
     
     ExecutorService singleThread = Executors.newSingleThreadExecutor();
     
     ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
     scheduler.schedule(() -> System.out.println("Delayed task"), 5, TimeUnit.SECONDS);
     ```
   * Why use thread pools? 
     * Resource efficiency (reuse threads). 
     * Control concurrency (e.g., limit to 10 threads). 
     * Built-in task queue (handles backlog).
3. Future
* Represents the result of an asynchronous task (from submit()). Used to:
  * Retrieve task results. 
  * Check task status. 
  * Cancel tasks.
* Key Methods:
  * get(): Blocks until task completes, returns result. 
  * isDone(): Checks if task completed. 
  * cancel(boolean mayInterrupt): Attempts task cancellation.