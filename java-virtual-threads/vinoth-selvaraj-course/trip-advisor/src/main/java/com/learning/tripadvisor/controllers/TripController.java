package com.learning.tripadvisor.controllers;

import com.learning.tripadvisor.dtos.FlightReservationResponse;
import com.learning.tripadvisor.dtos.TripPlan;
import com.learning.tripadvisor.dtos.TripReservationRequest;
import com.learning.tripadvisor.services.TripPlanService;
import com.learning.tripadvisor.services.TripReservationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final TripPlanService tripPlanService;

    private final TripReservationService tripReservationService;

    private  static  final Logger logger = LoggerFactory.getLogger(TripController.class);

    @GetMapping("/{airportCode}")
    public TripPlan planTrip(@PathVariable String airportCode){
        logger.info("planTrip called for airportCode:{}",airportCode);
        return tripPlanService.getTripPlan(airportCode);
    }

    @PostMapping
    public FlightReservationResponse reserveFlight(@RequestBody TripReservationRequest tripReservationRequest){
        logger.info("reserveFlight called for tripReservationRequest:{}",tripReservationRequest);
        return tripReservationService.reserve(tripReservationRequest);
    }
}
