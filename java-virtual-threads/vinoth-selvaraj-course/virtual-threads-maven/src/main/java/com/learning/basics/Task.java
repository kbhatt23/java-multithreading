package com.learning.basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {

    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    public static void ioIntensiveTask(int operation){
        logger.info("starting task for operation:{}",operation);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("completed task for operation:{}",operation);
    }
}
