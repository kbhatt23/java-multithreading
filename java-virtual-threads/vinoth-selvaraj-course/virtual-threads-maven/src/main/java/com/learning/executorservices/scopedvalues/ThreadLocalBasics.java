package com.learning.executorservices.scopedvalues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadLocalBasics {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalBasics.class);

    public static void main(String[] args) throws InterruptedException {
        THREAD_LOCAL.set("main init says jai sita rama");

        Thread t1 = Thread.ofPlatform().name("t1").start(() -> {
            logger.info("t1.init data: {}" , THREAD_LOCAL.get());

            THREAD_LOCAL.set("t1 says jai sita rama");

            logger.info("t1.final data: {}" , THREAD_LOCAL.get());
        });

        t1.join();

        //by main thread
        logger.info("main.init data: {}" , THREAD_LOCAL.get());

        THREAD_LOCAL.set("main says jai sita rama");

        logger.info("main.final data: {}" , THREAD_LOCAL.get());
    }
}
