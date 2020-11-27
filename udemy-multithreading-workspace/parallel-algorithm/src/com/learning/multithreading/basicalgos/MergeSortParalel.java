package com.learning.multithreading.basicalgos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.learning.multithreading.util.ParallelUtil;

public class MergeSortParalel {
public static void main(String[] args) {
	int[] numbers = new int[] { 4, 3, 2, 6, 7, 822, 2, 1, 6, 9 ,11,12};
	int[][] splitParts = ParallelUtil.splitParts(numbers);
	
	ExecutorService svc = Executors.newFixedThreadPool(ParallelUtil.PROCESSORS_SIZE);
	
	for(int[] part:splitParts) {
		svc.submit(() -> SelfMergeSort.mergeSort(numbers, 0, part.length));
	}
	
	svc.shutdown();
	
	
	
}
}
