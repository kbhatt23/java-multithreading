package com.learning.rxjava_basics.operators;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class FilterOperator {
	public static void main(String[] args) {
		@NonNull
		Observable<Integer> data = Observable.just(99, 23, 33, 75,45,66,4);
		
		data
		.count()//return Observable<Long>
		.subscribe(result -> System.out.println("total item count is "+result));
		
		data.filter(item -> item > 40)//returns observable
			.subscribe(item -> System.out.println("item recieved "+item));
		
		;
		
		System.out.println("sorterd with filter");
		data.filter(item -> item > 40)//returns observable<Integer>
		.sorted()//return observable<Integer>
		.subscribe(item -> System.out.println("item recieved "+item));
	
	;
	}
}
