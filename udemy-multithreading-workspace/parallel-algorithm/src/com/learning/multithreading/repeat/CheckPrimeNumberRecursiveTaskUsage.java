package com.learning.multithreading.repeat;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

import com.learning.multithreading.util.ParallelUtil;

public class CheckPrimeNumberRecursiveTaskUsage {

	public static void main(String[] args) {

		int[] numbers = ParallelUtil.generateNumbersRandonly();

		CheckPrimeNumberDnQ.validateSequentially(numbers);

		//validateUsingForkJoin(numbers);
	}

	public static void validateUsingForkJoin(int[] numbers) {
		long start = System.nanoTime();

		CheckPrimeNumberRecursiveTask task = new CheckPrimeNumberRecursiveTask(0, numbers.length - 1, numbers);
		ForkJoinPool forkJoinPool = new ForkJoinPool(ParallelUtil.PROCESSORS_SIZE + 1);
		ForkJoinTask<Void> submit = forkJoinPool.submit(task);

		submit.join();
		long end = System.nanoTime();
		System.out.println("sequential total time taken " + (end - start));
	}

}
