package com.learning.rxjava_basics.caching;

import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.core.Observable;

public class CachingForConnectableObservable {
	public static void main(String[] args) {
		//conecatable flux wont evenw ait for observer and starts emitting data
		System.out.println("===================");
		hotStreammReplayable();
	}

	private static void hotStreammReplayable() {
		Observable< Long> connectableObservable = Observable.interval(1,TimeUnit.SECONDS)
																.take(7)
																//easier to implement
																.cache();
		
		//we know it is hot and starts emiting data without even subscriber
		//if subsribes subscribe late than old data will be lost
		
		ThreadUtil.sleep(3000);
		//we will losse the data
		connectableObservable
		.subscribe(item -> System.out.println("observer 1 recieves "+item));
		
		ThreadUtil.sleep(2000);
		
		connectableObservable.subscribe(item -> System.out.println("observer 2 recieves "+item));
		
		ThreadUtil.sleep(3000);
			
	}

}
