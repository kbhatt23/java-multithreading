package com.learning.rxjava_basics.operators;

import io.reactivex.rxjava3.core.Observable;

public class TakevsWhileSuppresers {
public static void main(String[] args) {
	Observable<Integer> data = Observable.just(1,2,3,4,5,6,7,8,9);
	//take means till copunt is reached takes it and ignore remainign
	data.take(4)
		.subscribe(result -> System.out.println("take found "+result));
	System.out.println("=================");
	//skip is until cpount si reached it ignores and process remining if any
	data.skip(4)
	.subscribe(result -> System.out.println("skip found "+result));
	
	System.out.println("=================");
	
	//codmnitional
	data.takeWhile(item -> item < 5).subscribe(result -> System.out.println("takeWhile found "+result));
	
System.out.println("=================");
	
	//codmnitional
	data.takeWhile(item -> item > 5).subscribe(result -> System.out.println("takeWhile found "+result));
	
	System.out.println("=================");
	data.skipWhile(item -> item > 5).subscribe(result -> System.out.println("skipWhile found "+result));
}
}
