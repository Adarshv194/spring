package io.adarsh.springdatajpaexp.thread;

public class Printer {

    public static void print(char ch) {
        // half line is printed
        for (int i=1; i<=30; i++)
            System.out.print(ch);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {}
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
