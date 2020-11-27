package com.learning.multithreading.repeat;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class SummingUsingDivideAndConquer {
private static final int ITEMS_SIZE = 100000;
private static final int BATCH_SIZE = ITEMS_SIZE/Runtime.getRuntime().availableProcessors();

public static void main(String[] args) {

	int[] numbers = IntStream.rangeClosed(1, ITEMS_SIZE).toArray();
	System.out.println("actual expected sum "+Arrays.stream(numbers).sum());
	
	
	usingSequential(numbers);
	usingDNQ(numbers);

}

private static void usingSequential(int[] numbers) {
	long start= System.nanoTime();
	System.out.println("sequential sum found "+sumSequentially(numbers));
	long end= System.nanoTime();
	System.out.println("total time taken in sequential "+(end-start));
}

public static void usingDNQ(int[] numbers) {
	long start= System.nanoTime();
	System.out.println("DNQ sum calculated "+sumRecursively(numbers, 0, numbers.length-1));
	long end= System.nanoTime();
	System.out.println("total time taken in parallel "+(end-start));
}

public static long sumRecursively(int[] numbers, int start, int end) {
	//based on natch size
	if(end-start > BATCH_SIZE) {
		int mid = (end+start)/2;
		ExecutorService service = Executors.newFixedThreadPool(2);
		Future<Long> futureLeft = service.submit(() ->sumRecursively(numbers, start, mid) );
		Future<Long> futureRight =service.submit(() ->sumRecursively(numbers, mid+1, end) );
		long sumLeft=0;
		long sumRight=0;
		try {
			sumLeft = futureLeft.get();
			sumRight = futureRight.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sumLeft+sumRight;
	}else {
		//compuation
		long sum=0;
		for(int i = start ; i <= end; i++) {
			sum+=numbers[i];
		}
		return sum;
	}
}

public static long sumSequentially(int[] numbers) {
	long sum=0;
	for (int number : numbers) {
		sum+=number;
	}
	return sum;
}
}
