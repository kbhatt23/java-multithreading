package com.learning.structuredconcurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LongRunningTaskRunner {
    public static void main(String[] args) throws Exception{
        System.out.println("main started");


        try(ExecutorService executorService = Executors.newFixedThreadPool(2)){
            LongRunningTask longRunningTask = new LongRunningTask("demo-concurrency", true, 8000, "jeet gaye");
            Future<TaskResponse> futureTaskResponse = executorService.submit(longRunningTask);



           // Thread.sleep(2000);
           // futureTaskResponse.cancel(true);
        }




        System.out.println("main ended");
    }
}
