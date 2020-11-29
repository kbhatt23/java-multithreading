package com.learning.multithreading.parallelprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class REduceDangerInParalle2 {
	public static void main(String[] args) {

		List<Integer> numbers = IntStream.rangeClosed(1, 4).boxed().collect(Collectors.toList());

		// multiply using sequential
		// identity of multiple shud be a value when when multiplied to any number x gives result
		// as x, hence it is 1
		int multipleSequntially = numbers.stream().reduce(1, (result,item) -> result*item);
//24
		System.out.println(multipleSequntially);

		int multipleParallely = numbers.stream().parallel().reduce(1, (result,item) -> result*item);

//24		
		System.out.println(multipleParallely);

		// danger of wrong identity
		int wrongidentitySequentialMultiple = numbers.stream().reduce(2, (result,item) -> result*item);
//48
		System.out.println(wrongidentitySequentialMultiple);

		// danger of wrong identity
		int wrongidentityParallelMultiple = numbers.stream().parallel().reduce(2, (result,item) -> result*item);
//384		
		System.out.println(wrongidentityParallelMultiple);
		
		System.out.println("=======================");
		//empty arrayList in stream or paralle strema always returns identityvalue
		System.out.println(new ArrayList<Integer>().stream().reduce(3, (result,item) -> result*item));
		System.out.println(new ArrayList<Integer>().stream().parallel().reduce(3, (result,item) -> result*item));
		
		System.out.println("======self==============");
		List<Integer> items  = IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList());
		System.out.println(items.stream().reduce(3,Integer::sum));
		System.out.println(items.stream().parallel().reduce(3,Integer::sum));
		
		
	}
}
