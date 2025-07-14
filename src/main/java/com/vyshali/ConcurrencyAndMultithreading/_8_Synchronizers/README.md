## What the Program Does (Easy English)

### CountDownLatch
- 3 runners get ready.
- All wait until everyone is ready.
- Then all start running together.

### CyclicBarrier
- 3 threads meet at barrier.
- All wait until all arrive.
- Then all continue together.

### Semaphore
- Parking lot with 2 spots.
- 5 cars try to park.
- Only 2 park at a time, others wait.

### Phaser
- 3 athletes wait at start line.
- All start running together when race starts.
- Each finishes independently.

### Exchanger
- Main thread and delivery thread swap parcels.

## Concurrency Comments (Easy English)

### 1. CountDownLatch (One-time event)
```java
startLatch.countDown(); // ⏱️ "I'm ready!"
startLatch.await();    // 🛑 "Wait for others to be ready"
```
- Like: Friends waiting to start video call.
- **Use for:** Initialization before starting main work.

### 2. CyclicBarrier (Reusable meeting)
```java
meetingPoint.await(); // ⛳ "Wait here for others"
```
- Like: Tour group waiting at meeting point.
- **Reset automatically** for next meeting.

### 3. Semaphore (Resource control)
```java
parkingLot.acquire(); // 🚗 "Take parking spot"
parkingLot.release(); // 🚗 "Leave spot"
```
- Like: Limited parking spaces.
- **Controls access** to scarce resources.

### 4. Phaser (Dynamic coordination)
```java
racePhaser.register();    // ⚡ "Add me to group"
racePhaser.arriveAndAwaitAdvance(); // 🚩 "Wait for group"
racePhaser.arriveAndDeregister();   // 🏁 "I'm leaving group"
```
- Like: School field trip with flexible groups.
- **Handles changing** number of participants.

### 5. Exchanger (Data swap)
```java 
parcelExchange.exchange("Gift"); // 📦 "Swap parcels"
```
- Like: Two people exchanging packages.
- **Blocks until** both threads are ready.

## Sample Output:
```plaintext
=== CountDownLatch ===
Runner-1 is ready
Runner-2 is ready
Runner-3 is ready
Runner-3 is running!
Runner-1 is running!
Runner-2 is running!

=== CyclicBarrier ===
Thread-0 reached barrier
Thread-1 reached barrier
Thread-2 reached barrier
All arrived! Let's continue...
Thread-2 crossed barrier
Thread-0 crossed barrier
Thread-1 crossed barrier

=== Semaphore ===
Car-1 parked
Car-2 parked
Car-1 left
Car-3 parked
Car-2 left
Car-4 parked
...

=== Phaser ===
Athlete-1 at start line
Athlete-2 at start line
Athlete-3 at start line
Athlete-1 running...
Athlete-3 running...
Athlete-2 running...

=== Exchanger ===
Main received: Parcel A
Thread received: Parcel B
```

## Synchronizer Cheat Sheet

| Synchronizer    | Best For                      | Real-Life Analogy           |
|-----------------|------------------------------|-----------------------------|
| CountDownLatch  | One-time startup events      | Rocket launch countdown     |
| CyclicBarrier   | Repeated group meetings      | Tour group gathering        |
| Semaphore       | Resource pooling             | Parking lot management      |
| Phaser          | Dynamic multi-stage coordination | Video game boss phases  |
| Exchanger       | Thread data swapping         | Prisoner swap               |

---

💡 **Interview Tip:** Remember these key points:
- **CountDownLatch:** One-time use, counts down to zero
- **CyclicBarrier:** Reusable, all threads wait at barrier
- **Semaphore:** Manages resource pools
- **Phaser:** Handles complex multi-phase tasks
- **Exchanger:** Simple two-thread data swap