package com.learning.multithreading.forkjoin.advacned;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;

import com.learning.multithreading.basicalgos.SelfMergeSort;
import com.learning.multithreading.util.ParallelUtil;

public class MergeSortTaskUage {
public static void main(String[] args) {
	//int[] numbers = new int[] { 4, 3, 2, 6, 7, 822, 2, 1, 6, 9 ,11,12};
	
	int[] numbers = ParallelUtil.generateNumbersRandonly();
	//runUsingSequential(numbers);
	runUsingForkJoin(numbers);
	
	
}

private static void runUsingSequential(int[] numbers) {
	long start = System.nanoTime();
	String collect1 = Arrays.stream(numbers).mapToObj(String::valueOf).collect(Collectors.joining(",","[","]"));
	System.out.println(collect1);
	SelfMergeSort.mergeSort(numbers, 0, numbers.length-1);
	
	String collect = Arrays.stream(numbers).mapToObj(String::valueOf).collect(Collectors.joining(",","[","]"));
	System.out.println(collect);
	System.out.println("is array sorted "+ParallelUtil.isArraySorted(numbers));
	
	long end = System.nanoTime();
	System.out.println("total time taken in sequential "+(end-start));
}

public static void runUsingForkJoin(int[] numbers) {
	long start = System.nanoTime();
	String collect1 = Arrays.stream(numbers).mapToObj(String::valueOf).collect(Collectors.joining(",","[","]"));
	System.out.println(collect1);
	
	ForkJoinPool pool = new ForkJoinPool(ParallelUtil.PROCESSORS_SIZE+1);
	MergeSortTask task = new MergeSortTask(numbers, 0, numbers.length-1);
	ForkJoinTask<Void> submit = pool.submit(task);
	submit.join();
	
	String collect = Arrays.stream(numbers).mapToObj(String::valueOf).collect(Collectors.joining(",","[","]"));
	System.out.println(collect);
	System.out.println("is array sorted "+ParallelUtil.isArraySorted(numbers));
	long end = System.nanoTime();
	System.out.println("total time taken in parallel "+(end-start));
}

}
