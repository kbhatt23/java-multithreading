package com.learning.rxjava_basics.throttling;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.core.Observable;

public class WindowUsage {
public static void main(String[] args) {
	countBasedWindow();
	System.out.println("===============");
	timebasedWindow();
			  
}

private static void timebasedWindow() {

	Observable.interval(1, TimeUnit.SECONDS)//one element comes after every one sec
	.window(5)//will wait until buffer size is 5 then emit the observablle<observable>
	.flatMapSingle(observable -> observable.toList())
	.subscribe(arrayList -> System.out.println("Item recieved "+arrayList));
	
	ThreadUtil.sleep(20000);
}

private static void countBasedWindow() {
	Observable.range(1, 30)
			  .window(5) // obserbvble<observable>
			  .flatMapSingle(observable -> observable.toList()) // observable<list>
			  .subscribe(arrayList -> System.out.println("batch recieved "+arrayList))
	;
	
	//it do not support observable<collection> and hence this one is not needed
	System.out.println("======set not needed==========");
;
}
}
