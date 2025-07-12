package com.vyshali.ConcurrencyAndMultithreading.SynchronizationAndLocks;

public class VolatileBrokenExample {
    private static boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Inside thread: " +  Thread.currentThread().getName());
            for(int i = 0; i < 1000; i++) {
                if(flag) {
                   System.out.println(i);
               }
            }
        }).start();

        new Thread(() -> {
            System.out.println("Inside thread: " +  Thread.currentThread().getName());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flag = true;
        }).start();
    }
}
