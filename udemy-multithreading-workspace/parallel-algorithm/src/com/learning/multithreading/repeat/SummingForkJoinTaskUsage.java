package com.learning.multithreading.repeat;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

import com.learning.multithreading.util.ParallelUtil;

public class SummingForkJoinTaskUsage {
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(ParallelUtil.PROCESSORS_SIZE + 1);

		int[] numbers = IntStream.rangeClosed(1, 10000000).toArray();
		System.out.println("actual expected sum " + Arrays.stream(numbers).sum());
		
		usingSequential(numbers);
		
		
		usingForkJoin(forkJoinPool, numbers);

	}

	public static void usingForkJoin(ForkJoinPool forkJoinPool, int[] numbers) {
		long start = System.nanoTime();
		ForkJoinTask<Long> submit = forkJoinPool.submit(new SummingForkJoinTask(numbers, 0, numbers.length - 1));

		try {
			long sumResult = submit.get();
			System.out.println("Fork join result " + sumResult);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.nanoTime();
		System.out.println("total time taken in forkjoin " + (end - start));
	}

	private static void usingSequential(int[] numbers) {
		long start = System.nanoTime();
		System.out.println("sequential sum found " + sumSequentially(numbers));
		long end = System.nanoTime();
		System.out.println("total time taken in sequential " + (end - start));
	}
	
	public static long sumSequentially(int[] numbers) {
		long sum=0;
		for (int number : numbers) {
			sum+=number;
		}
		return sum;
	}
}
