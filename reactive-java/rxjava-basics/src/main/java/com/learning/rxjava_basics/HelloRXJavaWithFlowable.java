package com.learning.rxjava_basics;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;

public class HelloRXJavaWithFlowable {
public static void main(String[] args) {
	//data source -> like publisher in case of project reactor
	
	Flowable<String> dataSource = Flowable.create(emitter -> {
		emitter.onNext("jai shree ram");
		emitter.onNext("jai radhe shyam");
		emitter.onComplete();
	},
			BackpressureStrategy.LATEST
			);
	
	dataSource.subscribe(message -> System.out.println("Subscriber 1 recieves message "+message));
	dataSource.subscribe(message -> System.out.println("Subscriber 2 recieves message "+message));
}
}
