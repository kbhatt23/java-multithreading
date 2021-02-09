package com.learning.rxjava_basics;

import org.reactivestreams.Publisher;

import io.reactivex.rxjava3.core.Observable;

public class HelloRXJava {
public static void main(String[] args) {
	//data source -> like publisher in case of project reactor
	
	Observable<String> dataSource = Observable.create(emitter -> {
		emitter.onNext("jai shree ram");
		emitter.onNext("jai radhe shyam");
		emitter.onComplete();
	});
	
	dataSource.subscribe(message -> System.out.println("Subscriber 1 recieves message "+message));
	dataSource.subscribe(message -> System.out.println("Subscriber 2 recieves message "+message));
}
}
