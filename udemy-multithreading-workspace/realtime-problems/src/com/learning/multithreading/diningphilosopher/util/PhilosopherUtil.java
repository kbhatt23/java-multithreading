package com.learning.multithreading.diningphilosopher.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.learning.multithreading.diningphilosopher.beans.Fork;
import com.learning.multithreading.diningphilosopher.beans.Philosopher;

public class PhilosopherUtil {
	public static final int PHILOSOPHER_SIZE = 5;
	public static final int GAME_SIMULATION_TIME_SECONDS = 60;

	public static boolean aquireLock(Lock lock1, Lock lock2) {

			
				  lock1.lock();
				  lock2.lock();
				  return true;
				

	}

	public static List<Philosopher> generateData() {
		List<Fork> forks = IntStream.rangeClosed(1, PHILOSOPHER_SIZE)
				.mapToObj(i -> new Fork(i, new ReentrantLock(true))).collect(Collectors.toList());
		List<Philosopher> philosophers = new ArrayList<Philosopher>(PHILOSOPHER_SIZE);
		for (int i = 0; i < PHILOSOPHER_SIZE; i++) {
			Philosopher philosopher = null;
			if (i == PHILOSOPHER_SIZE - 1) {
				// last element
				philosopher = new Philosopher("Philosopher-" + (i + 1), forks.get(i), forks.get(0));
			} else {
				philosopher = new Philosopher("Philosopher-" + (i + 1), forks.get(i), forks.get(i + 1));
			}
			philosophers.add(philosopher);
		}

		return philosophers;
	}
}
