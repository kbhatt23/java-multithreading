package com.learning.multithreading.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveTask;
//input will be int[] or int list but result will be long of sum
public class SummingTask extends RecursiveTask<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Integer> numbers;
	private int startIndex;
	private int endIndex;
	public static final int MAX_BATCH_SIZE=10;
	
	public SummingTask(List<Integer> numbers,int startIndex,int endIndex) {
		this.numbers=numbers;
		this.startIndex=startIndex;
		this.endIndex=endIndex;
	}
	
	@Override
	protected Long compute() {
		//check if divide is needed
		if((endIndex-startIndex-1) > MAX_BATCH_SIZE) {
			//System.out.println(String.format("Breaking the array with startindex %s and endIndex %s", startIndex,endIndex));
			
			int mid = (startIndex+endIndex)/2;
			SummingTask firstHalf = new SummingTask(numbers, startIndex, mid);
			//handover to other thread pool queue
			firstHalf.fork();
			
			SummingTask secondHalf = new SummingTask(numbers, mid, endIndex);
			Long secondHalfResult = secondHalf.compute();
		
			Long firstHalfResult = firstHalf.join();
			return firstHalfResult+secondHalfResult;
			
			
		}else {
			//System.out.println(String.format("Starting sum calcualtion with startIndex %s and endIndex %s", startIndex,endIndex));
			long sum=0;
			for(int i = startIndex; i < endIndex ; i++) {
				sum+=numbers.get(i);
			}
			return sum;
		}
	}

}
