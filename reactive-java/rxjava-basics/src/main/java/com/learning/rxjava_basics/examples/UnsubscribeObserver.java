package com.learning.rxjava_basics.examples;

import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class UnsubscribeObserver {
public static void main(String[] args) {
	//using infinite async call
	@NonNull
	Observable<String> infiniteString = Observable.interval(1, TimeUnit.SECONDS).map(String::valueOf);
	
	@NonNull
	Disposable subscriber1Dispoable = infiniteString.subscribe(item -> System.out.println("subscriber 1 recieves message "+item));
	
	infiniteString.subscribe(item -> System.out.println("subscriber 2 recieves message "+item));
	
	//lets say after 4 second subscriber one do not want data
	ThreadUtil.sleep(4000);
	subscriber1Dispoable.dispose();
	
	ThreadUtil.sleep(3000);
}
}
