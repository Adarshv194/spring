package io.adarsh.springdatajpaexp.thread.JB;

public class ThreadSleepControl {
    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();
        System.out.println("Code is running in: " + currentThread.getName() + ", with state: " + currentThread.getState());
        Thread t1 = new Thread(() -> {
            System.out.println("Thread T1 started");
            System.out.println("Putting Thread T1 to sleep");
            try {
                Thread.sleep(20000); // on sleep, it will not hold the processor, thread goes into TIMED_WAITING state and processor will work on other threads
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread1 completed it's sleep cycle");
        });
        System.out.println("Starting new thread");
        t1.start();
        for (int i=1; i<100000; i++) {
            System.out.print(i);
        }
    }
}
