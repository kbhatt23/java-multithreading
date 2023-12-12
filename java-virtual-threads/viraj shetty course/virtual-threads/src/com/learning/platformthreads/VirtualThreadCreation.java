package com.learning.platformthreads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class VirtualThreadCreation {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main started");

        int attempts = 10;
        CountDownLatch latch = new CountDownLatch(attempts);
        //approach1(latch);
        //approach2(latch);
        //approach3(latch,attempts);
        approach4(latch,attempts);

        System.out.println("main submitted task");

        latch.await();

        System.out.println("main ended");
    }

    //no need of countdown latch as try with resources blocks main untill all virtual threads have completed the task
    private static void approach5(int attempts) {
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            //try with resources automatically calls shutdown method and await terminati0n
            //hence main thread will wait for termination hence no use of countdownlatch

            for (int i = 0; i < attempts; i++)
                executorService.submit(() -> callAPI());

        }
    }

    private static void approach4(CountDownLatch latch, int attempts) {
        ThreadFactory threadFactory = Thread.ofVirtual().name("approach4.", 1).factory();

        for(int i =0 ; i< attempts ; i++)
            threadFactory.newThread(() -> task(latch)).start();
    }

    private static void approach3(CountDownLatch latch, int attempts) {
        Thread.Builder.OfVirtual builder = Thread.ofVirtual().name("approach3.", 1);

        for(int i =0 ; i< attempts ; i++)
            builder.start(() -> task(latch));
    }

    private static void approach2(CountDownLatch latch) {
        Thread.Builder.OfVirtual virtualThreadBuilder = Thread.ofVirtual().name("test");

        virtualThreadBuilder.start(() -> task(latch));

    }

    private static void approach1(CountDownLatch latch) {
        Thread.startVirtualThread(() -> VirtualThreadCreation.task(latch));
    }

    private static void task(CountDownLatch countDownLatch){
        System.out.println("Task started by: "+Thread.currentThread());

        callAPI();

        System.out.println("Task completed by: "+Thread.currentThread());
        countDownLatch.countDown();
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
