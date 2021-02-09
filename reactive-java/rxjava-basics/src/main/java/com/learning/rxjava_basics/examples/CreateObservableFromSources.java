package com.learning.rxjava_basics.examples;

import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class CreateObservableFromSources {
public static void main(String[] args) {
	usingCreate();
	usingJust();
	usingIterable();
}

private static void usingJust() {
	System.out.println("==========usingJust=============");
	@NonNull
	Observable<String> just = Observable.just("sita ram", "radhe shyam");
	just.subscribe(ChannelUtil.dataChannel, ChannelUtil.errorChannel,ChannelUtil.completeChannel);
}

private static void usingIterable() {
	System.out.println("==========usingIterable=============");
	List<String> messages = Arrays.asList("sita ram", "radhe shyam");
	
	Observable<String> just = Observable.fromIterable(messages);
	just.subscribe(ChannelUtil.dataChannel, ChannelUtil.errorChannel,ChannelUtil.completeChannel);
}

private static void usingCreate() {
	System.out.println("==========usingCreate=============");
	Observable<String> observable = 
			Observable.create(emitter -> {
				//manual setting the data
				emitter.onNext("sita ram");
				emitter.onNext("radhe shyam");
				emitter.onComplete(); 
			});
	
	//without subscribe nothing happens -> cold streams
	
	observable.subscribe(ChannelUtil.dataChannel, ChannelUtil.errorChannel,ChannelUtil.completeChannel);
}
}
