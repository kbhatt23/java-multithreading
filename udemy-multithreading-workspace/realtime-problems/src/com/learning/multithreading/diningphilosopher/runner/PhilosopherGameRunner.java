package com.learning.multithreading.diningphilosopher.runner;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.learning.multithreading.diningphilosopher.beans.Philosopher;
import com.learning.multithreading.diningphilosopher.util.PhilosopherUtil;
import com.learning.multithreading.diningphilosopher.util.ThreadUtil;

public class PhilosopherGameRunner {
	public static void main(String[] args) {
		List<Philosopher> generateData = PhilosopherUtil.generateData();

		ExecutorService service = Executors.newFixedThreadPool(PhilosopherUtil.PHILOSOPHER_SIZE);
		
		for (Philosopher philosopher : generateData) {
			service.execute(() -> philosopher.eatAndThink() );
		}
		
		service.shutdown();
		
		
		ThreadUtil.sleep(PhilosopherUtil.GAME_SIMULATION_TIME_SECONDS);
		service.shutdownNow();
		
		generateData.stream().forEach(p -> System.out.println(p.getName()+" : "+p.getEatingCount()));
	}
}
