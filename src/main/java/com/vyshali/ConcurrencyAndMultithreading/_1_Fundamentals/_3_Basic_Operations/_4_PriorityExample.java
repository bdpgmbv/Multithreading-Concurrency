package com.vyshali.ConcurrencyAndMultithreading._1_Fundamentals._3_Basic_Operations;

public class _4_PriorityExample {
    public static void main(String[] args) {
        Thread lowPriority = new Thread(() -> {
            System.out.println("Low-priority thread started");
            for (int i = 0; i < 5; i++); // Simulate work
            System.out.println("Low-priority thread finished");
        });

        Thread highPriority = new Thread(() -> {
            System.out.println("High-priority thread started");
            for (int i = 0; i < 5; i++); // Simulate work
            System.out.println("High-priority thread finished");
        });

        lowPriority.setPriority(Thread.MIN_PRIORITY);   // Priority 1 (lowest)
        highPriority.setPriority(Thread.MAX_PRIORITY);  // Priority 10 (highest)

        highPriority.start();
        lowPriority.start();
    }
}
