package com.learning.multithreading.basicalgos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FactorialInParallel {
	public static void main(String[] args) {
		int[] numbers = IntStream.rangeClosed(1, 200).toArray();
		
		long[] factorials = factorialsUsingSingleThread(numbers);
		
		printArray(factorials);
		
		long[] factorialsUsingMultipleThread = factorialsUsingMultipleThread(numbers);
		printArray(factorialsUsingMultipleThread);
	}
	
	
	private static void printArray(long[] factorials) {
		System.out.println("=========================");
		String collect = Arrays.stream(factorials).mapToObj(String::valueOf).collect(Collectors.joining(","));
		System.out.println(collect);
		System.out.println("=========================");
	}
	public static long[] factorialsUsingMultipleThread(int[] numbers) {
		long start = System.nanoTime();
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		int[][] splitParts = splitParts(numbers, availableProcessors);
		long[] factorials = new long[numbers.length];
		List<Future<?>> futures = new ArrayList<Future<?>>(availableProcessors);
		ExecutorService svc =Executors.newFixedThreadPool(availableProcessors);
		for(int i =0 ; i<splitParts.length;i++) {
			int[] partsArray = splitParts[i];
			futures.add(svc.submit(() -> {
				return factorialsUsingSingleThread(partsArray);
			}));
		}
		int mainArrayIndex =0;
		for(Future<?> future:futures) {
			try {
				long[] factorialParts = (long[]) future.get();
				for(long part: factorialParts) {
					factorials[mainArrayIndex] = part;
					mainArrayIndex++;
				}
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		long end = System.nanoTime();
		System.out.println("parallel total time taken "+(end-start));
		return factorials;
		
	}

	public static long[] factorialsUsingSingleThread(int[] numbers) {
		long start = System.nanoTime();
		int length = numbers.length;
		long[] factorials = new long[length];
		for (int i=0; i<length;i++) {
			factorials[i]=findFactorial(numbers[i]);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long end = System.nanoTime();
		System.out.println("sequential total time taken "+(end-start));
		return factorials;
		
	}
	public static long findFactorial(int number) {
		long factorial = 1;
		for(int i = 1; i <= number ; i++) {
			factorial*=i;
		}
		return factorial;
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
