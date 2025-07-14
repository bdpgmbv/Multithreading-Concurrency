# Java Thread Pool and ScheduledExecutorService Example

## What the Program Does

- Creates a thread pool with 2 workers (`FixedThreadPool`).
- Submits two `Callable` tasks (`5+3` and `10+7`).
- Uses `Future` to get the results (waits if tasks are still running).
- Uses `ScheduledExecutorService` to run a task after a 2-second delay.
- Shuts down the pools to clean up resources.


## Concurrency Comments in Easy English

### FixedThreadPool
- Only 2 threads work at once.
- Safe for many tasks because extra tasks wait politely.

### Callable & Future
- `Callable` is like a worker who reports back with a result.
- `Future` is a receipt you check later for the result.

### ScheduledExecutorService
- Runs tasks on a timer without blocking other work.

### Shutdown
- Always shut down pools! Otherwise, threads stay alive like zombies.

## Key Tips for Your Interview

- **Thread Pools** save time by reusing threads (like reusing workers).
- `Future.get()` blocks your code – use it wisely!
- `ScheduledExecutorService` is great for timers/delays.
- Always shut down pools (forget this = memory leaks!).

## Thread Pools & Executors in Java
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