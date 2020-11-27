package com.learning.multithreading.basicalgos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class FindMinParrallel {
public static void main(String[] args) {
	int[] numbers = IntStream.rangeClosed(1, 100000000).toArray();
	long start = System.nanoTime();
	int minSequential = findMinSequential(numbers);
	System.out.println("min found sequential "+minSequential);
	long end = System.nanoTime();
	System.out.println("sequential total time taken "+(end-start));
	
	start = System.nanoTime();
	//int findMinParallell = findMinParallell(numbers);
	int findMinParallell = findMinParallellStream(numbers);
	
	System.out.println("min found in paralle "+findMinParallell);
	end = System.nanoTime();
	System.out.println("parallel total time taken "+(end-start));
}

private static int findMinSequential(int[] numbers) {
	
	int min = numbers[0];
	for(int num:numbers) {
		if(num < min) {
			min=num;
		}
	}
	return min;

}

private static int findMinParallell(int[] numbers) {
	int parts= Runtime.getRuntime().availableProcessors();
	int[][] splitParts = splitParts(numbers, parts);
	List<Future<Integer>> futures = new ArrayList<>(parts);
	ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(parts);
	for(int[] part: splitParts) {
		futures.add(newFixedThreadPool.submit(() ->findMinSequential(part)));
	}
	int min = futures.parallelStream()
			.map(t -> {
				try {
					return t.get();
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return -1;
			})
			.mapToInt(i -> i)
			.min().orElse(-1);
	
	return min;

}

private static int findMinParallellStream(int[] numbers) {
return Arrays.stream(numbers)
			 .parallel()
			.min().orElseGet(() -> -1);

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
