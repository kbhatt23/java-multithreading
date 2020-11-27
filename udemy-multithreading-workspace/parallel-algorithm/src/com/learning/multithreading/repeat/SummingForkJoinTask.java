package com.learning.multithreading.repeat;

import java.util.concurrent.RecursiveTask;

public class SummingForkJoinTask extends RecursiveTask<Long>{

	private static final long serialVersionUID = 1L;
	
	private int[] numbers;
	private int start;
	private int end;
	private int BATCH_SIZE;

	public SummingForkJoinTask(int[] numbers, int start, int end) {
		super();
		this.numbers = numbers;
		this.start = start;
		this.end = end;
		this.BATCH_SIZE= numbers.length/Runtime.getRuntime().availableProcessors();
	}

	@Override
	protected Long compute() {

		if((end-start) > BATCH_SIZE) {
			//break futher
			int mid = (end+start)/2;
			SummingForkJoinTask leftTask = new SummingForkJoinTask(numbers, start, mid);
			leftTask.fork();
			
			SummingForkJoinTask rightTask = new SummingForkJoinTask(numbers, mid+1, end);
			long selfResult = rightTask.compute();
			return selfResult+ leftTask.join();
		}else {
			
			//single thread based
			long sum=0;
			for(int i=start; i <= end ; i++) {
				sum+=numbers[i];
			}
			return sum;
		}
		
	}

}
