package com.learning.structuredconcurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

public class ShutDownOnSuccessDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Main Started");

        try(StructuredTaskScope.ShutdownOnSuccess<TaskResponse> scope = new StructuredTaskScope.ShutdownOnSuccess<TaskResponse>()){
            LongRunningTask longRunningTask1 = new LongRunningTask("task-1", true, 3000, "task1 response");
            LongRunningTask longRunningTask2 = new LongRunningTask("task-2", true, 10000, "task2 response");

            //forks
            //by default virtual threads will be used for each fork
            StructuredTaskScope.Subtask<TaskResponse> fork1 = scope.fork(longRunningTask1);
            StructuredTaskScope.Subtask<TaskResponse> fork2 = scope.fork(longRunningTask2);

            scope.join();


            TaskResponse response = scope.result();
            System.out.println("final response: "+response);

        }

        System.out.println("Main Ended");
    }
}
