package com.learning.platformthreads;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class HowManyVirtualThreads {
    public static void main(String[] args) {
        System.out.println("main started");
        
        int threads = 10;
        CountDownLatch latch = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++) {
            Thread.startVirtualThread(() -> {
                handle(latch, UUID.randomUUID().toString());
            });
        }

        System.out.println("main ended");

        try {
            latch.await();
        } catch (InterruptedException e) {

        }

        System.out.println("all task completed");
    }

    private static void handle(CountDownLatch latch, String index) {
        System.out.println("handle started for thread: " + Thread.currentThread()+" with index: "+index);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("handle ended for thread: " + Thread.currentThread()+" with index: "+index);
        latch.countDown();
    }
}