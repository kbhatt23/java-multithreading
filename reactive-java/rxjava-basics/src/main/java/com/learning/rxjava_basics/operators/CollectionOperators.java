package com.learning.rxjava_basics.operators;

import io.reactivex.rxjava3.core.Observable;

public class CollectionOperators {
public static void main(String[] args) {
	Observable<Integer> data = Observable.just(1,2,3,4);
	//to list is alsoa  reuction
	//it wil reduec to single observable element that can be subscribed
	data.toList().subscribe(items -> System.out.println("reduced list "+items));
}
}
