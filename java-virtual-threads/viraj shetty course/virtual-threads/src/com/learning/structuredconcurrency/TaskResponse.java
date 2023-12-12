package com.learning.structuredconcurrency;

public record TaskResponse(String name, long timeTaken, boolean fail, String response) {

}
