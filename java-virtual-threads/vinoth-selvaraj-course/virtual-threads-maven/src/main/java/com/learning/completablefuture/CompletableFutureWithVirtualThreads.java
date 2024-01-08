package com.learning.completablefuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CompletableFutureWithVirtualThreads {
    private static final Logger logger = LoggerFactory.getLogger(CompletableFutureWithVirtualThreads.class);
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        ThreadFactory threadFactory = Thread.ofVirtual().name("cf-virtual-", 1).factory();


        CompletableFuture.supplyAsync( CompletableFutureWithVirtualThreads :: bigTask , Executors.newThreadPerTaskExecutor(threadFactory))
                .thenAccept(str -> {logger.info("main : received: "+str);
                    latch.countDown();
                });

        logger.info("main, task submitted");
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }

        logger.info("main ended");
    }

    private static String bigTask(){
        logger.info("bigTask started by: "+Thread.currentThread());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        logger.info("bigTask ended by: "+Thread.currentThread());
        return "jai shree ram";
    }
}
