package com.learning.rxjava_basics.throttling;

import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.examples.ChannelUtil;
import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.core.Observable;

//throttling means based on logic we can ignore few items sent by observable/data source

public class ThrottlingUsage {
	public static void main(String[] args) {
		Observable<String> data = Observable.create(emitter -> {
			emitter.onNext("jai shri hari");
			
			ThreadUtil.sleep(500);
			emitter.onNext("ignore this one");
			
			ThreadUtil.sleep(400);
			emitter.onNext("ignore this one also");
			
			emitter.onNext("jai lekshmi narayana");
			
			ThreadUtil.sleep(300);
			emitter.onNext("naklee1");
			ThreadUtil.sleep(300);
			emitter.onNext("naklee2");
			ThreadUtil.sleep(300);
			emitter.onNext("naklee3");
			ThreadUtil.sleep(300);
			emitter.onNext("naklee4");
			
			emitter.onNext("kya pata");
			
			emitter.onComplete();
		});
		
		//without throlttling
		//data.subscribe(ChannelUtil.dataChannel, ChannelUtil.errorChannel,ChannelUtil.completeChannel);
		
		//throtlle first
		//picks first and then sleep for 1 second and whatever comes take that sleep again for 1 sec and so on...
//		data.throttleFirst(1, TimeUnit.SECONDS)
//		.subscribe(ChannelUtil.dataChannel, ChannelUtil.errorChannel,ChannelUtil.completeChannel);
		
		//start the session and for every 1 seocnd whatever is the last element send that to observer
//		data.throttleLast(1, TimeUnit.SECONDS)
//		.subscribe(ChannelUtil.dataChannel, ChannelUtil.errorChannel,ChannelUtil.completeChannel);
		
		data.throttleWithTimeout(1, TimeUnit.SECONDS)
		.subscribe(ChannelUtil.dataChannel, ChannelUtil.errorChannel,ChannelUtil.completeChannel);
		
	}
}
