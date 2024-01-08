package com.learning.basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ThreadFactory;

public class CooperativeSchedulingDemo {
    private static final Logger logger = LoggerFactory.getLogger(CooperativeSchedulingDemo.class);
    static{
        //ensuring only one processor core works for virtual thread
        //hence at max only one virtual thread can be executed at once: no context switching
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");

        logger.info("initial jdk virtual thread parallelism: {}",System.getProperty("jdk.virtualThreadScheduler.parallelism"));
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadFactory threadFactory = Thread.ofVirtual().name("worker", 1).factory();
        demo(threadFactory);
        demo(threadFactory);

        Thread.sleep(Duration.ofSeconds(2));
    }

    private static void demo(ThreadFactory threadFactory){

        threadFactory.newThread(CooperativeSchedulingDemo::task).start();
    }

    private static void task(){
        logger.info("Thread-{} tasks started",Thread.currentThread().getName());
        for(int i = 0 ; i < 10 ; i++){
            logger.info("Thread-{} printing task:{}",Thread.currentThread().getName(),i);
            if(i == 5)
                Thread.yield();
        }

        logger.info("Thread-{} tasks completed",Thread.currentThread().getName());
    }

}
