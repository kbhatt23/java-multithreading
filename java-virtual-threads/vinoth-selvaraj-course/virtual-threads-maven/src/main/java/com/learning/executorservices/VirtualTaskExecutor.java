package com.learning.executorservices;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class VirtualTaskExecutor {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int count= 50;
        List<ProductRatingData> productRatingData = handleProduct(count);
        System.out.println("final data: "+productRatingData);
    }

    private static List<ProductRatingData> handleProduct(int count) throws ExecutionException, InterruptedException {

        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()){
            return   IntStream.rangeClosed(1,count)
                    .parallel()
                    .mapToObj(i -> {
                        String productID = String.valueOf(i);
                        Future<String> productFuture = executorService.submit(() -> ExternalApiCallsService.findProduct(productID));

                        Future<Integer> ratingFuture = executorService.submit(() -> ExternalApiCallsService.findRating(productID));

                        try {
                            return new ProductRatingData(productFuture.get(), ratingFuture.get());
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    }).toList();

        }
    }
}
