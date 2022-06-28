package io.adarsh.springdatajpaexp.thread;

import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
public class InDirectRunnableImplementation {
    public static void main(String []args) {
        Thread currentThread = Thread.currentThread();
        System.out.println("main() method is executing in " + currentThread.getName() + " thread");
        // creating an object of MyRunnable to represent a worker
        MyRunnable myRunnable = new MyRunnable();
        // invoking the run() of MyRunnable directly
        myRunnable.run();
        System.out.println("Control is back in main, Starting a child thread");
        // creating a thread object to represent a manager
        Thread thread = new Thread(myRunnable); // creating has-a relationship of Thread with runnable
        // getting a new thread started
        thread.start();

        for (int i=10; i>0; i--) {
            System.out.println("main: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {}
        }
        System.out.println("main() is completed");
    }
}
