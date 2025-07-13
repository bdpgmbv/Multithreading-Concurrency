## 1. `start()` and `join()` Example

**Explanation:**

- The main thread creates and starts a worker thread.
- `start()` begins parallel execution.
- `join()` makes the main thread wait until the worker finishes.

**Output order:**

1. Main starting message
2. Worker messages
3. Main resuming message

## 2. `sleep()` Example

**Explanation:**

- The thread starts, prints a message, then pauses for 2 seconds using `sleep()`.
- During `sleep`, other threads can use the CPU.
- After waking up, it prints the completion message.

**Output:**
```text
Thread: Starting task
(2-second pause)
Thread: Finished task
```

## 3. `yield()` Example

**Explanation:**

- Two threads (A and B) run concurrently.
- `yield()` in Thread A politely suggests letting other threads run first (but the OS may ignore this).

**Possible output (order may vary):**

```text
Thread A: 1
Thread B: 1
Thread B: 2
Thread B: 3
Thread A: 2
Thread A: 3
```

## 4. Thread Priorities Example

**Explanation:**

- Two threads with different priorities (`1` = low, `10` = high).
- Higher-priority threads are more likely to run first (but not guaranteed).

**Typical output:**
```text
High-priority thread started
High-priority thread finished
Low-priority thread started
Low-priority thread finished
```