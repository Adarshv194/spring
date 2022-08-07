package io.adarsh.springdatajpaexp.thread;

public class Printer {

    public static void print(char ch) {
        // half line is printed
        for (int i=1; i<=30; i++)
            System.out.print(ch);
        try {
//            System.out.println(Thread.currentThread().getName() + " is going for a sleep of 1000 ms");
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            System.out.println(Thread.currentThread().getName() + " - Thread gets interrupted while in sleep state");
        }
        // remaining half line is printed
        for (int i=1; i<=30; i++)
            System.out.print(ch);
        // new line char is printed
        System.out.println();
    }

    public synchronized void call() {
        Thread thread = Thread.currentThread();
        System.out.println("Execution started in: " + thread.getName() + " thread");
        System.out.println("Call through instance object");
    }


}
