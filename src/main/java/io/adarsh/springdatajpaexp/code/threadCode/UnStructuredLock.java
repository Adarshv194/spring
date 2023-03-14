package io.adarsh.springdatajpaexp.code.threadCode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnStructuredLock implements Runnable {

    ReentrantLock lock = new ReentrantLock();
    Lock lock2 = new ReentrantLock();
    int value = 0;

    public void incrementValue() {
        value = value + 1;
        System.out.println("Value increments to: " + value + " for thread: " + Thread.currentThread().getName());
    }

    public void decrementValue() {
        value = value - 1;
        System.out.println("Value decrements to: " + value + " for thread: " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        while (true) {
            // When we use lock to protect the critical section, when lock is already been acquired by a thread then
            // the coming threads wil go into WAITING state rather than BLOCKED state(which happen using synchronized block)
            if (lock.tryLock()) {
                try {
                    Thread currentThread = Thread.currentThread();
                    System.out.println("Code is executing for: " + currentThread.getName());
                    try {
//                        lock.lock();
                        boolean flag = lock.isHeldByCurrentThread();
                        System.out.println("Incrementing value for thread: " + currentThread.getName());
                        incrementValue();
                        System.out.println("Putting the thread: " + currentThread.getName() + " to sleep");
                        Thread.sleep(2000);
                        System.out.println("Decrementing value for thread: " + currentThread.getName());
                        decrementValue();
                        System.out.println("Number of holds on lock by the current thread: " + lock.getHoldCount()); // Number of times the same lock is acquired by the same thread
                    } catch (InterruptedException interruptedException) {
                        System.out.println("Thread: " + Thread.currentThread() + " gets interrupted");
                    }
                } finally {
                    lock.unlock();
                    return;
                }
            } else {
                System.out.println("Lock is not available to acquire at the moment for thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        UnStructuredLock runnable = new UnStructuredLock();
        Thread thread1 = new Thread(runnable, "Thread 1");
        Thread thread2 = new Thread(runnable, " Thread 2");
        System.out.println("Starting both threads");
        thread1.start();
        thread2.start();

        Runnable runnableObj = new Runnable() {
            @Override
            public void run() {
                ReentrantLock lock = new ReentrantLock();
                Condition condition = lock.newCondition(); // condition object is bounded with the lock object
                try {
                    condition.await(); // same as monitor object wait()
                    condition.signal(); // same as monitor object notify()
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
    }
}
