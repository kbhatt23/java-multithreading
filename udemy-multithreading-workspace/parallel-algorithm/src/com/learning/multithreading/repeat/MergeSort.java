package com.learning.multithreading.repeat;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;

import com.learning.multithreading.util.ParallelUtil;

public class MergeSort {
	public static void main(String[] args) {
		int[] numbers = ParallelUtil.generateNumbersRandonly();
		mergeSort(numbers, 0, numbers.length-1);
		String collect = Arrays.stream(numbers).mapToObj(String::valueOf).collect(Collectors.joining(",","[","]"));
		System.out.println(collect);
		System.out.println("is array sorted "+ParallelUtil.isArraySorted(numbers));
		
		runUsingForkJoin(numbers);
	}
	
	public static void runUsingForkJoin(int[] numbers) {
		MergeSortForkJoinTask mergeSortForkJoinTask = new MergeSortForkJoinTask(0, numbers.length-1, numbers);
		ForkJoinPool pool = new ForkJoinPool(ParallelUtil.PROCESSORS_SIZE+1);
		ForkJoinTask<Void> submit = pool.submit(mergeSortForkJoinTask);
		submit.join();
		String collect = Arrays.stream(numbers).mapToObj(String::valueOf).collect(Collectors.joining(",","[","]"));
		System.out.println(collect);
		System.out.println("is array sorted "+ParallelUtil.isArraySorted(numbers));
	}

	public static void mergeSort(int[] numbers, int start, int end) {
		// this condition is met until there are single item
		if (end > start) {
			// for 2 sized array or more it comes inside
			int mid = (end + start) / 2;
			mergeSort(numbers, start, mid);
			mergeSort(numbers, mid + 1, end);
			merge(numbers, start, end, mid);
		}
	}

	public static void merge(int[] numbers, int start, int end, int mid) {

		int sizeFirstHalf = mid - start + 1;
		int sizeSecondHalf = end - mid;

		int[] firstHalfArray = new int[sizeFirstHalf];
		int[] secondHalfArray = new int[sizeSecondHalf];

		for (int i = 0; i < sizeFirstHalf; i++) {
			firstHalfArray[i] = numbers[start + i];
		}

		for (int i = 0; i < sizeSecondHalf; i++) {
			secondHalfArray[i] = numbers[mid + i + 1];
		}

		int i = 0;
		int j = 0;
		int k = start;
		while (i < sizeFirstHalf && j < sizeSecondHalf) {
			if (firstHalfArray[i] <= secondHalfArray[j]) {
				numbers[k] = firstHalfArray[i];
				i++;
			} else {
				numbers[k] = secondHalfArray[j];
				j++;
			}
			k++;
		}

		while (i < sizeFirstHalf) {
			numbers[k] = firstHalfArray[i];
			i++;
			k++;
		}
		while (i < sizeSecondHalf) {
			numbers[k] = secondHalfArray[j];
			j++;
			k++;
		}
	}
}
