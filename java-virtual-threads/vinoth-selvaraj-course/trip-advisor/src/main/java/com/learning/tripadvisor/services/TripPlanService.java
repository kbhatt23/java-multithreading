package com.learning.tripadvisor.services;

import com.learning.tripadvisor.dtos.TripPlan;
import com.learning.tripadvisor.restclients.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class TripPlanService {

    private static final Logger logger = LoggerFactory.getLogger(TripPlanService.class);
    private final EventServiceClient eventServiceClient;
    private final WeatherServiceClient weatherServiceClient;
    private final AccommodationServiceClient accommodationServiceClient;
    private final TransportationServiceClient transportationServiceClient;
    private final LocalRecommendationServiceClient localRecommendationServiceClient;
    private final ExecutorService executor;

    public TripPlan getTripPlan(String airportCode){
        var events = this.executor.submit(() -> this.eventServiceClient.getEvents(airportCode));
        var weather = this.executor.submit(() -> this.weatherServiceClient.getWeather(airportCode));
        var accommodations = this.executor.submit(() -> this.accommodationServiceClient.getAccommodations(airportCode));
        var transportation = this.executor.submit(() -> this.transportationServiceClient.getTransportation(airportCode));
        var recommendations = this.executor.submit(() -> this.localRecommendationServiceClient.getRecommendations(airportCode));
        return new TripPlan(
                airportCode,
                getOrElse(accommodations, Collections.emptyList()),
                getOrElse(weather, null),
                getOrElse(events, Collections.emptyList()),
                getOrElse(recommendations, null),
                getOrElse(transportation, null)
        );
    }

    private <T> T getOrElse(Future<T> future, T defaultValue){
        try {
            return future.get();
        } catch (Exception e) {
            logger.error("getOrElse: error", e);
        }
        return defaultValue;
    }
}
