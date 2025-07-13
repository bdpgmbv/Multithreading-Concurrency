package com.vyshali.ConcurrencyAndMultithreading._1_Fundamentals._4_Daemon_Threads;

public class DaemonThreadExample {
    public static void main(String[] args) {
        // Create a daemon thread (background worker)
        Thread daemonWorker = new Thread(() -> {
            while (true) {  // Runs forever
                System.out.println("Daemon: Cleaning up temporary files...");
                try {
                    Thread.sleep(1000);  // Work every 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create a normal thread (main worker)
        Thread mainWorker = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Main Worker: Task " + i);
                try {
                    Thread.sleep(1500);  // Simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Main Worker: All tasks done!");
        });

        // SET DAEMON STATUS BEFORE START
        daemonWorker.setDaemon(true);  // Mark as background worker

        daemonWorker.start();  // Start daemon thread
        mainWorker.start();    // Start main worker thread
    }
}
