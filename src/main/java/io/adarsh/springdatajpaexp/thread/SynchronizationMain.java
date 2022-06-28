package io.adarsh.springdatajpaexp.thread;

import java.lang.Thread.State;

public class SynchronizationMain {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println("Execution started in: " + thread.getName() + " thread");
        System.out.println("Creating printer");
        Printer printer = new Printer();
        System.out.println("Creating two user threads and giving them the reference of printer");
        StarPrinter starPrinter = new StarPrinter(printer);
        starPrinter.setName("Star Printer");
        HashPrinter hashPrinter = new HashPrinter(printer);
        hashPrinter.setName("Hash Printer");
        System.out.println("Creating thread for checking instance method call by taking it's lock");
        Thread instanceThread = new Thread(() -> {
            printer.call();
        });
        instanceThread.setName("Instance thread");
        System.out.println("Launching threads for user");
        starPrinter.start();
        hashPrinter.start();
        instanceThread.start();
        State state = instanceThread.getState();
    }
}
