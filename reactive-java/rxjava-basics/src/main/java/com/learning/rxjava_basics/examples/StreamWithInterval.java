package com.learning.rxjava_basics.examples;

import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class StreamWithInterval {
public static void main(String[] args) {
	System.out.println("task started");

	//passes incremental long values after every one second
	@NonNull
	Observable<String> interval = Observable.interval(1, TimeUnit.SECONDS)//observable<long>
		.map(String::valueOf)//Observable<String>
	;
	//subscription wont work
	//as interval is daemon multithreaded and since main thread dies observer die too
	interval.subscribe(ChannelUtil.dataChannel, ChannelUtil.errorChannel,ChannelUtil.completeChannel);
	
	//after 5 seconds main thread dies and hence the infinite stream provider dies too
	ThreadUtil.sleep(5000);
}
}
