package io.adarsh.springdatajpaexp.thread;

import lombok.SneakyThrows;

public class Consumer extends Thread {

  private Thread producerThread;
  private Buffer buffer;
  private Object lock;
  
  public Consumer(Buffer buffer, Object lock, Thread producerThread) {
    this.buffer = buffer;
    this.lock = lock;
    this.producerThread = producerThread;
  }

  @SneakyThrows
  @Override
  public void run() {
    synchronized (lock) {
      System.out.println("Control is in Consumer");
      for (int i=1; i<=5; i++) {
        if (!buffer.isSet() || buffer.getValue() == 0) {
          System.out.println("Control is in consumer thread and value has not been produced as of now, releasing lock");
          System.out.println("Producer thread state before releasing lock from consumer: " + producerThread.getState());
          // wait will release the lock
          lock.wait();
        }
        int consumedValue = buffer.getValue();
        System.out.println("Consumer consumes: " + consumedValue);
        buffer.setSet(false);
        // whenever we are gonna call notify() or wait() we have to call on the monitor lock object
        // on which we are taking the lock because it's the lock monitor threads are using for executing the code synchronized
        // for inter - communication
        System.out.println("After consuming , producer thread state before notifying it: " + producerThread.getState());
        lock.notifyAll();
//        System.out.println("After notifying the producer thread as it goes to the BLOCKED STATE, now interrupting it in BLOCKED STATE");
//        producerThread.interrupt();
//        break;
        System.out.println("After consuming , producer thread state after notifying it: " + producerThread.getState());
        if (i == 5)
          System.out.println("Consumer has consumed all the values, now no need to go in WAITING STATE, should go into TERMINATED STATE ");
        else
          lock.wait();
      }
      System.out.println("Consumer thread completed its execution going to TERMINATED STATE from: " + Thread.currentThread().getState() + " STATE");
    }
  }
}
