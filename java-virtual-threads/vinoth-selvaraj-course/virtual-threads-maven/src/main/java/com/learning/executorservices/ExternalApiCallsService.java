package com.learning.executorservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExternalApiCallsService {

    public static final Logger logger = LoggerFactory.getLogger(ExternalApiCallsService.class);
    private static final String PRODUCT_URL_FORMAT = "http://localhost:7070/sec01/product/";

    private static final String RATING_URL_FORMAT = "http://localhost:7070/sec01/rating/";

    public static String findProduct(String productId){
        logger.info("findProduct: finding product with id {}",productId);
        String url = PRODUCT_URL_FORMAT+ productId;

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public static Integer findRating(String productId){
        logger.info("findRating: finding rating with id {}",productId);
        String url = RATING_URL_FORMAT + productId;

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return Integer.valueOf(response.body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
        System.out.println(findProduct("1"));
        System.out.println(findRating("1"));
    }
}
