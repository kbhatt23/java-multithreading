package com.learning.bestpricestore.controllers;

import com.learning.bestpricestore.dtos.BestPriceResponse;
import com.learning.bestpricestore.dtos.Book;
import com.learning.bestpricestore.dtos.RestCallStatistics;
import com.learning.bestpricestore.services.BookRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/best-price-store")
public class BestPriceController {

    @Autowired
    private BookRetrievalService bookRetrievalService;

    //same object is created but have different copies in internal map for each thread
    public static final ScopedValue<RestCallStatistics> REST_CALL_STATISTICS_SCOPED_VALUE= ScopedValue.newInstance();

    @GetMapping("{name}")
    public BestPriceResponse findBestPriceBook(@PathVariable String name){
        long start = System.currentTimeMillis();
        RestCallStatistics restCallStatistics = new RestCallStatistics();
        try {
            List<Book> allDeals = ScopedValue.callWhere(REST_CALL_STATISTICS_SCOPED_VALUE, restCallStatistics,
                    () -> bookRetrievalService.findAllDeals(name));


            Book bestPriceDeal = allDeals.stream()
                    .min(Comparator.comparing(Book::cost))
                    .orElseThrow(() -> new IllegalArgumentException("No Book found with name:" + name));

            return new BestPriceResponse(bestPriceDeal,allDeals ,restCallStatistics);

        }catch (Exception e){
            throw new RuntimeException("Exception while calling getBestPrice",e);
        }finally {
                restCallStatistics.addTime("Best", System.currentTimeMillis() - start);
        }
    }
}
