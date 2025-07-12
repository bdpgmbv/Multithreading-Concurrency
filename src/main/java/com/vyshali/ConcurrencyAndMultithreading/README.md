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
- **`synchronized` Keyword**:
   - Synchronized methods
   - Synchronized blocks
   - Intrinsic locks (monitors)
- **`volatile` Keyword**: Visibility guarantees and limitations
- **Wait/Notify Mechanism**:
   - `wait()`, `notify()`, `notifyAll()`
   - Producer-Consumer pattern implementation

## 3. Java Memory Model (JMM)
- **Happens-Before Relationship**: Guarantees and rules
- **Memory Visibility**:
   - Shared variables
   - Reordering prevention
   - Memory barriers
- **Safe Publication Patterns**:
   - Immutable objects
   - `final` fields
   - Static initializers

## 4. java.util.concurrent (JUC) - Core
- **Executor Framework**:
   - `Executor`, `ExecutorService`, `ScheduledExecutorService`
- **Thread Pools**:
   - `FixedThreadPool`
   - `CachedThreadPool`
   - `ForkJoinPool`
- **Callable & Future**: Asynchronous result handling
- **Thread Factory Patterns**: Custom thread creation

## 5. Locking Mechanisms
- **`ReentrantLock`**:
   - `lock()`, `unlock()`, `tryLock()`
   - Fairness policies
- **`ReadWriteLock`**:
   - `ReentrantReadWriteLock` implementation
- **`StampedLock` (Java 8+)**:
   - Optimistic locking
   - `tryOptimisticRead()`

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
   - `AtomicInteger`
   - `AtomicReference`
   - `LongAdder`
- **Compare-and-Swap (CAS)**:
   - Underlying principles
   - Non-blocking algorithms
- **Atomic Field Updaters**: `AtomicReferenceFieldUpdater`

## 8. Synchronizers
- **`CountDownLatch`**: One-time event waiting
- **`CyclicBarrier`**: Reusable thread rendezvous
- **`Semaphore`**: Resource access control
- **`Phaser` (Java 7+)**: Dynamic task coordination
- **`Exchanger`**: Thread data swapping

## 9. Advanced Concepts
- **Deadlock/Livelock/Starvation**:  
  Detection, prevention (lock ordering, timeouts)
- **Fork/Join Framework**:  
  `RecursiveTask`, `RecursiveAction`, work-stealing
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