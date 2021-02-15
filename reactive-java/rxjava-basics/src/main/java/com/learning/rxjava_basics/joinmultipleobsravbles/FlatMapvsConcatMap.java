package com.learning.rxjava_basics.joinmultipleobsravbles;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class FlatMapvsConcatMap {
	public static void main(String[] args) {
		Observable<String> items = Observable.just("sita","ram","radhe","shyam");
	
		items.flatMap(
				entry -> extracted(entry)
				)
		.subscribe(charFound -> System.out.println("found char "+charFound))
		;
	}

	//split the items into char array and return observable for each word
	private static @NonNull Observable<String> extracted( @NonNull String entry) {
		return Observable.fromArray(entry.split(""));
	}
}
