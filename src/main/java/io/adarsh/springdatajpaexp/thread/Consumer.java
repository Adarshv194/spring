package io.adarsh.springdatajpaexp.thread;

import lombok.SneakyThrows;

public class Consumer extends Thread {

  private Buffer buffer;
  private Object lock;
  
  public Consumer(Buffer buffer, Object lock) {
    this.buffer = buffer;
    this.lock = lock;
  }

  @SneakyThrows
  @Override
  public void run() {
    System.out.println("Control is in Consumer");
    synchronized (lock) {
      for (int i=1; i<=5; i++) {
        if (!buffer.isSet() || buffer.getValue() == 0) {
          // wait will release the lock
          lock.wait();
        }
        int consumedValue = buffer.getValue();
        System.out.println("Consumer consumes: " + consumedValue);
        buffer.setSet(false);
        // whenever we are gonna call notify() or wait() we have to call on the monitor lock object
        // on which we are taking the lock because it's the lock monitor threads are using for executing the code synchronized
        // for inter - communication
        lock.notifyAll();
        lock.wait();
      }
    }
  }
}
