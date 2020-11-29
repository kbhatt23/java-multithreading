package com.learning.paralleprogramming.issues;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FactorialForkJoinTask extends RecursiveTask<List<Long>>{
	
	private List<Integer> numbers;
	private int startIndex;
	private int endIndex;
	private int batchSize;
	public FactorialForkJoinTask(List<Integer> numbers, int startIndex, int endIndex,int batchSize) {
		this.numbers = numbers;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.batchSize=batchSize;
	}
	@Override
	protected List<Long> compute() {

		if((endIndex-startIndex) > batchSize) {
			int mid = (startIndex+endIndex)/2;
			FactorialForkJoinTask firstHalf = new FactorialForkJoinTask(numbers, startIndex, mid, batchSize);
			FactorialForkJoinTask secondHalf = new FactorialForkJoinTask(numbers, mid+1, endIndex, batchSize);
			//better approach is to make all task no blokcing
			//as anyway we have work stealing and hence the first created thread can just act as mater , time divider and merger for fast processing
			//forking more than one task . pushing into the quue, let htreads steal task and do the job themselves
			invokeAll(firstHalf,secondHalf);
		
			ArrayList<Long> output = new ArrayList<>(firstHalf.join());
			output.addAll(secondHalf.join());
			return output;
		}
		else {
			//do the task in range of partition start and end index
			List<Long> output = new ArrayList<Long>();
			for(int i = startIndex ; i <= endIndex; i++) {
				int number = numbers.get(i);
				long factorial =1;
				for(int j=1 ; j <= number; j++) {
					factorial*=j;
				}
				output.add(factorial);
			}
			return output;
		}
	}

}
