package io.adarsh.springdatajpaexp.thread;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerMain {

  public static void main(String[] args) throws InterruptedException {
    List<Thread> threadList = new ArrayList<>();
    Thread monitorThread = new Thread(() -> {
      while (true) {
        if (Thread.currentThread().isInterrupted()) {
          System.out.println("Interruption......");
//          break;
        } else {
          threadList.forEach(thread -> System.out.print(thread.getName() + ": is in - " + thread.getState() + ", "));
          System.out.println("");
          try {
            Thread.sleep(100);
          } catch (InterruptedException interruptedException) {
            System.out.println("Monitor thread gets interrupted: handling in catch");
            interruptedException.printStackTrace();
            System.out.println("Monitor thread is in: " + Thread.currentThread().getState() + " STATE and its interruption status in catch block gets changed to: " + Thread.currentThread().isInterrupted());
            System.out.println("Interrupting it again ");
            Thread.currentThread().interrupt();
//          break;
        }
        }
      }
    });
    System.out.println("Execution started in: " + Thread.currentThread().getName());
    System.out.println("Creating common resource buffer");
    Buffer buffer = new Buffer();
    System.out.println("Creating lock object for synchronization and providing the reference to producer and consumer");
    Object lock = new Object();
    System.out.println("Creating Consumer and Producer threads and giving the reference of the common resource buffer");
    Producer producer = new Producer(buffer, lock);
    Consumer consumer = new Consumer(buffer, lock, producer, monitorThread);
    consumer.setName("Consumer Thread");
    producer.setName("Producer Thread");
    threadList.add(consumer);
    threadList.add(producer);
    monitorThread.setDaemon(true);
//    monitorThread.setPriority(10);
    monitorThread.start();
    consumer.start();
    producer.start();
    Thread thread = new Thread(() -> {
      System.out.println("Execution in temp thread, setting sleep(0) on the current thread");
      try {
        Thread.sleep(0);
        System.out.println("Execution completed after sleep(0)");
//        monitorThread.interrupt();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    thread.setName("Temp thread");
    threadList.add(thread);
//    monitorThread.start();
//    Thread.sleep(1000);
//    thread.start();

  }
}
