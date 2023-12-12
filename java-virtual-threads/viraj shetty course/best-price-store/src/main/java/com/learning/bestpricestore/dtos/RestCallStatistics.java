package com.learning.bestpricestore.dtos;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RestCallStatistics {
    private Map<String,Long> timeMap = new ConcurrentHashMap<>();

    public Map<String, Long> getTimeMap() {
        return timeMap;
    }

    public void addTime(String name, long timeTaken){
        timeMap.put(name,timeTaken);
    }
}
