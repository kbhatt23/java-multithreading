package com.learning.completablefuture;

import com.learning.utils.TaskUtil;
import com.learning.utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class BasicCompletableFuturePipeline {

    public static void main(String[] args) throws InterruptedException {
        //singleThread();
        ThreadUtils.print("main started");

        CountDownLatch latch = new CountDownLatch(1);

        //BasicCompletableFuturePipeline obj = new BasicCompletableFuturePipeline();
        CompletableFuture.supplyAsync(TaskUtil :: task1)
                .thenApply(TaskUtil:: task2)
                .thenAccept(finalResult -> ThreadUtils.print("finalResult: "+finalResult))
                .thenRun(() -> latch.countDown());

       ThreadUtils.print("main submitted all tasks");

       latch.await();

       ThreadUtils.print("main ended");
    }

    private static void singleThread() {
        long start = System.currentTimeMillis();
        BasicCompletableFuturePipeline obj = new BasicCompletableFuturePipeline();

        String result1 = TaskUtil.task1();

        String result2 = TaskUtil.task2(result1);

        System.out.println("final result: "+result2+" in total time: "+(System.currentTimeMillis() - start));
    }


}
