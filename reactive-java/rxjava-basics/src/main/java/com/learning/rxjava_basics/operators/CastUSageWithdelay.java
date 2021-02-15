package com.learning.rxjava_basics.operators;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.core.Observable;

public class CastUSageWithdelay {
public static void main(String[] args) {
	System.out.println("main started");
	Observable<Number> data = Observable.fromIterable(Arrays.asList(1,2,3,4,4,5,5,6,6,6,7,8,9,9,10));

	//cast can e used to type cast element one by one into another subclass
	data.delay(10, TimeUnit.SECONDS)//pass items one by one, better to keep it after distinct as now items will be very slow
		.distinct()
		.cast(Integer.class)//transform number of integer, we know the item is of interger type
		.map(String::valueOf)
		.subscribe(number -> System.out.println("unique item found "+number));
	
	//we know delay will make it non blocking/async and hence main thread shud wait so taht daemon thread do not die
	ThreadUtil.sleep(11000);
}
}
