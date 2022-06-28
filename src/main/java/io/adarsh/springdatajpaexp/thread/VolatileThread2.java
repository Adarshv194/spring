package io.adarsh.springdatajpaexp.thread;

public class VolatileThread2 extends Thread{

  private VolatileResource volatileResource;

  public VolatileThread2(VolatileResource volatileResource) {
    this.volatileResource = volatileResource;
  }

  @Override
  public void run() {
  }
}
