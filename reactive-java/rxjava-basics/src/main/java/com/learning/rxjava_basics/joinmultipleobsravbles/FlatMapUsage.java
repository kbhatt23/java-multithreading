package com.learning.rxjava_basics.joinmultipleobsravbles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.core.Observable;

public class FlatMapUsage {
public static void main(String[] args) {
	Observable<Long> ob1 = Observable.interval(1, TimeUnit.SECONDS)
										.take(5)
										.flatMap(FlatMapUsage::manipulator)
										;
	ob1.subscribe(res -> System.out.println("recieved message "+res));
	
	ThreadUtil.sleep(6000);
}

public static Observable<Long> manipulator(long times){
	List<Long> items = new ArrayList<>();
	for(int i=0 ; i <times ; i++) {
		items.add(times);
	}
	return Observable.fromIterable(items);
}
}
