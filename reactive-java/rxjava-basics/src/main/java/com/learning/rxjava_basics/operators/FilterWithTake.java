package com.learning.rxjava_basics.operators;

import java.util.Arrays;

import io.reactivex.rxjava3.core.Observable;

public class FilterWithTake {
public static void main(String[] args) {
	Observable<Integer> data = Observable.fromIterable(Arrays.asList(1,2,3,4,4,5,5,6,6,6,7,8,9,9,10));

	data.distinct()
		.filter(number -> number < 7)
		.map(String::valueOf)
		.subscribe(number -> System.out.println("unique item found "+number));
	

	data.elementAt(22)	//maybe is returned meaning o or 1, if item is not ther then onsucces wont be called
	.subscribe(number -> System.out.println("item at index found "+number));
}
}
