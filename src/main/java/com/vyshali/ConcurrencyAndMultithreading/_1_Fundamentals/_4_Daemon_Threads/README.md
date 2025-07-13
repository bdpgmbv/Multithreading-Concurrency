## 5. Daemon Threads Example

**Explanation:**

- There are two workers:
    - A background worker (daemon) that cleans up files every second, running forever.
    - A main worker that does 3 tasks and then finishes.
- The background worker is set as a daemon thread.
- When the main worker finishes, the program exits immediately—even if the background worker is still running.

**Typical output:**
```
Daemon: Cleaning up temporary files...
Main Worker: Task 1
Daemon: Cleaning up temporary files...
Main Worker: Task 2
Daemon: Cleaning up temporary files...
Main Worker: Task 3
Main Worker: All tasks done!
(Program exits here - daemon stops mid-cleanup)
```

## Daemon Thread Facts

**Purpose:**
- Perform background tasks that don't need to complete.
- Automatically terminate when the main program finishes.

**Use Cases:**
- Auto-save/background saving features.
- Periodic cache cleanup.
- Monitoring/logging tasks.
- Garbage collection (JVM uses daemon threads!).

**Rules:**
- Must set daemon status before starting the thread.
- Can't convert to daemon after the thread starts.
- Daemon threads spawn daemon children by default.
- Avoid resource access in daemons (may abort mid-operation).

> **Warning:** Daemon threads are abruptly terminated—don't use them for critical operations like database commits!