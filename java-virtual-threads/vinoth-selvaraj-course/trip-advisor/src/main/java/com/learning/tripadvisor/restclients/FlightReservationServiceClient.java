package com.learning.tripadvisor.restclients;


import com.learning.tripadvisor.dtos.FlightReservationRequest;
import com.learning.tripadvisor.dtos.FlightReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class FlightReservationServiceClient {

    private final RestClient client;

    public FlightReservationResponse reserve(FlightReservationRequest request) {
        return this.client.post()
                          .body(request)
                          .retrieve()
                          .body(FlightReservationResponse.class);
    }

}
