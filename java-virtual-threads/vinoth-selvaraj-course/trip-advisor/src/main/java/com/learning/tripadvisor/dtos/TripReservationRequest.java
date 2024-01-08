package com.learning.tripadvisor.dtos;

import java.time.LocalDate;

public record TripReservationRequest(String departure,
                                     String arrival,
                                     LocalDate date) {
}
