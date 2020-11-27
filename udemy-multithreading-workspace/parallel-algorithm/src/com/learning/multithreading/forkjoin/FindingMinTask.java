package com.learning.multithreading.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FindingMinTask extends RecursiveTask<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Integer> numbers;
	private int startIndex;
	private int endIndex;
	
	public FindingMinTask(List<Integer> numbers,int startIndex,int endIndex) {
		this.numbers=numbers;
		this.startIndex=startIndex;
		this.endIndex=endIndex;
	}
	public static final int MAX_BATCH_SIZE=10;
	
	@Override
	protected Integer compute() {
		if((endIndex-startIndex-1) > MAX_BATCH_SIZE) {
			//lets break it
			int mid = (endIndex+startIndex)/2;
			
			FindingMinTask firstHalf = new FindingMinTask(numbers, startIndex, mid);
			//passing first half to pool
			firstHalf.fork();
			
			FindingMinTask secondHalf = new FindingMinTask(numbers, mid, endIndex);
			Integer secondHalfResult = secondHalf.compute();
			
			Integer firstHalfResult = firstHalf.join();

			return firstHalfResult > secondHalfResult ? secondHalfResult : firstHalfResult;
		}else {
			//compute now
			int min = numbers.get(startIndex);
			for(int i=startIndex+1; i < endIndex ; i++) {
				Integer integer = numbers.get(i);
				if(integer < min) {
					min=integer;
				}
			}
			return min;
		}
	}

	
}
