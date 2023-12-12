package com.learning.structuredconcurrency;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BasicStructuredConcurrencyDemo {
    public static void main(String[] args) throws InterruptedException, TimeoutException {
        System.out.println("Main Started");

        //if main thread is interrupted children threads are interrupted automatically
        interruptMain(2000);

        //start of the scope
        try(StructuredTaskScope<TaskResponse> scope = new StructuredTaskScope<>()){

            LongRunningTask longRunningTask1 = new LongRunningTask("task-1", false, 3000, "task1 response");
            LongRunningTask longRunningTask2 = new LongRunningTask("task-2", false, 10000, "task2 response");

            //forks
            //by default virtual threads will be used for each fork
            StructuredTaskScope.Subtask<TaskResponse> fork1 = scope.fork(longRunningTask1);
            StructuredTaskScope.Subtask<TaskResponse> fork2 = scope.fork(longRunningTask2);

           // Thread.sleep(1000);

            //if main thread is interrupted children thread will get terminated automatically
            //Thread.currentThread().interrupt();

            //join for all the threads to be completed(success or failure does not matter)
            scope.join();

            //main will be blocked at max unmtil timeout or if both tasks are done
            //if timeout happens worker thread will be terminated automatically
            //scope.joinUntil(Instant.now().plus(Duration.ofSeconds(2)));

            //typically will do something meaningful from these
            StructuredConcurrencyUtil.printResult(fork1);

            StructuredConcurrencyUtil.printResult(fork2);
        }
        //end of scope, main will get unblocked only after proper cleanup in all scenarios

        System.out.println("Main Ended");
    }

    private static void interruptMain(long ms) {
        Thread mainThread = Thread.currentThread();
        Thread.ofPlatform().start(() -> {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
            }
            mainThread.interrupt();
        });
    }
}
