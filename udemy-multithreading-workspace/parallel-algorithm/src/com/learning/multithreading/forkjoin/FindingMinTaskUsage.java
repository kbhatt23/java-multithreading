package com.learning.multithreading.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindingMinTaskUsage {
public static void main(String[] args) {
	List<Integer> numbers = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());
	int min = numbers.get(0);
	for(int i=1; i < numbers.size() ; i++) {
		Integer integer = numbers.get(i);
		if(integer < min) {
			min=integer;
		}
	}
	System.out.println("min using sequntial "+min);

	
	
	
	FindingMinTask task = new FindingMinTask(numbers, 0, numbers.size());
	
	ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors()+1);
	ForkJoinTask<Integer> submit = pool.submit(task);
	
	System.out.println("min item found using pool "+submit.join());
}
}
