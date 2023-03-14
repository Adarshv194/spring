package io.adarsh.springdatajpaexp.code.threadCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class PrimeNumberWithThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Executors class acts as a factory class, and it creates an object of ThreadPoolExecutor (which implements ExecutorService) and manages threads as threads[] (arrays)
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        ScheduledThreadPoolExecutor scheduledExecutorService = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        Semaphore semaphore = new Semaphore(3, true);

        Thread.currentThread().setName("Main Thread");
        List<Thread> threadList = new ArrayList<>();
        threadList.add(Thread.currentThread());

        Runnable reportRunnable = new Runnable() {
            public void run() {
                System.out.println("Generating report...");
                threadList.forEach(thread -> System.out.println("State of thread: " + thread.getName() + " is: " + thread.getState()));
                /*System.out.println("Active threads working: " + executorService.getActiveCount());
                System.out.println("Total result calculated: " + executorService.getCompletedTaskCount());*/
            }
        };

        // first it will execute the runnable with initalDelay and after that it gonna execute the runnable after period
        scheduledExecutorService.scheduleAtFixedRate(reportRunnable, 2, 9, TimeUnit.SECONDS);

        FutureTask<Integer> task = null;
        while (true) {
            /*System.out.println("Active Thread count: " + Thread.activeCount() + " using Thread class");
            System.out.println("Active Thread count: " + executorService.getActiveCount() + " using Executor service");*/
            System.out.println("Enter the nth number to find the prime");
            Scanner scObj = new Scanner(System.in);
            int input = scObj.nextInt();
            if (input == 0)
                break;

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        // when all the passes of the semaphore has been acquired by other threads
                        // then the coming threads will go into WAITING state
                        // providing the permit in the semaphore while acquiring will update the passes and
                        // at the below code it only allow mentioned passes
                        semaphore.acquire(2);
                        int result = PrimeNumberCalculator.primeNumberCalculator(input);
                        System.out.println(input + "th prime number is: " + result);
                    } catch (InterruptedException interruptedException) {
                        System.out.println("Gets Interrupted while waiting for semaphore");
                    } finally {
                        semaphore.release();
                    }
                }
            };

            Thread thread = new Thread(runnable);
            threadList.add(thread);
            thread.start();

            Callable<Integer> callable = new Callable<Integer>() {
                public Integer call() throws Exception {
                    System.out.println(PrimeNumberCalculator.primeNumberCalculator(input));
                    return PrimeNumberCalculator.primeNumberCalculator(input);
                }
            };
            task = new FutureTask<>(callable);
            Thread t = new Thread(task);
//            t.start();
//            executorService.execute(runnable);
//            Future<?> submit = executorService.submit(runnable);
//            Future<Integer> integerFuture = executorService.submit(callable);
//            System.out.println("Future integer reference has been received");
//            System.out.println("Let's try to get the value from the future");
//            System.out.println(input + "th prime number is: " + integerFuture.get());
        }

//        System.out.println("Result from the future object: " + task.get());
//        executorService.shutdown();
//        scheduledExecutorService.shutdown();

        // If we don't provide the executorService then it gonna uses the common shared ForkJoinThreadPool by JVM.
//        CompletableFuture.supplyAsync(() -> "result", executorService)
//                .thenAccept((String result) -> System.out.println(result));
        // Although thenAccept is also executing in the same thread which is executing the supplier lambda concurrently
        // but this will be much useful when we want to call external API concurrently where we don't have control over
        // modifying code and after getting the result we want to perform our logic
        // using this we are getting the result and performing our logic as well as not waiting the main thread
        // when result is not ready.
    }
}
