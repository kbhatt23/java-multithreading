package com.learning.demoloom.controllers.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class DemoApiService {

    private static final Logger logger = LoggerFactory.getLogger(DemoApiService.class);

    private static final String TIME_CONSUMING_API_URL = "https://kbhatt23.free.beeceptor.com/api/time-consuming";

    public String callAPI() throws IOException, InterruptedException {
        logger.info("callAPI: Started for URL: {}, by Thread: {}",TIME_CONSUMING_API_URL,Thread.currentThread());
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

            logger.info("callAPI: ended for URL: {}, by Thread: {}",TIME_CONSUMING_API_URL,Thread.currentThread());
            return response.body();
        }
    }
}
