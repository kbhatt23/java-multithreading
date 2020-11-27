package com.learning.multithreading.basicalgos;

import java.util.stream.IntStream;

public class DivideAndConquerMin {
public static void main(String[] args) {
//	int[] numbers = new int[] {3,4,5,2,6,7,86,6,0,1,4,9};
	//int[] numbers = new int[] {3,4,5,2};
	int[] numbers = IntStream.rangeClosed(1, 10000).toArray();
	int findMinRecursively = findMinRecursively(numbers, 0, numbers.length-1);
	System.out.println(findMinRecursively);
}

public static int findMinRecursively(int[] numbers, int begin,int end) {
	
	if(end == begin) {
		return numbers[begin];
	}
	//this if condition is better for performance as it saves method stack to add to fiund 0 and 1 indices when only 2 items are present in array
	if(end == begin+1) {
		//only 2 items left
		return numbers[begin] > numbers[end] ? numbers[end]: numbers[begin];
	}
	else if(end > begin) {
		int mid = (end+begin)/2;
		int minFirstPart = findMinRecursively(numbers, begin, mid);
		int minSecondPart = findMinRecursively(numbers, mid+1, end);
		return minFirstPart > minSecondPart ? minSecondPart : minFirstPart;
	}
	return -1;
}
}
