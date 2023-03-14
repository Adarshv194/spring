package io.adarsh.springdatajpaexp.code.threadCode;

import org.elasticsearch.monitor.jvm.JvmStats;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedKeyword {
    public static void main(String[] args) {
        List<Thread> monitorList = new ArrayList<>();
        Thread monitorThread = new Thread(() -> {
            while (true) {
                monitorList.forEach(thread -> System.out.println("Thread: " + thread.getName() + " state is: " + thread.getState()));
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    System.out.println("Thread: " + Thread.currentThread() + " get interrupted and now the current state of the interruption is: " + Thread.currentThread().isInterrupted());
                    return;
                }
            }
        });
        Object lock = new Object();

        Thread consumer = new Thread(() -> {
            // when thread reaches to a point when it needs to acquire a lock, if the lock is not available then it will go into BLOCKED state.
            try {
                synchronized (lock) {
                    System.out.println("Lock is with consumer thread");
//                    monitorThread.interrupt();
                    Thread.sleep(0);
//                try {
//                    lock.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                    System.out.println("Notifying producer thread");
//                    lock. notify();
                    while (true) {}
                }
            } catch (InterruptedException interruptedException) {
                System.out.println("Interrupted with state: " + Thread.currentThread().getState());
            }
        });

        Thread producer = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Lock is with producer thread");
                try {
                    lock.wait(900);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Consumer thread state before notifying: " + consumer.getState());
                lock.notify();
                for (int i=0; i<1000; i++) {
                    System.out.println();
                }
                System.out.println("Interrupting consumer thread when it's in: " + consumer.getState());
                // when the consumer thread is in blocked state and if we interrupt it will stay in the blocked state
                consumer.interrupt();
                System.out.println("Producer thread going to sleep");
                try {
                    Thread.sleep(1000); // sleep method will not release the lock, only wait method will release it.
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        });

        monitorThread.interrupt();
        monitorList.add(producer);
        monitorList.add(consumer);

        monitorThread.start();
        producer.setName("Producer Thread");
        consumer.setName("Consumer Thread");
        producer.start();
        consumer.start();
    }
}
