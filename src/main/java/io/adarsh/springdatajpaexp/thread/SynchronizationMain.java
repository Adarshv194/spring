package io.adarsh.springdatajpaexp.thread;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;

public class SynchronizationMain {

    public static void main(String[] args) {
        // creating a monitor thread
        List<Thread> threadList = new ArrayList<>();
        Thread monitorThread = new Thread(() -> {
            while (true) {
                threadList.forEach(thread -> {
                    System.out.print(thread.getName() + " - " + thread.getState() + ", ");
//                    if (thread.getState().equals(State.TIMED_WAITING)) {
//                        System.out.println("");
//                        System.out.println("Interrupting thread: " + thread.getName());
//                        thread.interrupt();
//                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                System.out.println("");
            }
        });
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
        threadList.add(starPrinter);
        threadList.add(hashPrinter);
        monitorThread.setDaemon(true);
        monitorThread.start();
        starPrinter.start();
        hashPrinter.start();
        instanceThread.start();
        State state = instanceThread.getState();
    }
}
