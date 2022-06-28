package io.adarsh.springdatajpaexp.thread;

import lombok.SneakyThrows;

public class Producer extends Thread{

  private Buffer buffer;
  private Object lock;

  public Producer(Buffer buffer, Object lock) {
    this.buffer = buffer;
    this.lock = lock;
  }

  @SneakyThrows
  @Override
  public void run() {
    synchronized (lock) {
      System.out.println("Control is in Producer");
        for (int i=1; i<=5; i++) {
          if (buffer.isSet() && buffer.getValue() != 0) {
            lock.wait();
          }
          System.out.println("Producer produces: "+ i);
          // whenever we are gonna call notify() or wait() we have to call on the monitor lock object
          // on which we are taking the lock because it's the lock monitor threads are using for executing the code synchronized
          // for inter - communication
          buffer.setValue(i);
          buffer.setSet(true);
          lock.notifyAll();
          lock.wait();
        }
    }
  }

}
