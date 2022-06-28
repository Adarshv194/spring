package io.adarsh.springdatajpaexp.thread.JB;

import java.util.concurrent.locks.Lock;

public class SynchronizationCounterMain {

  public static void main(String[] args) {
    Object lock = new Object();
    Counter counter = new Counter(lock);
    new Thread(counter, "Thread One").start();
    new Thread(counter, "Thread Two").start();
    new Thread(counter, "Thread Three").start();
    new Thread(counter, "Thread Four").start();
  }

}
