package com.learning.executorservices.scopedvalues;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;

//mimick Spring boot like framework
//have fixed thread pool each having thread local
//each thread data will be safe local to itself and without passing param we can use it
//no need to pass the object in method param
public class ThreadLocalBasedFiltersFramework {

    private static  final  int MAX_CONCURRENCY = 2;

    //double users are accessing than max threads in server framework
    private static final int MAX_USERS = 4;

    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalBasedFiltersFramework.class);

    private static final ThreadLocal<String> USER_SESSION = new ThreadLocal<>();
    public static void main(String[] args) {

        logger.info("starting server");

        ThreadFactory threadFactory = Thread.ofPlatform().name("framework-", 1).factory();
        try(ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONCURRENCY,threadFactory)){

            for(int i = 0 ; i < MAX_USERS ; i++){
                int userID = i;
                executorService.submit(() -> processRequest(userID));
            }
        };

        logger.info("shutting down server");

    }

    private static void processRequest(int userID){
        logger.info("processRequest started for user {}",userID);

        authenticate(userID);
        handleController(userID);

        logger.info("processRequest ended for user {}",userID);
    }

    private static void authenticate(int userID) {
        USER_SESSION.set(UUID.randomUUID().toString() + ":" + userID);

        logger.info("authenticate success for {}",userID);
    }
    private static void handleController(int userID) {
        logger.info("handleController for session: {}", USER_SESSION.get());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
