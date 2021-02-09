package com.learning.rxjava_basics.examples;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class RandomStreams {
public static void main(String[] args) {
	usingRange(5);
	usingEmptyObservable();
	usingNeverObservable();
	usingError();
}

private static void usingRange(int range) {
	
	@NonNull
	Observable<String> dataSource = Observable.range(1, range)
			.map(String::valueOf)
;
	
	dataSource.subscribe(ChannelUtil.dataChannel, ChannelUtil.errorChannel,ChannelUtil.completeChannel);
	}


private static void usingEmptyObservable() {
	System.out.println("=============");
	//empty means no data and hence 0 onnext and direct one on complete
	@NonNull
	Observable<String> dataSource = Observable.empty();
;
	
	dataSource.subscribe(ChannelUtil.dataChannel, ChannelUtil.errorChannel,ChannelUtil.completeChannel);
	}

private static void usingNeverObservable() {
	System.out.println("=============");
	//never means 0 onnext, 0 oncomplete and 0 onerror
	@NonNull
	Observable<String> dataSource = Observable.never();
;
	
	dataSource.subscribe(ChannelUtil.dataChannel, ChannelUtil.errorChannel,ChannelUtil.completeChannel);
	}


private static void usingError() {
	System.out.println("=============");
	//error means 0 onnext are called and directly onerror is called wiot 0 oncomplete
	//this is used for unit testing, in case we are expecting an error observable
	//same goes with emmptyobervable and none observable
	@NonNull
	Observable<String> dataSource = Observable.error(RuntimeException::new);
;
	
	dataSource.subscribe(ChannelUtil.dataChannel, ChannelUtil.errorChannel,ChannelUtil.completeChannel);
	}
}
