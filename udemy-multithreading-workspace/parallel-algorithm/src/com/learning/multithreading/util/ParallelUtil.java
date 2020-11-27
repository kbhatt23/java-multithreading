package com.learning.multithreading.util;

import java.util.Arrays;
import java.util.Random;

public class ParallelUtil {
	public static final int PROCESSORS_SIZE = Runtime.getRuntime().availableProcessors();

	//we can divide task to smaller arrays 
	//size of array is size of processor
		public static int[][] splitParts(int[] numbers) {
			int[][] parts = new int[PROCESSORS_SIZE][];
			int sizeNumbers = numbers.length;
			int sizePerPart = sizeNumbers / PROCESSORS_SIZE;
			int sizeLastPart = sizePerPart + sizeNumbers % PROCESSORS_SIZE;
			int numberIndex = 0;
			for (int i = 0; i < PROCESSORS_SIZE; i++) {
				int part[] = (i == PROCESSORS_SIZE - 1) ? new int[sizeLastPart] : new int[sizePerPart];
				for (int j = 0; j < part.length; j++, numberIndex++) {
					part[j] = numbers[numberIndex];
				}
				parts[i] = part;
			}
			return parts;
		}
		
		public static boolean isArraySorted(int[] numbers) {
			int[] newCopy = new int[numbers.length];
			System.arraycopy(numbers, 0, newCopy, 0, numbers.length);
			Arrays.sort(newCopy);
			return Arrays.equals(numbers, newCopy);
		}

		public static int[] generateNumbersRandonly() {
			int[] numbers = new int[100000];
			Random random = new Random();
			for(int i=0;i<numbers.length;i++) {
				numbers[i]=random.nextInt(10000);
			}
			return numbers;
		}
}
