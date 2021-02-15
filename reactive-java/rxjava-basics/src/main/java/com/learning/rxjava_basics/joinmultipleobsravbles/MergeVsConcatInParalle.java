package com.learning.rxjava_basics.joinmultipleobsravbles;

import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.examples.ChannelUtil;
import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class MergeVsConcatInParalle {
public static void main(String[] args) {
	Observable<@NonNull String> ob1Take = Observable.interval(1, TimeUnit.SECONDS)
			.map(number -> "Observer1-" + number).take(5);

	Observable<@NonNull String> ob2Take = Observable.interval(500, TimeUnit.MILLISECONDS)
			.map(number -> "Observer2-" + number).take(5);
	
	//sequnce will be random
	@NonNull
	//Observable<@NonNull  String> mergeWith = ob1Take.mergeWith(ob2Take);
	Observable<@NonNull  String> mergeWith = ob1Take.concatWith(ob2Take);
	
	mergeWith.subscribe(ChannelUtil.dataChannel,ChannelUtil.errorChannel,ChannelUtil.completeChannel)
	;
	
	ThreadUtil.sleep(7000);
}
}
