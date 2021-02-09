package com.learning.rxjava_basics.examples;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

public class HotVsColdObservables {

	public static void main(String[] args) {
		//coldObservables();
		hotObservables();
	}

	private static void coldObservables() {

		@NonNull
		Observable<String> coldObservable = Observable.interval(1, TimeUnit.SECONDS)
		.map(String::valueOf)
		;
		//default is cold and synchrnous/blocking except interval method
		//however interval stream ais async in nature and hence if main thread dies it dies too
		
		coldObservable.subscribe(item -> System.out.println("subscriber 1 recieves "+item), ChannelUtil.errorChannel,ChannelUtil.completeChannel);

		ThreadUtil.sleep(2000);
		//cold means new subscriber wont get the old data
		//since stream is infinite we tought we might get data just after spent time interval
		//but in cold it starts from begining
		coldObservable.subscribe(item -> System.out.println("subscriber 2 recieves "+item), ChannelUtil.errorChannel,ChannelUtil.completeChannel);
	
		ThreadUtil.sleep(4000);
	}
	
	private static void hotObservables() {

		@NonNull
		ConnectableObservable<@NonNull Long> connectableObservable = Observable.interval(1, TimeUnit.SECONDS)
				.publish();
		
		connectableObservable.connect();
			
		//default is cold and synchrnous/blocking except interval method
		//however interval stream ais async in nature and hence if main thread dies it dies too
		
		connectableObservable
		.map(String::valueOf)
		.subscribe(item -> System.out.println("subscriber 1 recieves "+item), ChannelUtil.errorChannel,ChannelUtil.completeChannel);

		ThreadUtil.sleep(2000);
		//cold means new subscriber wont get the old data
		//since stream is infinite we tought we might get data just after spent time interval
		//but in cold it starts from begining
		connectableObservable.
		map(String::valueOf)
		.subscribe(item -> System.out.println("subscriber 2 recieves "+item), ChannelUtil.errorChannel,ChannelUtil.completeChannel);
	
		ThreadUtil.sleep(4000);
	}
}
