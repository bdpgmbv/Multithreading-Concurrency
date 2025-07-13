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
