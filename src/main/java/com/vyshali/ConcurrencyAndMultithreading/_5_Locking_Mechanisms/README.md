# ReadWriteLock Example: Easy English Guide

## What the Program Does

- **Starting Point:**
    - Bank account starts with \$100.
    - 3 readers want to check the balance.
    - 2 writers want to deposit money (\$50 and \$100).

## ReadWriteLock Rules

- 🔑 **Read Key:** Many can hold → Readers work together.
- 🔑 **Write Key:** Only one can hold → Writers work alone.

## Sample Output

```
Initial balance: $100
Reader-1 sees balance: $100
Reader-2 sees balance: $100
Reader-3 sees balance: $100

Writer-1 depositing $50
Writer-1 done. New balance: $150

Writer-2 depositing $100
Writer-2 done. New balance: $250
```

## Concurrency Comments (Easy English)
### Readers Work Together

```java
lock.readLock().lock(); // 🔑 Get READ key
try {
        // All readers enter together!
        } finally {
        lock.readLock().unlock(); // 🔓 Return key
}
```
- All 3 readers see "$100" at same time
- Like friends looking at same bank statement

### Writer Gets Exclusive Access:
```java
lock.writeLock().lock(); // 🔑 Get WRITE key (exclusive)
try {
    // No one else can read or write now!
} finally {
    lock.writeLock().unlock(); // 🔓 Return key
}
```
- When Writer-1 works, everyone waits
- Balance becomes $150 safely

### Writers Take Turns:
- Writer-2 must wait for Writer-1 to finish
- Like a bathroom with one key - next writer waits

### Fairness Matters:
``` java
new ReentrantReadWriteLock(true); // Fair lock
```
- Ensures first-come-first-served
- Prefers waiting readers/writers over new requests

## Key Points for Interview

### When to Use
- Use when you have many readers and few writers.
- Example: Bank apps (many check balance, few transfer).

### Performance
- Better than `synchronized` because readers don't block each other.
- Writers still block everyone (readers + other writers).

### Always Unlock
- Use `try-finally` to prevent deadlocks.
- Forgetting unlock = frozen program!

### Fairness Tradeoff
- Fair locks = more waiting but no starvation.
- Unfair locks = better throughput but unfair.

## Real-Life Analogy

Imagine a library with special rules:
- **Reading Room:** Many people can read books together.
- **Writing Room:** Only one person can edit books at a time.
- **Rule:** When someone is editing, no one can enter either room!