package io.adarsh.springdatajpaexp.thread.JB;

public class Counter implements Runnable {

  private int value = 0;
  private Object lock;

  public Counter() {}

  public Counter(Object lock) {
    this.lock = lock;
  }

  private void incrementValue() {
    try {
      Thread.sleep(20);
    } catch (InterruptedException interruptedException) {}
    value++;
  }

  private void decrementValue() {
    value--;
  }

  private int getValue() {
    return this.value;
  }


  @Override
  public void run() {
    // this lock object can be any arbitrary object reference but most used is the same (this) object reference.
    synchronized (lock) {
      this.incrementValue();
      System.out.println(Thread.currentThread().getName() + " increments: " + this.getValue());
      this.decrementValue();
      System.out.println(Thread.currentThread().getName() + " decrements: " + this.getValue());
    }
  }
}
