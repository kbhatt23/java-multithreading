package com.learning.multithreading.forkjoin.advacned;

import java.util.concurrent.RecursiveAction;

import com.learning.multithreading.basicalgos.SelfMergeSort;

public class MergeSortTask extends RecursiveAction{

	private static final long serialVersionUID = 8376232644099783100L;
	private int[] numbers;
	int startIndex;
	int endIndex;
	
	public MergeSortTask(int[] numbers, int startIndex, int endIndex) {
		this.numbers = numbers;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}




	@Override
	protected void compute() {
		if((endIndex-startIndex) > 4) {
			//break it
			int mid = (endIndex+startIndex)/2;
			MergeSortTask firstHalf = new MergeSortTask(numbers, startIndex, mid);
			firstHalf.fork();
			
			MergeSortTask secondHalf = new MergeSortTask(numbers, mid+1, endIndex);
			secondHalf.compute();
			
			firstHalf.join();
			SelfMergeSort.merge(numbers, startIndex, mid, endIndex);
			
		}else {
			//do the task
			SelfMergeSort.mergeSort(numbers, startIndex, endIndex);
			
		}
	}

}
