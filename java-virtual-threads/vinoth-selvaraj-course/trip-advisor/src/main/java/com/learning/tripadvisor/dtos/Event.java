package com.learning.tripadvisor.dtos;

import java.time.LocalDate;

public record Event(String name,
                    String description,
                    LocalDate date) {
}
