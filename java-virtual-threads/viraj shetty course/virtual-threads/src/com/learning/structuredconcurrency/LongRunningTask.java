package com.learning.structuredconcurrency;

import java.util.concurrent.Callable;

//can fail, succeed or cancelled
//useful for demonstrating structured concurrency
public class LongRunningTask implements Callable<TaskResponse> {

    private final String name;

    private final boolean fail;

    private final long time;

    private final String response;

    public LongRunningTask(String name, boolean fail, long time, String response) {
        this.name = name;
        this.fail = fail;
        this.time = time;
        this.response = response;
    }
    @Override
    public TaskResponse call() throws Exception {
        System.out.println("Task Started by: "+name);

        long start = System.currentTimeMillis();
        int loopCount = 10;
        long loopSleepTime = time/10;
        for(int i=0 ; i < loopCount ; i++){

            if(Thread.currentThread().isInterrupted()){
                handleInterruptedException();
            }

            //combination of IO task + CPU task
            //CPU task
            System.out.println("working on job..."+name);

            //IO task
            try{
                Thread.sleep(loopSleepTime);
            }catch (InterruptedException e){
                handleInterruptedException();
            }
        }
        if(fail){
            System.err.println("Task: "+name+" got failed");
            throw new RuntimeException("Task: "+name+" got failed");
        }
        System.out.println("Task Completed by: "+name);
        return new TaskResponse(name, (System.currentTimeMillis() - start), false,response);

    }

    private void handleInterruptedException() throws InterruptedException {
        System.err.println("Task: "+name+" is interrupted");
        throw new InterruptedException("Task: "+name+" is interrupted");
    }
}
