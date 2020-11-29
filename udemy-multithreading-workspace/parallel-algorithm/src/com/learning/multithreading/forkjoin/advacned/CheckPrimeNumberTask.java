package com.learning.multithreading.forkjoin.advacned;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class CheckPrimeNumberTask extends RecursiveAction{

	private static final long serialVersionUID = 8376232644099783100L;
	private int[] numbers;
	int startIndex;
	int endIndex;
	
	public CheckPrimeNumberTask(int[] numbers, int startIndex, int endIndex) {
		this.numbers = numbers;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}




	@Override
	protected void compute() {
		if((endIndex-startIndex-1) > 100) {
			//break it
			int mid = (endIndex+startIndex)/2;
			CheckPrimeNumberTask firstHalf = new CheckPrimeNumberTask(numbers, startIndex, mid);
			firstHalf.fork();
			
			CheckPrimeNumberTask secondHalf = new CheckPrimeNumberTask(numbers, mid, endIndex);
			secondHalf.compute();
			
			firstHalf.join();
			
		}else {
			//do the task
//			for(int i=startIndex;i<endIndex ; i++) {
//				CheckingPrimeNumbersForkJoin.checkPrimeNumber(numbers[i]);
//			}
			
			//funtional style
			Arrays.stream(numbers).skip(startIndex-0).limit(endIndex-startIndex).forEach(CheckingPrimeNumbersForkJoin::checkPrimeNumber);
			
		}
	}

}
