package io.adarsh.springdatajpaexp.thread;

import org.springframework.scheduling.annotation.Async;

public class MyThreadCumRunnable extends Thread {

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        System.out.println("run() of MyRunnable is executing in " + currentThread.getName() + " thread");
        for (int i=0; i<=10; i++) {
            System.out.println("run: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {}
        }
        System.out.println("run() is completed");
    }

}
