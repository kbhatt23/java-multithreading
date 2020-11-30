package com.learning.multithreading.parallelprogramming;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class IoIntensiveTask {
	public static void main(String[] args) {
		List<Integer> data = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());
		executeUsingThreadpool(4, data);
	
		//this is not io intensive task and hence too much thread is actually bad for perfoamance and memory
		//executeUsingThreadpool(100, data);
	}

	public static void executeUsingThreadpool(int size, List<Integer> data) {
		CommonUtil.startTimer();
		ForkJoinPool pool = new ForkJoinPool(size);

		ForkJoinTask<?> submit = pool
				.submit(() -> data.parallelStream().map(IoIntensiveTask::manipulateIOIntensiveTask).forEach(result -> {
				}));

		pool.shutdown();
		submit.join();
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
	}

	public static double manipulateIOIntensiveTask(int number) {
		double result = 0;
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				result = Math.sqrt(i * j);
			}
		}
		return result;
	}
}
