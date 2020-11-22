package com.learning.multithreading.executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class FactorialExecutors {
public static void main(String[] args) {
	List<Integer> numbers = Arrays.asList(10,21,32,27,37,23,11,18,10,21,32,27,37,23,11,18,10,21,32,27,37,23,11,18,10,21,32,27,37,23,11,18,10,21,32,27,37,23,11,18,10,21,32,27,37,23,11,18,10,21,32,27,37,23,11,18,10,21,32,27,37,23,11,18);
	
	//first do it using sequentials
	sequentialCalculation(numbers);
	
	//executorService(numbers);
	
}

private static void executorService(List<Integer> numbers) {
	long start = System.currentTimeMillis();
	ExecutorService svc = Executors.newFixedThreadPool(4);
	List<Future<Long>> collect = numbers.stream()
			.map(FactorialCalucaltor::new)
			.map(obj -> svc.submit(obj))
			.collect(Collectors.toList());
	collect.stream()
	.map(future -> {
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	})
	.forEach(System.out::println);
			
			long end = System.currentTimeMillis();
	
			System.out.println("Total time taken in seconds by executor: "+(end-start)/60);
}

private static void sequentialCalculation(List<Integer> numbers) {
	long start = System.currentTimeMillis();
	numbers.stream()
		  .map(number -> new FactorialCalucaltor(number))
		  .map(t -> {
			try {
				return t.call();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return -1;
		})
		  .forEach(System.out::println);
	
	long end = System.currentTimeMillis();
	
	System.out.println("Total time taken in seconds by sequential: "+(end-start)/60);
}
	
}
