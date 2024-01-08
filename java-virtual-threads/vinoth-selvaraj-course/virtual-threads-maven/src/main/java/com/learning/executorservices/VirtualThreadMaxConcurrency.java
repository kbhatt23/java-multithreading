package com.learning.executorservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;

//to demonstrate how to limit concurrency level in case of virtual threads
//for platform threads we can use fixedthreadpool to limit max concurrency
//but that pools the thread, same can not be used for virtual thread as it will pool it
//hence in case virtual thread use semaphore for max concurrency limit + use onethreadpertask executor for virtual thread
public class VirtualThreadMaxConcurrency {
    private static final Logger logger = LoggerFactory.getLogger(VirtualThreadMaxConcurrency.class);

    private static final int MAX_CONCURRENCY = 3;

    public static void main(String[] args) throws InterruptedException {
        //no max concurrency limit for cached pool, concurrency == num of tasks
        //ExecutorService executorService = Executors.newCachedThreadPool();

        //we can set concurrency limit in fixedthreadpool but it will be platform thread and will be pooled in memory
        //ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONCURRENCY);

        //create one virtual thread for each task, no pooling thats best practise
        //ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();


        //creates virtual thread with limit but it causes thread pooling
        //pooling virtual thread is bad
//        ThreadFactory threadFactory = Thread.ofVirtual().name("virtual-", 1).factory();
//        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONCURRENCY, threadFactory);
//        concurrencyLimit(executorService,20);

        ThreadFactory threadFactory = Thread.ofVirtual().name("virtual-", 1).factory();
        ExecutorService executorService = Executors.newThreadPerTaskExecutor(threadFactory);

        concurrencyLimitVirtualThread(executorService , 20 , new Semaphore(MAX_CONCURRENCY) );


        executorService.shutdown();
        logger.info("all tasks submitted");

        Thread.sleep(Duration.ofSeconds(10));
    }

    private static void concurrencyLimit(ExecutorService executorService, int totalOperations){
        for(int i = 1; i <= totalOperations ; i++){
            String productId = String.valueOf(i);
            executorService.submit(() -> {
                String product = ExternalApiCallsService.findProduct(String.valueOf(productId));
                logger.info(product);
            });
        }
    }

    private static void concurrencyLimitVirtualThread(ExecutorService executorService, int totalOperations, Semaphore semaphore){
        for(int i = 1; i <= totalOperations ; i++){
            String productId = String.valueOf(i);
            executorService.submit(() -> {
                try{
                    semaphore.acquire();
                    String product = ExternalApiCallsService.findProduct(String.valueOf(productId));
                    logger.info(product);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            });
        }
    }
}
