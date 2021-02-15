package com.learning.rxjava_basics.paralleization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SubscribeOnvsObserveOn {
	public static void main(String[] args) {
		Observable<String> names = Observable.just("sita-ram","uma-mahesh","radhe-shyam","lekshmi-narayan");
		
		//subscribeonusage
		subscribeOnUsage(names);
		
		System.out.println("===========");
		observeOnUsage(names);
	}

	//subscribe on means we do not have flexibilty to have different scheduler usage on different part of pipeline
	//whatever was defined first will be considered for whole pipeline
	private static void subscribeOnUsage(Observable<String> names) {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);

		//colsest to data will take precenden and will be considered for whole pipleine
		names.subscribeOn(Schedulers.from(newFixedThreadPool))
			.map(str -> {
				System.out.println("Thread doing uppercase "+Thread.currentThread().getName());
				return str.toUpperCase();
				})
			.subscribeOn(Schedulers.computation())//hoping other part of pipelein will happen on different scheuler technique, but wont happen
			.map(str -> {
				System.out.println("Thread doing concatenation "+Thread.currentThread().getName());
				return "jai ".concat(str);
				})
			.subscribeOn(Schedulers.io())
			.subscribe(res -> System.out.println("final result "+res+" from thread "+Thread.currentThread().getName()));
		
		ThreadUtil.sleep(500);
		newFixedThreadPool.shutdown();
	}
	
	//we have flexibility to have differnet scheuling technique for different part of pipeleline
	private static void observeOnUsage(Observable<String> names) {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);

		//colsest to data will take precenden and will be considered for whole pipleine
		names.observeOn(Schedulers.from(newFixedThreadPool))
			.map(str -> {
				System.out.println("Thread doing uppercase "+Thread.currentThread().getName());
				return str.toUpperCase();
				})
			.observeOn(Schedulers.computation())//hoping other part of pipelein will happen on different scheuler technique, but wont happen
			.map(str -> {
				System.out.println("Thread doing concatenation "+Thread.currentThread().getName());
				return "jai ".concat(str);
				})
			.observeOn(Schedulers.io())
			.subscribe(res -> System.out.println("final result "+res+" from thread "+Thread.currentThread().getName()));
		
		ThreadUtil.sleep(500);
		newFixedThreadPool.shutdown();
	}
}
