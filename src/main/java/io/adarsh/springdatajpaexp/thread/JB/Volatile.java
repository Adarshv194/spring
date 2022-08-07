package io.adarsh.springdatajpaexp.thread.JB;

import io.adarsh.springdatajpaexp.thread.Buffer;

public class Volatile {

    // If Thread1 is using the synchronized block and has updated some value in a lock-protected space.
    // If another Thread2 that is not using the synchronized block tries to access the updated value which needs
    // to be get updated by Thread1 in the middle of the synchronized block execution.
    // Then if however scheduler schedules the Thread1 after updating the value and before completion of the synchronized block
    // Then Thread2 can read the updated value of Thread1 without use of volatile keyword because when scheduler schedules a thread then
    // Threads writes the data back to the main memory
    // That's why we have to use lock properly and should manage the access of shared resources only in a mutually exclusive way
    // because there is a high possibility that scheduler will schedule our thread in b/w the execution of the synchronized block
    // because of which data will be written back to the main memory and also OS has to manage multiple processes threads
    // And if locks and access of shared resources is not well managed then our data will get corrupted

    // usecase
    // And if we want to access the updated value (:required use case:) and don't want to rely on scheduling then
    // we can use volatile so that it forces to write data to the main memory on read and access of the volatile variable
    // as it writes the data of all the variables which are used before access of volatile keyword.

    // eg:
//    boolean statusUpdated;
//    volatile String status;
//
//    statusUpdated = true;
//    status = "Granted"
    // As there is a possibility that use of the  updated volatile variable by other threads can be dependent on other variable too
    // like in Thread2
    // if (statusUpdated) {
//            sout(status);
    // }

    public static void main(String[] args) {
        System.out.println("Creating Resources");
        Buffer buffer = new Buffer();
        var obj = new Object() {
          int value;
        };

        Thread thread1 = new Thread(() -> {
            synchronized (buffer) {
                for (int i=0; i<10000; i++) {
                    for (int j=0; j<10000; j++){
                        for (int k=0; k<1000000; k++) {
//                            for (int l=0; l<100; l++);
                        }
                    }
                }
                System.out.println("Updating value");
                obj.value++;
                for (int i=0; i<1000000000; i++) {
                    for (int j=0; j<1000000000; j++) {
                        for (int k=0; k<1000000; k++) {
                            for (int l=0; l<100000; l++){
                                int i1 = i * j * k * l;
                                i++;
                                i1 = i + j * i1;
                                System.out.print("");
                            }
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                System.out.println(obj.value);
                if (obj.value == 1) {
                    System.out.println("Value read");
//                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.setDaemon(true);
        thread2.start();
    }
}
