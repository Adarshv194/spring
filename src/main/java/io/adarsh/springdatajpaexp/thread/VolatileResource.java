package io.adarsh.springdatajpaexp.thread;

public class VolatileResource {

  private int value;

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
