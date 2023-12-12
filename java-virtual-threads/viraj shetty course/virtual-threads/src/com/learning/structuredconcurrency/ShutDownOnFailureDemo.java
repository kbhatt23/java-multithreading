package com.learning.structuredconcurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

public class ShutDownOnFailureDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Main Started");

        try(StructuredTaskScope.ShutdownOnFailure scope = new StructuredTaskScope.ShutdownOnFailure()){
            LongRunningTask longRunningTask1 = new LongRunningTask("task-1", true, 3000, "task1 response");
            LongRunningTask longRunningTask2 = new LongRunningTask("task-2", false, 10000, "task2 response");

            //forks
            //by default virtual threads will be used for each fork
            StructuredTaskScope.Subtask<TaskResponse> fork1 = scope.fork(longRunningTask1);
            StructuredTaskScope.Subtask<TaskResponse> fork2 = scope.fork(longRunningTask2);

            scope.join();
            scope.throwIfFailed();

            //flow will come here only if all response are succesful
            //hence no need to check the state, just get
            System.out.println("task1 response: "+fork1.get());
            System.out.println("task2 response: "+fork2.get());
        }

        System.out.println("Main Ended");
    }
}
