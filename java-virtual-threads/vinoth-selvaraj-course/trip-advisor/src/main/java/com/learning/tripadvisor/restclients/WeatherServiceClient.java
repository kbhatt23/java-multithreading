package com.learning.tripadvisor.restclients;

import com.learning.tripadvisor.dtos.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class WeatherServiceClient {

    private final RestClient restClient;

    public Weather getWeather(String airportCode){

        return restClient.get()
                  .uri("{airportCode}", airportCode)
                .retrieve()
                .body(Weather.class);
    }
}
