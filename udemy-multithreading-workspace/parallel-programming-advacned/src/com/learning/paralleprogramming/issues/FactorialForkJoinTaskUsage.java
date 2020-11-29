package com.learning.paralleprogramming.issues;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

public class FactorialForkJoinTaskUsage {
	public static void main(String[] args) {

		List<Integer> numbers = IntStream.rangeClosed(0, 200000).collect(ArrayList::new, List::add, List::addAll);
		usingSequential(numbers);
		usingForkJoin(numbers);
	}

	private static void usingSequential(List<Integer> numbers) {
		long start = System.nanoTime();
		List<Long> factorails = new ArrayList<Long>();
		for (int number : numbers) {
			long factorial = 1;
			for (int j = 1; j <= number; j++) {
				factorial *= j;
			}
			factorails.add(factorial);
		}
		System.out.println(factorails);
		long end = System.nanoTime();
		System.out.println("sequnetial toalt time taken " + (end - start));
	}

	public static void usingForkJoin(List<Integer> numbers) {
		long start = System.nanoTime();
		int processors = Runtime.getRuntime().availableProcessors();
		int size = numbers.size();
		int batchSize = size / processors;
		ForkJoinPool pool = new ForkJoinPool(processors + 1);
		ForkJoinTask<List<Long>> submit = pool.submit(new FactorialForkJoinTask(numbers, 0, size - 1, batchSize));

		List<Long> output = submit.join();
		System.out.println(output);
		long end = System.nanoTime();
		System.out.println("parallel toalt time taken " + (end - start));
	}

}
