package io.adarsh.springdatajpaexp.thread;

public class HashPrinter extends Thread {

    Printer printer;

    public HashPrinter() {}

    public HashPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        synchronized (Printer.class) {
            Thread thread = Thread.currentThread();
            System.out.println("Execution started in " + thread.getName() + " thread");
            System.out.println("Hash printer started, getting a line of hash printed");
      // if we use the synchronized keyword at method level in resource class then the above code will run asynchronously
            Printer.print('#');
        }
    }
}
