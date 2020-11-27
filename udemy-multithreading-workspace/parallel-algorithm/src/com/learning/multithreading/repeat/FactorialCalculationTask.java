package com.learning.multithreading.repeat;

import java.util.concurrent.RecursiveTask;

import com.learning.multithreading.util.ParallelUtil;

public class FactorialCalculationTask extends RecursiveTask<Integer>{
	private static final long serialVersionUID = 8376232644099783100L;
	private int[] numbers;
	private int startIndex;
	private int endIndex;
	
	public FactorialCalculationTask(int[] numbers, int startIndex, int endIndex) {
		this.numbers = numbers;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	@Override
	protected Integer compute() {

		if((endIndex-startIndex) > (numbers.length/ParallelUtil.PROCESSORS_SIZE)) {
			//break task
			int mid = (startIndex+endIndex)/2;
			FactorialCalculationTask task1 = new FactorialCalculationTask(numbers, startIndex, mid);
			task1.fork();
			
			FactorialCalculationTask task2 = new FactorialCalculationTask(numbers, mid+1, endIndex);
			int result2 = task2.compute();
			
			int result1 = task1.join();
			
			return result1 > result1 ? result2:  result1;
			
			
		}else {
			//find min sequentially
			int min = numbers[startIndex]
					;
			for(int i=startIndex ; i <= endIndex ; i++) {
				if(numbers[i] < min)
					min=numbers[i];
			}
			return min;
		}
	}

}
