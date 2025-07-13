#### Java's wait(), notify(), and notifyAll() methods enable thread coordination for shared resources. 
#### They are part of the Object class and must be used within synchronized blocks/methods.

### Core Concepts
#### wait()
* Releases the object's lock and suspends the current thread. 
* Thread remains inactive until another thread calls notify()/notifyAll(). 
* Syntax: object.wait();

#### notify()
* Wakes up one arbitrary waiting thread. 
* Does not release the lock immediately; the awakened thread competes for the lock. 
* Syntax: object.notify();

#### notifyAll()
* Wakes up all waiting threads. 
* Threads compete for the lock in an order determined by the JVM scheduler. 
* Syntax: object.notifyAll();

```
Loop for Condition Checking
Always use while loops (not if) to re-check conditions after waking:

while (condition) {
    wait();
}

Prevents spurious wakeups (unexpected thread wakeups without notification).
```
