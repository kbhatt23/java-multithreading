package com.learning.tripadvisor.services;

import com.learning.tripadvisor.dtos.Flight;
import com.learning.tripadvisor.dtos.FlightReservationRequest;
import com.learning.tripadvisor.dtos.FlightReservationResponse;
import com.learning.tripadvisor.dtos.TripReservationRequest;
import com.learning.tripadvisor.restclients.FlightReservationServiceClient;
import com.learning.tripadvisor.restclients.FlightSearchServiceClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripReservationService {

    private static final Logger logger = LoggerFactory.getLogger(TripReservationService.class);

    private final FlightSearchServiceClient flightSearchServiceClient;

    private final FlightReservationServiceClient flightReservationServiceClient;

    public FlightReservationResponse reserve(TripReservationRequest tripReservationRequest){

        String departure = tripReservationRequest.departure();
        String arrival = tripReservationRequest.arrival();
        List<Flight> flights = flightSearchServiceClient.getFlights(departure, arrival);

        Flight cheapestFlight = flights.stream()
                .min(Comparator.comparingInt(Flight::price))
                .orElseThrow(() -> new IllegalStateException("unable to find any flight"));

        FlightReservationRequest flightReservationRequest = new FlightReservationRequest(departure, arrival, cheapestFlight.flightNumber(), tripReservationRequest.date());

        return flightReservationServiceClient.reserve(flightReservationRequest);
    }

}
