package io.adarsh.springdatajpaexp.thread.JB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class ThreadPrimeMain {

  public static void main(String[] args) {
    System.out.println("Control is in: " + Thread.currentThread().getName() + " thread");
    List<Thread> threadList = new ArrayList<>();
    threadList.add(Thread.currentThread());
    Thread monitorThreadState = new Thread(() -> {
      while (true) {
        threadList.forEach(thread -> System.out.print(thread.getName() + ": " + thread.getState() + " "));
        System.out.println();
        if (Thread.interrupted()) {
          System.out.println("Monitor thread gets interrupted... closing monitoring");
          break;
        } else {
          System.out.println("Thread is not getting interrupted");
        }
        try {
          Thread.sleep(3000);
        } catch (InterruptedException exception) {
          System.out.println("Monitor thread gets interrupted. Ending monitoring of the threads.");
//          break;
        }
      }
    });
    monitorThreadState.setName("Monitor thread");
    // Daemon thread gets terminated when the last application thread gets terminated. (If running on indefinitely loop)
//    monitorThreadState.setDaemon(true);
    monitorThreadState.start();


    Scanner scObj = new Scanner(System.in);
    while(true) {
      System.out.println("Enter the nth number to find the prime");
      int number = scObj.nextInt();
      if (number == 0) {
        monitorThreadState.interrupt();
        System.out.println("Waiting for all threads to get completed");
        joinThreads(new ArrayList<>(threadList));
        int size = threadList.size() - 1;
        System.out.println("Number of primes calculated: " + size);
       break;
      }
      Thread thread = new Thread(() -> {
        int nthPrime = PrimeCalculator.calculateNthPrime(number);
        System.out.println("The " + number + "th prime number is: " + nthPrime);
      });
      threadList.add(thread);
      thread.start();
    }
  }

  public static void joinThreads(List<Thread> threadList) {
    threadList.remove(Thread.currentThread());
    threadList.forEach(thread -> {
      try {
        System.out.println("Joining " + thread.getName() + " thread running in: " + Thread.currentThread());
        // The main thread will get on WAITING state in this case while only it will join the streamed One thread and do it turn by turn
        thread.join();
      } catch (InterruptedException exception) {
        System.out.println("Got interrupted while waiting for joined threads to gets completed. Quiting now..");
      }
    });
  }

}
