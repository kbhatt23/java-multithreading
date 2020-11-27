package com.learning.multithreading.advacnedalgos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import com.learning.multithreading.forkjoin.advacned.CheckingPrimeNumbersForkJoin;
import com.learning.multithreading.util.ParallelUtil;

public class CheckingPrimeNumbersParalley {
	public static void main(String[] args) {
		
		
		
		int[] numbers = IntStream.rangeClosed(3, 10000000).toArray();
	
		//cahing for JIT
		for(int i=0; i < numbers.length;i++) {
			checkPrimeNumber(numbers[i]);
		}
		
		
		checkPrimeNumberSequential(numbers);
		
		checkPrimeNumberParallel(numbers);
		
		//checkPrimeNumberParallelDivideConquer(numbers);
	}

	private static void checkPrimeNumber(int number) {
		int squareRoot = (int) Math.sqrt(number) + 1;
		boolean isPrime = true;
		for (int i = 2; i < squareRoot; i++) {
			if (number % i == 0) {
				isPrime = false;
			}
		}
		try {
		if(isPrime) {
			//System.out.println(number+" is a prime number");
		}else {
			//System.out.println(number+" is not a prime number");
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static void checkPrimeNumberParallel(int[] numbers) {
		long start = System.nanoTime();
		int[][] splitParts = ParallelUtil.splitParts(numbers);
		
		ExecutorService servie = Executors.newFixedThreadPool(ParallelUtil.PROCESSORS_SIZE);
		for(int[] part: splitParts) {
			servie.execute( () -> {
				for(int i=0; i < part.length;i++) {
					checkPrimeNumber(part[i]);
				}
			});
		}
		
		servie.shutdown();
		try {
			servie.awaitTermination(60000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servie.shutdownNow();
		long end = System.nanoTime();
		System.out.println("total time taken in paralle "+(end-start));
		
	}
	
	private static void checkPrimeNumberParallelDivideConquer(int[] numbers) {
		long start = System.nanoTime();
		checkPrimieNumberDnQ(numbers,0,numbers.length);
		long end = System.nanoTime();
		System.out.println("total time taken in paralle DNQ "+(end-start));
		
	}
	
	private static void checkPrimieNumberDnQ(int[] numbers, int startIndex, int endIndex) {
		//same split parts logic like forkjoin
		if((endIndex - startIndex -1) > (numbers.length/ParallelUtil.PROCESSORS_SIZE)) {
			int mid = (endIndex+startIndex)/2;
			checkPrimieNumberDnQ(numbers, 0, mid);
			checkPrimieNumberDnQ(numbers, mid, endIndex);
		}else {
			//do the task
			ExecutorService service = Executors.newSingleThreadExecutor();
			for(int i=startIndex;i<endIndex ; i++) {
				checkPrimeNumber(numbers[i]);
			}
		}
	}

	private static void checkPrimeNumberSequential(int[] numbers) {
		long start = System.nanoTime();
		for(int number: numbers) {
			checkPrimeNumber(number);
		}
		long end = System.nanoTime();
		System.out.println("total time taken in sequential "+(end-start));
	}

}
