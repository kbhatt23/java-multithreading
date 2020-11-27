package com.learning.multithreading.repeat;

import java.util.concurrent.RecursiveAction;

import com.learning.multithreading.util.ParallelUtil;

public class MergeSortForkJoinTask extends RecursiveAction{
	private int start;
	private int end;
	private int[] numbers;
	
	

	public MergeSortForkJoinTask(int start, int end, int[] numbers) {
		this.start = start;
		this.end = end;
		this.numbers = numbers;
	}



	@Override
	protected void compute() {
		int batchSize = numbers.length/ParallelUtil.PROCESSORS_SIZE;
		if(end-start > batchSize) {
			int mid = (end+start)/2;
			MergeSortForkJoinTask firstHalf = new MergeSortForkJoinTask(start, mid, numbers);
			firstHalf.fork();
			
			MergeSortForkJoinTask secondHalf = new MergeSortForkJoinTask(mid+1, end, numbers);
			secondHalf.compute();
			
			firstHalf.join();
			MergeSort.merge(numbers, start, end, mid);
		}else {
			MergeSort.mergeSort(numbers, start, end);
		}
	}

}
