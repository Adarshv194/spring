package io.adarsh.springdatajpaexp.thread;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerMain {

  public static void main(String[] args) {
    List<Thread> threadList = new ArrayList<>();
    Thread monitorThread = new Thread(() -> {
      while (true) {
        if (Thread.currentThread().isInterrupted()) {
          System.out.println("Interruption......");
          break;
        }
        threadList.forEach(thread -> System.out.print(thread.getName() + ": is in - " + thread.getState() + ", "));
        System.out.println("");
        try {
          Thread.sleep(0);
        } catch (InterruptedException interruptedException) {
          System.out.println("Monitor thread gets interrupted: handling in catch");
          break;
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
    Consumer consumer = new Consumer(buffer, lock, producer);
    consumer.setName("Consumer Thread");
    producer.setName("Producer Thread");
    threadList.add(consumer);
    threadList.add(producer);
//    monitorThread.start();
    consumer.start();
    producer.start();
  }

}
