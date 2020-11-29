package com.learning.multithreading.parallelprogramming;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class ArrayListSpliteratorSumParallel {

	
	public long calculateSum(List<Integer> numbers,boolean isParallel) {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();
		Stream<Integer> stream = isParallel ? numbers.parallelStream(): numbers.stream();
		long sum = (long)stream.mapToInt(i->i).sum();
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
		return sum;
	}
	
	public List<Integer> addNumber(List<Integer> numbers,boolean isParallel) {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();
		Stream<Integer> stream = isParallel ? numbers.parallelStream(): numbers.stream();
		List<Integer> addList = stream.map(i->i+1).collect(Collectors.toList());
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
		return addList;
	}
	
}
