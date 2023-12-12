package com.learning.platformthreads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class VirtualThreadCreationUsingExecutors {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main started");

        int attempts = 10;
        //approach5(attempts);
        approach6(attempts);

        System.out.println("main ended");
    }

    //no need of countdown latch as try with resources blocks main untill all virtual threads have completed the task
    private static void approach5(int attempts) {
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            //try with resources automatically calls shutdown method and await terminati0n
            //hence main thread will wait for termination hence no use of countdownlatch

            for (int i = 0; i < attempts; i++)
                executorService.submit(() -> callAPI());

            System.out.println("main submitted task");
        }
    }

    //create virtual thread with name
    private static void approach6(int attempts) {
        ThreadFactory threadFactory = Thread.ofVirtual().name("custom-executor-", 1).factory();
        try(ExecutorService executorService = Executors.newThreadPerTaskExecutor(threadFactory)) {
            //try with resources automatically calls shutdown method and await terminati0n
            //hence main thread will wait for termination hence no use of countdownlatch

            for (int i = 0; i < attempts; i++)
                executorService.submit(() -> callAPI());

            System.out.println("main submitted task");
        }
    }

    private static void callAPI() {
        System.out.println("callAPI started by: "+Thread.currentThread());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("callAPI ended by: "+Thread.currentThread());

    }
}
