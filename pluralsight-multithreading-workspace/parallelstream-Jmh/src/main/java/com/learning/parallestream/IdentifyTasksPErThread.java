package com.learning.parallestream;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class IdentifyTasksPErThread {

	public static void main(String[] args) {
		//has to be conucurrent as stream is parallel
		Map<String, Integer> threadMapCount = new ConcurrentHashMap<>();
		int numberCount= 100000;
		
		int sum = IntStream.rangeClosed(1, numberCount)
				.parallel()
				//.peek(number -> System.out.println("task getting executed by thread "+Thread.currentThread().getName()))
				.peek(number -> threadMapCount.put(Thread.currentThread().getName(), 
						threadMapCount.getOrDefault(Thread.currentThread().getName(), 0)+1
						)) 
				.sum();
		threadMapCount.forEach((thread,count) -> System.out.println(thread + " -> "+count));
		System.out.println("final sum "+sum);
	}
}
