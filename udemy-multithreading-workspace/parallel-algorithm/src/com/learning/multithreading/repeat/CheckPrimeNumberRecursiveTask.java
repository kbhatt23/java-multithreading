package com.learning.multithreading.repeat;

import java.util.concurrent.RecursiveAction;

public class CheckPrimeNumberRecursiveTask extends RecursiveAction {
	private int start;
	private int end;
	private int[] numbers;
	private static final int BATCH_SIZE = 1000;

	public CheckPrimeNumberRecursiveTask(int start, int end, int[] numbers) {
		this.start = start;
		this.end = end;
		this.numbers = numbers;
	}

	@Override
	protected void compute() {

		int size = end - start;
		if (size > BATCH_SIZE) {
			int mid = (start + end) / 2;
			CheckPrimeNumberRecursiveTask firstHalf = new CheckPrimeNumberRecursiveTask(start, mid, numbers);
			firstHalf.fork();

			CheckPrimeNumberRecursiveTask selfHalf = new CheckPrimeNumberRecursiveTask(mid + 1, end, numbers);
			selfHalf.compute();
			firstHalf.join();
		} else {
			for (int i = start; i <= end; i++) {
				int number = numbers[i];
				boolean isPrimeNumber = CheckPrimeNumberDnQ.checkPrimeNumber(number);
				if (isPrimeNumber)
					System.out.println(number + " is a prime number");
				else
					System.out.println(number + " is not a prime number");
			}
		}

	}

}
