package com.learning.multithreading.forkjoin.advacned;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

import com.learning.multithreading.util.ParallelUtil;

public class CheckingPrimeNumbersForkJoin {
	public static void main(String[] args) {

		int[] numbers = IntStream.rangeClosed(3, 1000000).toArray();


		checkPrimeNumberSequential(numbers);

		checkPrimeNumberParallel(numbers);
	}

	public static void checkPrimeNumber(int number) {
		int squareRoot = (int) Math.sqrt(number) + 1;
		boolean isPrime = true;
		for (int i = 2; i < squareRoot; i++) {
			if (number % i == 0) {
				isPrime = false;
			}
		}
		try {
			if (isPrime) {
				// System.out.println(number+" is a prime number");
			} else {
				// System.out.println(number+" is not a prime number");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void checkPrimeNumberParallel(int[] numbers) {
		long start = System.nanoTime();
		ForkJoinPool forkJoinPool = new ForkJoinPool(ParallelUtil.PROCESSORS_SIZE + 1);

		ForkJoinTask<Void> submit = forkJoinPool.submit(new CheckPrimeNumberTask(numbers, 0, numbers.length));

		submit.join();

		long end = System.nanoTime();
		System.out.println("total time taken in paralle " + (end - start));

	}

	private static void checkPrimeNumberSequential(int[] numbers) {
		long start = System.nanoTime();
		for (int number : numbers) {
			checkPrimeNumber(number);
		}
		long end = System.nanoTime();
		System.out.println("total time taken in sequential " + (end - start));
	}

}
