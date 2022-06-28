package io.adarsh.springdatajpaexp.thread;

public class StarPrinter extends Thread {

    Printer printer;

    public StarPrinter() {}

    public StarPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        synchronized (Printer.class) {
            Thread thread = Thread.currentThread();
            System.out.println("Execution started in " + thread.getName() + " thread");
            System.out.println("Star printer started, getting a line of star printed");
            // if we use the synchronized keyword at method level in resource class then the above code will run asynchronously
            Printer.print('*');
        }
    }
}
