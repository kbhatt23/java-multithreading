package com.learning.multithreading.parallelprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ArrayListSpliteratorSumParallelTest {
	ArrayListSpliteratorSumParallel obj = new ArrayListSpliteratorSumParallel();
	private static List<Integer> numbers = IntStream.range(1, 10000000).boxed().collect(Collectors.toList());
	
	private static List<Integer> numbersLinkedList = IntStream.range(1, 10000000).boxed().collect(Collectors.toCollection(LinkedList::new));

	@RepeatedTest(value = 7)
	//@ParameterizedTest
	//@ValueSource(booleans = true)
	void calculateSumParallel() {
		obj.calculateSum(numbers, true);
	}
	
	@RepeatedTest(value = 7)
	//@ParameterizedTest
	//@ValueSource(booleans = false)
	void calculateSumSequential() {
		obj.calculateSum(numbers, false);
	}
	
	@RepeatedTest(value = 7)
	//@ParameterizedTest
	//@ValueSource(booleans = false)
	void calculateSumSequentialLinkeList() {
		obj.calculateSum(numbersLinkedList, false);
	}
	@RepeatedTest(value = 7)
	//@ParameterizedTest
	//@ValueSource(booleans = false)
	void calculateSumParallelLinkeList() {
		obj.calculateSum(numbersLinkedList, true);
	}
	
	

}
