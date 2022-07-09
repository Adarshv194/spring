package io.adarsh.springdatajpaexp.thread.JB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadSynchronizationStateCheck {

    public static void main(String[] args) {
        System.out.println("Creating resource...");
        Object resource = new Object();
        List<Thread> threadList = new ArrayList<>();
        Thread monitorThread = new Thread(() -> {
            while (true) {
                threadList.forEach(thread -> System.out.print(thread.getName() + ": " + thread.getState() + ", "));
                System.out.println("");
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread interruption handled...............");
                    break;
                }
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    System.out.println("Thread is in: " + Thread.currentThread().getState());
                    System.out.println("Thread gets interrupted");
                    e.printStackTrace();
                    break;
                }
            }
        });
//        monitorThread.setDaemon(true);
        monitorThread.start();
        Thread thread1 = new Thread(() -> {
            synchronized (resource) {
                try {
                    System.out.println("Execution started in Thread 1");
                    Thread.sleep(2000);
                    System.out.println("Execution completed in Thread 1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.setName("thread1");
        threadList.add(thread1);
        thread1.start();
        Thread thread2 = new Thread(() -> {
            synchronized (resource) {
                try {
                    System.out.println("Execution started in Thread 2");
                    Thread.sleep(2000);
                    System.out.println("Execution completed in Thread 2");
                    System.out.println("Interrupting monitor thread when its in :" + monitorThread.getState());
                    monitorThread.interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.setName("thread2");
        threadList.add(thread2);
        thread2.start();
    }
}
