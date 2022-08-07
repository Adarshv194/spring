package io.adarsh.springdatajpaexp.thread.JB;

import io.adarsh.springdatajpaexp.thread.Buffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VolatileMain {
    public static void main(String[] args) throws InterruptedException {

        List<Thread> threadList = new ArrayList<>();
        Thread monitorThread = new Thread(() -> {
            while (true) {
                threadList.forEach(thread -> System.out.print(thread.getName() + " - " + thread.getState() + " ,"));
                System.out.println("");
            }
        });

        var obj = new Object() {
           volatile int value = -1;
        };

        /*Buffer buffer = new Buffer();*/
        List<Buffer> bufferList = Arrays.asList(
                new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(),
                new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(),
                new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(),
                new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(),
                new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(),
                new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(),
                new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(),
                new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(),
                new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(),
                new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(),
                new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(),
                new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer(), new Buffer()
        );

//        System.out.println(bufferList.size());

        List<List<Buffer>> listInBatches = getListInBatches(bufferList);
        System.out.println(listInBatches.size());
        Thread thread1 = new Thread(() -> {
          listInBatches.get(0)
                  .parallelStream()
                  .forEach(buffer -> buffer.setVolatileValue(++obj.value));
            System.out.println("Thread 1 completed");
        });
        Thread thread2 = new Thread(() -> {
          listInBatches.get(1)
                  .parallelStream()
                  .forEach(buffer -> buffer.setVolatileValue(++obj.value));
            System.out.println("Thread 2 completed");
        });
        Thread thread3 = new Thread(() -> {
          listInBatches.get(2)
                  .parallelStream()
                  .forEach(buffer -> buffer.setVolatileValue(++obj.value));
            System.out.println("Thread 3 completed");
        });
        Thread thread4 = new Thread(() -> {
          listInBatches.get(4)
                  .parallelStream()
                  .forEach(buffer -> buffer.setVolatileValue(++obj.value));
            System.out.println("Thread 4 completed");
        });
        Thread thread5 = new Thread(() -> {
          listInBatches.get(5)
                  .parallelStream()
                  .forEach(buffer -> buffer.setVolatileValue(++obj.value));
            System.out.println("Thread 5 completed");
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

//        bufferList.parallelStream()
//                .forEach(buffer -> {
//                    buffer.setVolatileValue(++obj.value);
//                });

        Thread.sleep(19000);
        bufferList.sort((val1, val2) -> val1.getVolatileValue() - val2.getVolatileValue());
        bufferList.forEach(buffer -> System.out.println(buffer.getVolatileValue()));
        System.out.println();
//        Thread thread1 = new Thread(() -> {
//            synchronized (buffer) {
////                System.out.println("Value before updation in Thread 1: " + buffer.getVolatileValue());
//                buffer.setVolatileValue(25);
////                System.out.println("Value after updation in Thread 1: " + buffer.getVolatileValue());
////                System.out.println("Thread 1 execution completed");
//            }
//        });
//
//        Thread thread2 = new Thread(() -> {
//            while (true) {
//                System.out.println("Value in Thread2: " + buffer.getVolatileValue());
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
////        thread2.setDaemon(true);
//        thread2.setPriority(10);
//        thread2.start();
//        thread1.start();
    }

    private static List<List<Buffer>> getListInBatches(List<Buffer> bufferList) {
        int size = bufferList.size();
        int batch = size/5;
        int startIndex = 0;
        int endIndex = batch;
        List<List<Buffer>> batchList = new ArrayList<>();
        while(startIndex != size) {
            batchList.add(bufferList.subList(startIndex, endIndex));
            startIndex = endIndex;
            endIndex += batch;
            if (endIndex > size)
                endIndex = size;
        }
        return batchList;
    }
}