package com.learning.multithreading.repeat;

import com.learning.multithreading.util.ParallelUtil;

public class FindMinDNQ {

	public static void main(String[] args) {
		int[] numbers = ParallelUtil.generateNumbersRandonly();
		System.out.println(findMinRecursivley(numbers, 0, numbers.length-1));
	}

	public static int findMinRecursivley(int[] numbers, int start,int end) {
		int size = end-start;
		if(size == 1) {
			return numbers[start];
		}
		if(size == 2) {
			return numbers[start] > numbers[end] ? numbers[end] : numbers[start];
		}
		
		int mid = (start+end)/2;
		int firstHalfSmaller = findMinRecursivley(numbers, start, mid);
		
		int secondHalfSmaller = findMinRecursivley(numbers, mid+1, end);
		return firstHalfSmaller> secondHalfSmaller ? secondHalfSmaller: firstHalfSmaller;
	}
}
