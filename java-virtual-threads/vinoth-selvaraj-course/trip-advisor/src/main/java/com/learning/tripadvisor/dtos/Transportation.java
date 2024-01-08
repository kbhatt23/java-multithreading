package com.learning.tripadvisor.dtos;

import java.util.List;

public record Transportation(List<CarRental> carRentals,
                             List<PublicTransportation> publicTransportations) {
}
