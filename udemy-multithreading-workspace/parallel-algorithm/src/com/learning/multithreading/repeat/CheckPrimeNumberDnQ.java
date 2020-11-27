package com.learning.multithreading.repeat;

import com.learning.multithreading.util.ParallelUtil;

public class CheckPrimeNumberDnQ {
	private static final int BATCH_SIZE = 1000;
	public static void main(String[] args) {
		int[] numbers = ParallelUtil.generateNumbersRandonly();
		validateSequentially(numbers);
		validateDNQ(numbers);
		
	}

	private static void validateDNQ(int[] numbers) {
		long start= System.nanoTime();
		dnqValidateRecursively(numbers, 0, numbers.length-1);
		long end= System.nanoTime();
		System.out.println("DNQ total time taken "+(end-start));
	}
	
	private static void dnqValidateRecursively(int[] numbers, int start, int end) {
		int size = end-start;
		if(size > BATCH_SIZE) {
			int mid = (start+end)/2;
			dnqValidateRecursively(numbers, start, mid);
			dnqValidateRecursively(numbers, mid+1, end);
		}else {
			for(int i=start ; i<=end;i++) {
				int number = numbers[i];
				boolean isPrimeNumber = checkPrimeNumber(number);
				if(isPrimeNumber)
					System.out.println(number+" is a prime number");
				else
					System.out.println(number+" is not a prime number");
			}
		}
		
	}

	public static boolean checkPrimeNumber(int number) {
		boolean isPrime = true;
		int sqrt = (int) Math.sqrt(number) + 1;
		for (int i = 2; i < sqrt; i++) {
			if (number % i == 0)
				isPrime = false;
		}
		return isPrime;
	}
	public static void validateSequentially(int[] numbers) {
		long start= System.nanoTime();
		for(int number: numbers) {
			boolean isPrimeNumber = checkPrimeNumber(number);
			if(isPrimeNumber)
				System.out.println(number+" is a prime number");
			else
				System.out.println(number+" is not a prime number");
		}
		long end= System.nanoTime();
		System.out.println("sequential total time taken "+(end-start));
	}
}
