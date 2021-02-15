package com.learning.rxjava_basics.paralleization;

import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ComputationSchedulerOnSingleThread {
	public static void main(String[] args) {
		tryConcurrencyInSingleObserver();
		System.out.println("===========");
		useFlatMapToRunConccurentWithSingleObservre();
	}

	private static void useFlatMapToRunConccurentWithSingleObservre() {
		Observable<String> names = Observable.interval(1, TimeUnit.SECONDS)
							    .take(5)
								.map(number -> "number-" + number)
								.flatMap(e -> Observable.just(e).subscribeOn(Schedulers.computation()))
								;
		
		names.subscribe(item -> System.out
				.println("item recieved " + item + " from thread " + Thread.currentThread().getName()));
		
		ThreadUtil.sleep(6000);
								
	}

	private static void tryConcurrencyInSingleObserver() {
		Observable<String> names = Observable.interval(1, TimeUnit.SECONDS)
				.take(5)
				.map(number -> "number-" + number)
				// .just("sita-ram","uma-mahesh","radhe-shyam","lekshmi-narayan")
				.subscribeOn(Schedulers.computation());

		// we know even tough multiple thread pools are assigned
		// scince observer is single it can not use more than one thread and hence runs
		// sequentially
		names.subscribe(item -> System.out
				.println("item recieved " + item + " from thread " + Thread.currentThread().getName()));

		ThreadUtil.sleep(6000);
	}
}
