package com.learning.tripadvisor;

import com.learning.tripadvisor.dtos.Accommodation;
import com.learning.tripadvisor.dtos.FlightReservationRequest;
import com.learning.tripadvisor.dtos.FlightReservationResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;

//no need to load context
class RestClientTests {

	private static final Logger logger = LoggerFactory.getLogger(RestClientTests.class);
	@Test
	void getAccommodations() {
		RestClient restClient = RestClient.builder().baseUrl("http://localhost:7070/sec02/accommodations/")
				.build();

		List<Accommodation> accommodations = restClient.get()
				.uri("{airportCode}", "LAS")
				.retrieve()
				.body(new ParameterizedTypeReference<List<Accommodation>>() {
				});

		logger.info("accommodations: {}",accommodations);

	}

	@Test
	public void postReservation(){
		RestClient restClient = RestClient.builder().baseUrl("http://localhost:7070/sec03/flight/reserve")
				.build();

		FlightReservationRequest flightReservationRequest = new FlightReservationRequest("JFK", "LAS", "AA123", LocalDate.now());

		FlightReservationResponse flightReservationResponse = restClient.post()
				.body(flightReservationRequest)
				.retrieve()
				.body(FlightReservationResponse.class);
		logger.info("flightReservationResponse: {}",flightReservationResponse);
	}


}
