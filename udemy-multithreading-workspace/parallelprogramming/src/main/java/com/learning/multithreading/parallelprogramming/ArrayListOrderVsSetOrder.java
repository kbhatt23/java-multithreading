package com.learning.multithreading.parallelprogramming;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayListOrderVsSetOrder {
	public static void main(String[] args) {
		List<Integer> numbersList = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
		System.out.println(numbersList);
		// as we know spliterator is ordered the order shud be mainatined even in
		// transformed collection
		manipulateArrayLis(numbersList);

		Set<Integer> numbersSet = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toSet());
		manipulateSet(numbersSet);
	}

	private static void manipulateSet(Set<Integer> numbersSet) {
		Set<Integer> transformedSet = numbersSet.stream().parallel().map(i -> i * 2).collect(Collectors.toSet());
	}

	public static void manipulateArrayLis(List<Integer> numbersList) {
		List<Integer> transformed = numbersList.stream().parallel().map(i -> i * 2).collect(Collectors.toList());
		//System.out.println(transformed);
		
		//for each wont be ordered , but foreachordered will be
		transformed.stream()
					.parallel()
					.forEachOrdered(System.out::println);

		for (int i = 0; i < numbersList.size(); i++) {
			int numberOriginal = numbersList.get(0);
			int numberTransformed = transformed.get(0);
			if (numberOriginal * 2 != numberTransformed) {
				throw new RuntimeException("Order is not amintained");
			}
		}
	}
}
