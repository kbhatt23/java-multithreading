package com.learning.requesthandlers;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RequestHandlerExecutor {

    private static final int MAX_USERS = 10;
    public static final String REQUEST_HANDLER = "request-handler-";

    public static void main(String[] args) {
        ThreadFactory threadFactory = Thread.ofVirtual().name(REQUEST_HANDLER, 1).factory();

        //ThreadFactory threadFactory = Thread.ofPlatform().name(REQUEST_HANDLER, 1).factory();

        List<Future<String>> futures;
        try(ExecutorService executorService = Executors.newThreadPerTaskExecutor(threadFactory)){
            futures = IntStream.rangeClosed(1,MAX_USERS)
                    .mapToObj(userIndex ->
                        executorService.submit(new UserRequestHandler(userIndex))
                    ).collect(Collectors.toList());
        }



        futures.forEach(future -> {
            try {
                System.out.println("response received: "+future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
