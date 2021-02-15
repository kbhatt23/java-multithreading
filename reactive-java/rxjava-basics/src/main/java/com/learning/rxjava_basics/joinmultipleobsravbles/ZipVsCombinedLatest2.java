package com.learning.rxjava_basics.joinmultipleobsravbles;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.examples.ChannelUtil;
import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class ZipVsCombinedLatest2 {
	public static void main(String[] args) {
		// combinedlatest means each index will take latest one
		//meaning if one item is slower and other one is faster
		//some of the items may lose from slower one
		Observable<@NonNull String> ob1Take = Observable.interval(1, TimeUnit.SECONDS)
				.map(number -> "Observer1-" + number).take(5);

		Observable<@NonNull String> ob2Take = Observable.interval(500, TimeUnit.MILLISECONDS)
				.map(number -> "Observer2-" + number).take(5);
		

			@NonNull
			Observable<String> combineLatest = Observable.combineLatest(Arrays.asList(ob1Take,ob2Take), objArray -> {
				//no waiting whatever is latest will be picked
				String item1 = 	(String)objArray[0];
				String item2 = 	(String)objArray[1];
				return item1+" : "+item2;
			});
		
		//zip means each index waits 
		//if one is slower then eac iteration wait until that one is completed
		//hence maintianng sequnce
			combineLatest.subscribe(ChannelUtil.dataChannel,ChannelUtil.errorChannel,ChannelUtil.completeChannel)
		;		
				//as itnerval entry is async
				ThreadUtil.sleep(6000);
	}
}
