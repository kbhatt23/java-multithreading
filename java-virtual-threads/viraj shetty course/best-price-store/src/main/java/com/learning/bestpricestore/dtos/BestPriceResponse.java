package com.learning.bestpricestore.dtos;

import java.util.List;

public record BestPriceResponse(Book bestPriceDeal, List<Book> allDeals, RestCallStatistics restCallStatistics) {
}
