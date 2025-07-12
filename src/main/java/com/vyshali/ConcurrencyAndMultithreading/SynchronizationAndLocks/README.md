### Synchronized Methods vs Synchronized Blocks
* The Core Problem: Race Conditions
* What Happens: When multiple threads access shared data simultaneously, they might "race" to modify it, leading to corrupted results.
* Interview Answer:"Race conditions occur when threads access shared state without coordination. The result depends on thread execution timing, causing unpredictable bugs."

Synchronized Methods 
* What: The entire method is locked.
* Lock object: Instance method → this (the current object), Static method → Class object (e.g., MyClass.class)

Synchronized Blocks
* What: Only a specific section of code is locked.
* Lock object: You explicitly choose any object (e.g., this, custom lock, String, etc.)

### Volatile
* The volatile keyword in Java is used to indicate that a variable's value will be modified by different threads. It ensures that changes made to a volatile variable by one thread are immediately visible to other threads, preventing thread-caching of the variable's value.
* Solution: volatile forces all reads/writes to go directly to main memory.

### Reentrant Lock 
A ReentrantLock is a mutual exclusion lock in Java that provides the same basic behavior as synchronized blocks/methods but with additional features like fairness, interruptible locking, and timed lock attempts. It's called "reentrant" because a thread can acquire the same lock multiple times without deadlocking itself.

#### Key Features:
* Reentrancy: A thread holding the lock can reacquire it without blocking. 
* Fairness: Supports first-come-first-served ordering (optional). 
* Interruptible Locking: lockInterruptibly() allows threads to respond to interrupts while waiting. 
* Timeout: tryLock() attempts to acquire the lock with a timeout. 
* Condition Support: Multiple Condition objects can be associated with the lock.

#### Best Practices:
* Always release locks in finally blocks to prevent deadlocks. 
* Avoid nested locks unless necessary (use reentrancy judiciously). 
* Prefer timeouts (tryLock) to avoid indefinite blocking.