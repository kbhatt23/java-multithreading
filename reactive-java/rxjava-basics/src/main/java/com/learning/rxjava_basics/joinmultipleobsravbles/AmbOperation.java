package com.learning.rxjava_basics.joinmultipleobsravbles;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.examples.ChannelUtil;
import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class AmbOperation {
	public static void main(String[] args) {
		//amb operator is like any of method of completabelfutre
		//whihc complestes first is returned to the subscriber
		//the slower one will be completely rejected ulike merge/concat/flatmap/concatmap
		@NonNull
		Observable<@NonNull String> ob1Take = Observable.interval(1,TimeUnit.SECONDS)
												      .map(number -> "Observer1-"+number)
												      .take(5);
		
		Observable<@NonNull String> ob2Take = Observable.interval(500,TimeUnit.MILLISECONDS)
													    .map(number -> "Observer2-"+number)
													    .take(5);
		
		@NonNull
		Observable< @NonNull String> optimumObservable = Observable.amb(Arrays.asList(ob1Take, ob2Take));
		
		optimumObservable.subscribe(ChannelUtil.dataChannel,ChannelUtil.errorChannel,ChannelUtil.completeChannel)
;		
		//as itnerval entry is async
		ThreadUtil.sleep(6000);
	}
}
