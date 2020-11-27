package com.learning.multithreading.advacnedalgos;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ParalleMergeSort {

	public static void main(String[] args) {
		int[] numbers = new int[] { 4, 3, 2, 6, 7, 822, 2, 1, 6, 9 };
		mergeSort(numbers, 0, numbers.length - 1);
		String collect = Arrays.stream(numbers).mapToObj(String::valueOf).collect(Collectors.joining(",", "[", "]"));
		System.out.println(collect);
	}

	public static void mergeSort(int[] numbers, int startIndex, int endIndex) {
		if (endIndex > startIndex) {
			int mid = (startIndex + endIndex) / 2;
			mergeSort(numbers, startIndex, mid);
			mergeSort(numbers, mid + 1, endIndex);
			merge(numbers, startIndex, mid, endIndex);
		}
	}

	public static void merge(int[] numbers, int startIndex, int mid, int endIndex) {

		int firstHAlfSize = mid - startIndex + 1;
		int secondHalfSize = endIndex - mid;
		int[] firstArray = new int[firstHAlfSize];
		int[] secondArray = new int[secondHalfSize];
		for (int i = 0; i < firstHAlfSize; i++) {
			firstArray[i] = numbers[i + startIndex];
		}
		for (int i = 0; i < secondHalfSize; i++) {
			secondArray[i] = numbers[mid + 1 + i];
		}
		int i = 0;
		int j = 0;
		int k = startIndex;
		while (i < firstHAlfSize && j < secondHalfSize) {
			if (firstArray[i] <= secondArray[j]) {
				numbers[k] = firstArray[i];
				i++;
			} else {
				numbers[k] = secondArray[j];
				j++;
			}
			k++;
		}

		while (i < firstHAlfSize) {
			numbers[k] = firstArray[i];
			i++;
			k++;
		}

		while (j < secondHalfSize) {
			numbers[k] = secondArray[j];
			j++;
			k++;
		}
	}
}
