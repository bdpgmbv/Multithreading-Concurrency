package com.vyshali.ConcurrencyAndMultithreading._2_Thread_Synchronization._2_volatile_Keyword;

public class VolatileFixedExample {
    public static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("Inside thread: " +  Thread.currentThread().getName());
            for(int i = 0; i < 1000; i++) {
                if(flag) {
                    System.out.println(i);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Inside thread: " +  Thread.currentThread().getName());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flag = false;
        });

        t1.start();
        t2.start();
    }
}
