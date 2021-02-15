package com.learning.rxjava_basics.caching;

import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MultipleObservable {
public static void main(String[] args) {
	
	@NonNull
	Observable<Long> ob1 = Observable.interval(1, TimeUnit.SECONDS).take(5).subscribeOn(Schedulers.io());
	@NonNull
	Observable<Long> ob2 = Observable.interval(1, TimeUnit.SECONDS).skip(5).take(5).subscribeOn(Schedulers.io());
	
	Consumer<Long> c = item -> System.out.println("recieving item "+item+" from thread "+Thread.currentThread().getName());
	
	//by default it is sequential
	//meaning subsribe method will be cllockign if using default observable
	//iuf using schduler or manual created thread it become non blocking and hence ob2 can subscribe concurrnetly 
	ob1.subscribe(c);
	ob2.subscribe(c);
	
	ThreadUtil.sleep(11000);
}
}
