# Java Concurrency & Multithreading Roadmap
*From fundamentals to advanced concepts for challenging interviews*

## 1. Fundamentals
- **Thread Lifecycle**: States (`NEW`, `RUNNABLE`, `BLOCKED`, `WAITING`, `TIMED_WAITING`, `TERMINATED`)
- **Thread Creation**:
   - Extending `Thread` class
   - Implementing `Runnable` interface
- **Basic Operations**:
   - `start()`, `join()`, `sleep()`, `yield()`
   - Thread priorities
- **Daemon Threads**: Purpose and use cases

## 2. Thread Synchronization
- **Race Conditions**: Causes and examples 
  - (What? When 2+ threads try to change the same data at once, causing chaos. 
  - Example: Two threads updating your bank balance simultaneously → wrong balance!)
- **`synchronized` Keyword**:
   - Synchronized methods 
     - (Only one thread can use this method at a time.)
   - Synchronized blocks 
     - (Locks only part of a method.)
   - Intrinsic locks (monitors) 
     - (Every Java object has a hidden lock. synchronized uses this lock to block other threads.)
- **`volatile` Keyword**: Visibility guarantees and limitations 
  - (What? Forces threads to read the latest value of a variable (no cached copies). 
  - Limitation: Good for visibility (seeing changes), but not for complex operations (like count++).)
- **Wait/Notify Mechanism**:
   - `wait()`, 
     - (A thread pauses and releases its lock.)
   - `notify()`, 
     - (Wakes up one waiting thread.)
   - `notifyAll()` 
     - (Wakes up all waiting threads.)
   - Producer-Consumer pattern implementation 
     - (Producer makes data → Consumer uses data. 
     - If the queue is empty, consumers wait(). 
     - If the queue has data, producers notify() consumers.)

## 3. Java Memory Model (JMM) (How Threads "See" Data)
- **Happens-Before Relationship**: Guarantees and rules
    * (Guarantees: If Action A happens-before Action B, then A’s changes are visible to B.
    * Rules:
       * Unlock → Lock: After releasing a lock, changes are seen by the next thread locking it.
       * volatile write → read: Writes are visible to future reads.)
- **Memory Visibility**:
   - Shared variables
     - (Data accessed by multiple threads.)
   - Reordering prevention
     - (JMM stops the JVM from rearranging code in unsafe ways.)
   - Memory barriers
     - (CPU instructions that force order (like "flush changes to memory").)
- **Safe Publication Patterns**: 
  - (Safe ways to share objects between threads)
     - Immutable objects 
       - (Objects that never change after creation (e.g., String). Always safe!)
     - `final` fields 
       - (Fields marked final are visible to all threads once the object is created.)
     - Static initializers 
       - (Static blocks run once when the class loads → thread-safe setup.)

## 4. java.util.concurrent (JUC) - Core
- **Executor Framework**:
   - `Executor`,
     - (Runs tasks for you (like starting a car engine). You give it a task, it runs it.)
   - `ExecutorService`,
     - (Like Executor but fancier. Can run many tasks, track progress, and shut down neatly.)
   - `ScheduledExecutorService`
     - (Runs tasks at specific times (e.g., "do this every 5 seconds").)
- **Thread Pools**:
   - `FixedThreadPool`
     - (A team of fixed workers. If all workers are busy, new tasks wait in line.)
   - `CachedThreadPool`
     - (Hires temporary workers as needed. Good for quick tasks, but may create too many threads.)
   - `ForkJoinPool`
     - (Breaks big tasks into small pieces. Workers steal tasks from each other to stay busy.)
- **Callable & Future**: Asynchronous result handling
  - (Callable: A task that returns a result (e.g., "calculate 5+5").)
  - (Future: A "promise" that holds the result of a Callable task. You check it later.)
- **Thread Factory Patterns**: Custom thread creation
  - (Customizes how threads are created (e.g., naming threads, setting priority).)

## 5. Locking Mechanisms
- **`ReentrantLock`**:
  - (A lock that works like a key 🔑 to a room. Only one thread can hold the key at a time.)
  - `lock()`,
    - (Take the key (blocks if key is taken)
  - `unlock()`,
    - (Return the key)
  - `tryLock()`
    - (Try to take the key without waiting (returns true/false))
  - Fairness policies
    - (Lets waiting threads take turns (like a fair line).)
- **`ReadWriteLock`**:
   - `ReentrantReadWriteLock` implementation
     - (Read key: Many threads can hold it at once (for reading)
     - Write key: Only one thread can hold it (for writing))
- **`StampedLock` (Java 8+)**:
   - Optimistic locking
   - `tryOptimisticRead()`
     - (Optimistic locking: First reads without locking (fast), then checks if data changed. 
     - tryOptimisticRead(): "I think no one is writing... let me read quickly!")

## 6. Concurrent Collections
- **Thread-Safe Collections**:
   - `ConcurrentHashMap`
   - `CopyOnWriteArrayList`
   - `ConcurrentSkipListMap`
- **Blocking Queues**:
   - `ArrayBlockingQueue`
   - `LinkedBlockingQueue`
   - `PriorityBlockingQueue`
- **Synchronized Wrappers**: `Collections.synchronizedList()`

## 7. Atomic Classes
- **Atomic Variables**:
  - (These are "safe-update boxes" for numbers/objects when many threads change them at once.)
  - `AtomicInteger`
    - (Like a shared score counter that updates safely.)
  - `AtomicReference`
    - (A box holding an object (e.g., a user's name) that swaps safely.)
  - `LongAdder`
    - (Super-fast counter for heavy updates (e.g., counting website clicks).)
- **Compare-and-Swap (CAS)**:
   - Underlying principles
   - Non-blocking algorithms
     - (How atomic classes work:
     - Step 1: Check if the value is still what you expect. 
     - Step 2: Only then update it. 
     - If changed by another thread, try again. No waiting!)
- **Atomic Field Updaters**: `AtomicReferenceFieldUpdater`
  - (AtomicReferenceFieldUpdater: Safely updates a field inside an object (e.g., updating a player’s status in a game).)

## 8. Synchronizers
- **`CountDownLatch`**: One-time event waiting
  - (Like a "Ready, Set, Go!" race starter. All threads wait until a countdown hits zero.)
- **`CyclicBarrier`**: Reusable thread rendezvous
  - (A meeting point for threads (e.g., 4 friends wait at a café before ordering). Reusable!)
- **`Semaphore`**: Resource access control
  - (Like a nightclub bouncer—only allows X threads into a resource at a time.)
- **`Phaser` (Java 7+)**: Dynamic task coordination
  - (A flexible barrier where threads can join/leave dynamically (like group project phases).)
- **`Exchanger`**: Thread data swapping
  - (Two threads swap data (e.g., "I’ll give you my toy car for your action figure").)

## 9. Advanced Concepts
- **Deadlock/Livelock/Starvation**:  
  Detection, prevention (lock ordering, timeouts)
  - **Fork/Join Framework**:
    - `RecursiveTask`,
    - `RecursiveAction`, 
  - work-stealing
- **CompletableFuture (Java 8+)**:  
  Asynchronous chaining, composition, error handling
- **ThreadLocal**:  
  Per-thread data isolation (and pitfalls like memory leaks)

## 10. Performance & Scalability
- **Lock Contention**:  
  Bottlenecks and mitigation (lock splitting, striping)
- **False Sharing**:  
  Cache-line padding, `@Contended` (Java 8)
- **Non-Blocking Algorithms**:  
  Lock-free queues (e.g., `ConcurrentLinkedQueue`)

## 11. Testing & Debugging
- **Concurrency Bugs**:  
  Heisenbugs, race detectors, `ThreadDump` analysis
- **JUnit Testing**:  
  `@Timeout`, multithreaded test patterns
- **Tools**:  
  `jconsole`, `jstack`, Java Flight Recorder

## 12. Modern Patterns & Best Practices
- **Immutable Objects**:  
  Thread safety without synchronization
- **Reactive Programming**:  
  Project Reactor (though beyond core concurrency)
- **Virtual Threads (Project Loom)**:  
  Lightweight threads for high-throughput (Java 19+)