package com.learning.multithreading.parallelprogramming;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class REduceDangerInParalle {
	public static void main(String[] args) {

		List<Integer> numbers = IntStream.rangeClosed(1, 8).boxed().collect(Collectors.toList());

		// sum using sequential
		// identity of sum shud be a value when when addeed to any number x gives result
		// as x, htne it is 0
		int sumSequntially = numbers.stream().reduce(0, Integer::sum);

		System.out.println(sumSequntially);

		int sumParallely = numbers.stream().parallel().reduce(0, Integer::sum);

		System.out.println(sumParallely);

		// danger of wrong identity
		int wrongidentitySequentialSum = numbers.stream().reduce(2, Integer::sum);
		System.out.println(wrongidentitySequentialSum);

		// danger of wrong identity
		int wrongidentityParallelSum = numbers.stream().parallel().reduce(2, Integer::sum);
		System.out.println(wrongidentityParallelSum);
	}
}
