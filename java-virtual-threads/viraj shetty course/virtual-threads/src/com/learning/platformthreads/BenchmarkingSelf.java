package com.learning.platformthreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class BenchmarkingSelf {
    private static final int TASKS_COUNT =1000;
    private static final int TASKS_TIME_MS =1000;
    public static void main(String[] args) {

        //usingExecutorService();
        usingForkJoinPool();
        //usingVirtualThread();
    }

    private static void usingExecutorService() {
        long start = System.currentTimeMillis();
        try(ExecutorService executorService = Executors.newFixedThreadPool(16)){
            for(int i = 0 ; i < TASKS_COUNT ; i++){
                executorService.submit(new SelfTask(i));
            }
        }

        System.out.println("usingExecutorService: total time taken: "+ (System.currentTimeMillis() - start));
    }

    private static void usingForkJoinPool() {
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(1000);

            for(int i = 0 ; i < TASKS_COUNT ; i++){
                pool.submit(new SelfTask(i));
            }

            pool.shutdown();
        try {
            pool.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("usingForkJoinPool: total time taken: "+ (System.currentTimeMillis() - start));
    }

    private static void usingVirtualThread() {
        long start = System.currentTimeMillis();
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()){
            for(int i = 0 ; i < TASKS_COUNT ; i++){
                executorService.submit(new SelfTask(i));
            }
        }

        System.out.println("usingVirtualThread: total time taken: "+ (System.currentTimeMillis() - start));
    }

    static class SelfTask implements Runnable{

        private int id;
        SelfTask(int id){
            this.id=id;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(TASKS_TIME_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(Thread.currentThread()+ ": task completed for id: "+id);
        }
    }
}

