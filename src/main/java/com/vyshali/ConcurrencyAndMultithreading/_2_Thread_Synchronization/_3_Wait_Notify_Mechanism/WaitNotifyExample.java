package com.vyshali.ConcurrencyAndMultithreading._2_Thread_Synchronization._3_Wait_Notify_Mechanism;

public class WaitNotifyExample {
    public static void main(String[] args) {
        final Object object = new Object();

        Thread waiter = new Thread(() -> {
            synchronized(object) {
                try {
                    System.out.println("Inside try block: " + Thread.currentThread().getName());
                    object.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Received signal from other thread: " + Thread.currentThread().getName());
            }
        });

        Thread notifier = new Thread(() -> {
            synchronized(object) {
                try {
                    System.out.println("Inside try block: " + Thread.currentThread().getName());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Notifying other threads: " + Thread.currentThread().getName());
                object.notify();
            }
        });

        waiter.start();
        notifier.start();
    }
}
