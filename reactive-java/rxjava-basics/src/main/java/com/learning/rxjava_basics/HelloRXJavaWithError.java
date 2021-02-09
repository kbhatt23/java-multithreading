package com.learning.rxjava_basics;

import java.util.Random;

import io.reactivex.rxjava3.core.Observable;

public class HelloRXJavaWithError {
public static void main(String[] args) {
	//data source -> like publisher in case of project reactor
	
	Observable<String> dataSource = Observable.create(emitter -> {
		emitter.onNext("jai shree ram");
		emitter.onNext("jai radhe shyam");
		boolean isSuccess = false;
		if(isSuccess)
			emitter.onComplete();
		else
			emitter.onError(new RuntimeException("unable to fetch all content"));
	});
	
	dataSource.subscribe(message -> System.out.println("Subscriber 1 recieves message "+message) , 
				error -> System.out.println("Subscriber 1 error occurred in data source with message "+error),
				() -> System.out.println("Subscriber 1 completed all tasks")
			);
	dataSource
	.subscribe(message -> System.out.println("Subscriber 2 recieves message "+message), 
			error -> System.out.println("Subscriber 2 error occurred in data source with message "+error),
			() -> System.out.println("Subscriber 2 completed all tasks"));
}
}
