package com.learning.requesthandlers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.*;


public class UserRequestHandler implements Callable<String> {
    private static final String TIME_CONSUMING_API_URL = "https://kbhatt23.free.beeceptor.com/api/time-consuming";

    private final int userIndex;
    public UserRequestHandler(int userIndex) {
        this.userIndex=userIndex;
    }

    @Override
    public String call() throws Exception {
        long start = System.currentTimeMillis();
        //String result = usingSequential();

        //String result = usingFutures();

        String result = usingCompletableFuture();

        System.out.println("total time taken: "+(System.currentTimeMillis()-start));
        return result;
    }

    private String usingSequential() throws IOException, InterruptedException {
        String apiResult =  callAPI(userIndex);
        String api2Result = callAPI2(userIndex);

        String result =  String.join(System.lineSeparator(), apiResult,api2Result);
        return result;
    }

    private String usingFutures() throws IOException, InterruptedException {

        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()){
        Future<String> api1Future = executorService.submit(() -> callAPI(userIndex));

        Future<String> api2Future = executorService.submit(() -> callAPI2(userIndex));


            String result = null;
            try {
                result = String.join(System.lineSeparator(), api1Future.get(),api2Future.get());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            return result;
        }
    }

    private String usingCompletableFuture() {
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()){
            CompletableFuture<String> api1Future = CompletableFuture.supplyAsync(() -> {
                try {
                    return callAPI(userIndex);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            },executorService);
            CompletableFuture<String> api2Future = CompletableFuture.supplyAsync(() -> {
                try {
                    return callAPI2(userIndex);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            },executorService);

            String result = api1Future.thenCombine(api2Future, (apiResult, api2Result) -> {
                return String.join(System.lineSeparator(), apiResult, api2Result);
            }).join(); //join is fine as it will block only virtual thread and platform thread will be free
        return result;
        }
    }

    private static String callAPI(int userIndex) throws IOException, InterruptedException {
        System.out.println("callAPI for user: "+userIndex +" Started by Thread: "+Thread.currentThread());
        try(HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build()){

            // Create a URI for the endpoint
            URI uri = URI.create(TIME_CONSUMING_API_URL);

            // Create an HttpRequest with the URI and GET method
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            // Send the request and retrieve the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            // Print the response body

            System.out.println("callAPI for user: "+userIndex +" Ended by Thread: "+Thread.currentThread());
            return response.body();
        }
    }
    private static String callAPI2(int userIndex) throws IOException, InterruptedException {
        System.out.println("callAPI2 for user: "+userIndex +" Started by Thread: "+Thread.currentThread());
        try(HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build()){

            // Create a URI for the endpoint
            URI uri = URI.create(TIME_CONSUMING_API_URL);

            // Create an HttpRequest with the URI and GET method
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            // Send the request and retrieve the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            // Print the response body

            System.out.println("callAPI2 for user: "+userIndex +" Ended by Thread: "+Thread.currentThread());
            return response.body();
        }
    }
}
