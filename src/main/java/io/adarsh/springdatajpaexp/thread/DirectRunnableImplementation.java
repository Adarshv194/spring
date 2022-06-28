package io.adarsh.springdatajpaexp.thread;

import io.adarsh.springdatajpaexp.model.SectionTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class DirectRunnableImplementation {

    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();
        System.out.println("main() method is executing in " + currentThread.getName() + " thread");
        // creating a MyThreadCumRunnable object to represent the manager cum worker
        MyThreadCumRunnable myThreadCumRunnable = new MyThreadCumRunnable();
        // invoking the run() of MyThreadCumRunnable directly
        myThreadCumRunnable.run();
        System.out.println("Control is back in main, Starting a child thread");
        // getting a new thread started
        myThreadCumRunnable.start();
        new SectionTest().test();
        for (int i=10; i>0; i--) {
            System.out.println("main: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {}
        }
        System.out.println("main() is completed");
    }
}
