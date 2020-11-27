package com.learning.multithreading.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SummingTaskUsage {
	public static void main(String[] args) {
		List<Integer> numbers = IntStream.rangeClosed(1, 1000000).boxed().collect(Collectors.toList());
		long start = System.nanoTime();
		long sumSequential = 0;
		for(int n : numbers) {
			sumSequential+=n;
		}
		System.out.println("sum using stream " + sumSequential);
		long end = System.nanoTime();
		System.out.println("sequential time taken "+(end-start));

		start = System.nanoTime();
		SummingTask summingTask = new SummingTask(numbers, 0, numbers.size());

		// each batch will run in its own thread at max and 1 is for main thread
		//int poolSize = (numbers.size() / SummingTask.MAX_BATCH_SIZE) + 1;
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors()+1);

		ForkJoinTask<Long> submit = pool.submit(summingTask);

		Long result = submit.join();
		System.out.println("sum result " + result);
		end = System.nanoTime();
		System.out.println("parallel time taken "+(end-start));
	}
}
