package com.learning.multithreading.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PAginationUsingStream {
	public static void main(String[] args) {
		List<Integer> items = IntStream.rangeClosed(1, 16).collect(ArrayList::new, List::add, List::addAll);
		
		System.out.println("actual items "+items);
		
		System.out.println(paginate(4, 3, items));
	}
	
	public static <T> List<T> paginate(int pageNumber, int size,List<T> items){
		int skipElemnets = size*pageNumber;
		
		return items.stream()
			 .skip(skipElemnets)
			 .limit(size)
			 .collect(Collectors.toList());
	}

}
