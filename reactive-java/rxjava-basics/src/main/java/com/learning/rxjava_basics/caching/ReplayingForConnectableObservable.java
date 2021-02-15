package com.learning.rxjava_basics.caching;

import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

public class ReplayingForConnectableObservable {
	public static void main(String[] args) {
		//conecatable flux wont evenw ait for observer and starts emitting data
		hotStream();
		System.out.println("===================");
		hotStreammReplayable();
	}

	private static void hotStreammReplayable() {
		ConnectableObservable< Long> connectableObservable = Observable.interval(1,TimeUnit.SECONDS)
																.take(7)
																//.replay()	//replay the elemetns from beginint
																.replay(2) //replay the data from 2 items before the currnet item only , before that other old elements are ignored
																;																
		//returns disposable whihc can be used by observer to unsubscribe;
		@NonNull
		Observable<Long> autoConnect = connectableObservable.autoConnect();
		
		//we know it is hot and starts emiting data without even subscriber
		//if subsribes subscribe late than old data will be lost
		
		ThreadUtil.sleep(3000);
		//we will losse the data
		autoConnect.subscribe(item -> System.out.println("observer 1 recieves "+item));
		
		ThreadUtil.sleep(2000);
		
		autoConnect.subscribe(item -> System.out.println("observer 2 recieves "+item));
		
		ThreadUtil.sleep(3000);
			
	}

	private static void hotStream() {
		ConnectableObservable< Long> connectableObservable = Observable.interval(1,TimeUnit.SECONDS)
																.take(7)
																.publish();
		
		//returns disposable whihc can be used by observer to unsubscribe;
		connectableObservable.connect();
		
		//we know it is hot and starts emiting data without even subscriber
		//if subsribes subscribe late than old data will be lost
		
		ThreadUtil.sleep(3000);
		//we will losse the data
		connectableObservable.subscribe(item -> System.out.println("observer 1 recieves "+item));
		
		ThreadUtil.sleep(2000);
		
		connectableObservable.subscribe(item -> System.out.println("observer 2 recieves "+item));
		
		ThreadUtil.sleep(3000);
	}
}
