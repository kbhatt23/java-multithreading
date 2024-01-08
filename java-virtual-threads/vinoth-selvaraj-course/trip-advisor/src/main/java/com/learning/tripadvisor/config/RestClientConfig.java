package com.learning.tripadvisor.config;

import com.learning.tripadvisor.restclients.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.util.concurrent.Executors;

@Configuration
public class RestClientConfig {
    private static final Logger log = LoggerFactory.getLogger(RestClientConfig.class);

    @Value("${spring.threads.virtual.enabled}")
    private boolean isVirtualThreadEnabled;

    @Bean
    public AccommodationServiceClient accommodationServiceClient(@Value("${accommodation.service.url}") String baseUrl){
        return  new AccommodationServiceClient(createRestClient(baseUrl));
    }

    @Bean
    public EventServiceClient eventServiceClient(@Value("${event.service.url}") String baseUrl){
        return new EventServiceClient(createRestClient(baseUrl));
    }

    @Bean
    public WeatherServiceClient weatherServiceClient(@Value("${weather.service.url}") String baseUrl){
        return new WeatherServiceClient(createRestClient(baseUrl));
    }

    @Bean
    public TransportationServiceClient transportationServiceClient(@Value("${transportation.service.url}") String baseUrl){
        return new TransportationServiceClient(createRestClient(baseUrl));
    }

    @Bean
    public LocalRecommendationServiceClient recommendationServiceClient(@Value("${local-recommendation.service.url}") String baseUrl){
        return new LocalRecommendationServiceClient(createRestClient(baseUrl));
    }

    @Bean
    public FlightSearchServiceClient flightSearchServiceClient(@Value("${flight-search.service.url}") String baseUrl){
        return new FlightSearchServiceClient(createRestClient(baseUrl));
    }

    @Bean
    public FlightReservationServiceClient reservationServiceClient(@Value("${flight-reservation.service.url}") String baseUrl){
        return new FlightReservationServiceClient(createRestClient(baseUrl));
    }


    private RestClient createRestClient(String baseUrl){
        log.info("createRestClient: base url: {}", baseUrl);
        RestClient.Builder builder = RestClient.builder().baseUrl(baseUrl);
        if(isVirtualThreadEnabled){
            builder.requestFactory(new JdkClientHttpRequestFactory(
                    HttpClient.newBuilder().executor(Executors.newVirtualThreadPerTaskExecutor()).build()
            ));
        }
        return builder.build();
    }
}
