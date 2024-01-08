package com.learning.cpuintensivedemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class PrimeNumberVirtualThread {


    public static final int PRIME_NUMBER_INDEX = 199999;

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        //int count = 1;
        //int count= Runtime.getRuntime().availableProcessors();
        int count= Runtime.getRuntime().availableProcessors() * 3;
        usingVirtualThread(count);
        usingPlatformThread(count);
    }

    private static void usingVirtualThread(int count) {
        long start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            Thread.ofVirtual().start(() -> {
                cpuIntensiveTask(latch);
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("usingVirtualThread: total time taken: "+(System.currentTimeMillis()-start));
    }

    private static void cpuIntensiveTask(CountDownLatch latch) {
        long startTask = System.currentTimeMillis();
        PrimeNumber.nthPrime(PRIME_NUMBER_INDEX);
        System.out.println("individual task time taken: "+(System.currentTimeMillis()-startTask));
        latch.countDown();
    }

    private static void usingPlatformThread(int count) {
        long start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            Thread.ofPlatform().start(() -> cpuIntensiveTask(latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("usingPlatformThread: total time taken: "+(System.currentTimeMillis()-start));
    }
}
