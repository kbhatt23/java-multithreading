package com.learning.rxjava_basics.throttling;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.core.Observable;

public class BufferUsage {
public static void main(String[] args) {
	countBasedBuffering();
	System.out.println("===============");
	timebasedBuffering();
			  
}

private static void timebasedBuffering() {

	Observable.interval(1, TimeUnit.SECONDS)//one element comes after every one sec
		.buffer(5, TimeUnit.SECONDS)//we wait for 5 seocnd and keep on storing data in buffer then pusshes to subscriber at once
		.subscribe(arrayList -> System.out.println("Item recieved "+arrayList));
	
	ThreadUtil.sleep(20000);
}

private static void countBasedBuffering() {
	Observable.range(1, 30)
			  .buffer(5)
			  .subscribe(arrayList -> System.out.println("batch recieved "+arrayList))
	;
	System.out.println("======set==========");
	//override batch D.S to hashset
	Observable.range(1, 30)
	  .buffer(5,HashSet::new)
	  .subscribe(arrayList -> System.out.println("batch recieved "+arrayList))
;
}
}
