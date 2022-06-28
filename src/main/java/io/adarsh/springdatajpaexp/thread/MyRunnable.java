package io.adarsh.springdatajpaexp.thread;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

public class MyRunnable implements Runnable{

    @SneakyThrows
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
