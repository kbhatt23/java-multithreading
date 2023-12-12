package com.learning.bestpricestore.services;

import com.learning.bestpricestore.controllers.BestPriceController;
import com.learning.bestpricestore.dtos.Book;
import com.learning.bestpricestore.dtos.RestCallStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.StructuredTaskScope;

@Service
public class BookRetrievalService {

    @Value("#{${book.store.urls}}")
    private Map<String,String> storeUrlMap;

    private static final Logger logger = LoggerFactory.getLogger(BookRetrievalService.class);

    private RestClient restClient = RestClient.create();
    public List<Book> findAllDeals(String name) throws InterruptedException {

        try(StructuredTaskScope<Book> scope = new StructuredTaskScope<>()){
            List<StructuredTaskScope.Subtask<Book>> subtasks = new ArrayList<>();
            storeUrlMap.forEach((storeName, storeURL)->{
                subtasks.add(scope.fork(() -> retrieveBookDetails(storeName,storeURL,name)));
            });

            scope.join();

            subtasks.stream()
                    .filter(subtask -> subtask.state() == StructuredTaskScope.Subtask.State.FAILED)
                    .map(StructuredTaskScope.Subtask::exception)
                    .forEach(error ->{
                        logger.error("findAllDeals: error occurred -> "+error);
                    });

            return subtasks.stream()
                    .filter(subtask -> subtask.state() == StructuredTaskScope.Subtask.State.SUCCESS)
                    .map(StructuredTaskScope.Subtask::get)
                    .toList();
        }
    }

    private Book retrieveBookDetails(String storeName, String storeURL, String name) {
        long start = System.currentTimeMillis();

        Book book = restClient.get()
                .uri(storeURL + "/" + name)
                .retrieve()
                .body(Book.class);

        RestCallStatistics restCallStatistics = BestPriceController.REST_CALL_STATISTICS_SCOPED_VALUE.get();
        restCallStatistics.addTime(storeName, System.currentTimeMillis() - start);

        return book;
    }
}
