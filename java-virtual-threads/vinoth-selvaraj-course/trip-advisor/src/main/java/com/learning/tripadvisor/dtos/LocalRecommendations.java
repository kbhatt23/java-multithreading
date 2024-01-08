package com.learning.tripadvisor.dtos;

import java.util.List;

public record LocalRecommendations(List<String> restaurants,
                                   List<String> sightseeing) {
}
