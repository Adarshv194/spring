package io.adarsh.springdatajpaexp.thread;

public class ProducerConsumerMain {

  public static void main(String[] args) {
    System.out.println("Execution started in: " + Thread.currentThread().getName());
    System.out.println("Creating common resource buffer");
    Buffer buffer = new Buffer();
    System.out.println("Creating lock object for synchronization and providing the reference to producer and consumer");
    Object lock = new Object();
    System.out.println("Creating Consumer and Producer threads and giving the reference of the common resource buffer");
    Consumer consumer = new Consumer(buffer, lock);
    Producer producer = new Producer(buffer, lock);
    consumer.start();
    producer.start();
  }

}
