package com.learning.structuredconcurrency;

import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.TimeoutException;


public class SelfLearningStructuredConcurrency {

    public static void main(String[] args) throws Exception{
        //interruptMain();
        //blockForAll();
        //blockForFailure();
        blockForSuccess();
    }

    private static void interruptMain() {
        Thread mainThread = Thread.currentThread();
        Thread.ofPlatform().start(() ->{
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {

            }
            mainThread.interrupt();
        });

    }

    private static void blockForAll() throws InterruptedException, TimeoutException {
        System.out.println("Main Started");
        try(StructuredTaskScope<TaskResponse> scope = new StructuredTaskScope<>()){

            LongRunningTask apiTask = new LongRunningTask("api-call", false, 5000, "api-response");
            LongRunningTask grpcTask = new LongRunningTask("grpc-call", false, 8000, "grpc-response");
            LongRunningTask graphqlTask = new LongRunningTask("graphql-call", true, 7000, "graphql-response");

            StructuredTaskScope.Subtask<TaskResponse> forkAPI = scope.fork(apiTask);
            StructuredTaskScope.Subtask<TaskResponse> forkGRPC = scope.fork(grpcTask);
            StructuredTaskScope.Subtask<TaskResponse> forkGraphql = scope.fork(graphqlTask);

            //infinite block untill all tasks complete: maybe succes or failure
            scope.join();

            //scope.joinUntil(Instant.now().plusSeconds(4));

            StructuredConcurrencyUtil.printResult(forkAPI);
            StructuredConcurrencyUtil.printResult(forkGRPC);
            StructuredConcurrencyUtil.printResult(forkGraphql);
        }
        System.out.println("Main Ended");
    }

    private static void blockForFailure() throws InterruptedException, TimeoutException, ExecutionException {
        System.out.println("Main Started");
        try(var scope = new StructuredTaskScope.ShutdownOnFailure()){

            LongRunningTask apiTask = new LongRunningTask("api-call", true, 5000, "api-response");
            LongRunningTask grpcTask = new LongRunningTask("grpc-call", false, 8000, "grpc-response");
            LongRunningTask graphqlTask = new LongRunningTask("graphql-call", false, 7000, "graphql-response");

            StructuredTaskScope.Subtask<TaskResponse> forkAPI = scope.fork(apiTask);
            StructuredTaskScope.Subtask<TaskResponse> forkGRPC = scope.fork(grpcTask);
            StructuredTaskScope.Subtask<TaskResponse> forkGraphql = scope.fork(graphqlTask);

            //infinite block untill all tasks complete: maybe succes or failure
            scope.join();
            scope.throwIfFailed();

            //scope.joinUntil(Instant.now().plusSeconds(4));

            System.out.println("api response: "+forkAPI.get());
            System.out.println("grpc response: "+forkGRPC.get());
            System.out.println("graphql response: "+forkGraphql.get());
        }
        System.out.println("Main Ended");
    }

    private static void blockForSuccess() throws InterruptedException, ExecutionException {
        System.out.println("Main Started");
        try(var scope = new StructuredTaskScope.ShutdownOnSuccess<TaskResponse>()){

            LongRunningTask apiTask = new LongRunningTask("api-call", false, 5000, "api-response");
            LongRunningTask grpcTask = new LongRunningTask("grpc-call", false, 8000, "grpc-response");
            LongRunningTask graphqlTask = new LongRunningTask("graphql-call", false, 7000, "graphql-response");

            StructuredTaskScope.Subtask<TaskResponse> forkAPI = scope.fork(apiTask);
            StructuredTaskScope.Subtask<TaskResponse> forkGRPC = scope.fork(grpcTask);
            StructuredTaskScope.Subtask<TaskResponse> forkGraphql = scope.fork(graphqlTask);

            //infinite block untill all tasks complete: maybe succes or failure
            scope.join();

            TaskResponse result = scope.result();

            //scope.joinUntil(Instant.now().plusSeconds(4));

            System.out.println("first response: "+result);
        }
        System.out.println("Main Ended");
    }
}
