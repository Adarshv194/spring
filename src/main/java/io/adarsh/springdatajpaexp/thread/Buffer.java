package io.adarsh.springdatajpaexp.thread;

public class Buffer {

  private int value;
  private boolean isSet = true;

  public /*synchronized*/ int getValue() throws InterruptedException {
    return value;
//    System.out.println("Consumer consumes: " + value);
//    notifyAll();
//    wait();
  }

  public /*synchronized*/ void setValue(int value) throws InterruptedException {
//    System.out.println("Producer produces: " + value);
    this.value = value;
//    notifyAll();
//    wait();
  }

  public boolean isSet() {
    return isSet;
  }

  public void setSet(boolean set) {
    isSet = set;
  }
}
