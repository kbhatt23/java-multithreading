package com.learning.multithreading.basicalgos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class SumInParallel {
public static void main(String[] args) {
	int[] numbers = IntStream.rangeClosed(1, 10000).toArray();
	
	System.out.println("sum using single thread "+sumUsingSingleThread(numbers));
	System.out.println("sum using multiple threads "+sumUsingMultipleThreads(numbers));
	
}

private static long sumUsingMultipleThreads(int[] numbers) {
	long start = System.currentTimeMillis();
	int parts = Runtime.getRuntime().availableProcessors();
	int[][] splitParts = splitParts(numbers,parts);
	
	ExecutorService svc= Executors.newFixedThreadPool(parts);
	List<Future<Long>> futures = new ArrayList<Future<Long>>(parts);
	for(int[] part: splitParts) {
		futures.add(svc.submit(() -> sumUsingSingleThread(part)));
	}
	
	long sum= futures.stream().
	
	mapToLong(f -> {
		try {
			return f.get().longValue();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	})
			.sum();
	
	long end = System.currentTimeMillis();
	System.out.println("paralele total time taken "+(end-start));
	return sum;
	
}

private static long sumUsingSingleThread(int[] numbers) {
	long start = System.currentTimeMillis();
	long sum = 0;
	for(int num:numbers) {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sum+=num;
	}
	long end = System.currentTimeMillis();
	System.out.println("sequential total time taken "+(end-start));
	return sum;
}

public static int[][] splitParts(int[] array,int parts) {
	
	
	int[][] splitArrays = new int[parts][];
	int length = array.length;
	int sizeOfEachArray  = length/parts;
	int sizeOfLastArray  =sizeOfEachArray+length % parts;
	int mainArrayInde  = 0;
	for(int i=0 ; i< splitArrays.length; i++) {
		int[] splitArray = (i == parts-1) ? new int[sizeOfLastArray]: new int[sizeOfEachArray]; 
		
		for(int j =0 ; j < splitArray.length;j++) {
			splitArray[j] = array[mainArrayInde];
			mainArrayInde++;
		}
		splitArrays[i]=splitArray;
		
	}
	return splitArrays;
}

}
