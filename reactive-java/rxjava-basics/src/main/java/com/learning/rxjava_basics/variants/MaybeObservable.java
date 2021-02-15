package com.learning.rxjava_basics.variants;

import java.util.ArrayList;

import com.learning.rxjava_basics.examples.ChannelUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MaybeObservable {
public static void main(String[] args) {
	//multiple data enties
	//Observable<String> data = Observable.just("sita-ram","radhe-shyma","uma-shankar");
	
	//empty items
	//Observable<String> data = Observable.fromIterable(new ArrayList<>());
	Observable<String> data = Observable.empty()
			;
		
		@NonNull
		Maybe<@NonNull String> firstElement = data.firstElement();
		
		firstElement.subscribe(ChannelUtil.dataChannel, ChannelUtil.errorChannel, 
				ChannelUtil.completeChannel
				);
		
		System.out.println("============");
		
		MaybeObserver<String> observer = new MaybeObserver<String>() {

			@Override
			public void onSubscribe(@NonNull Disposable d) {
				System.out.println("subscription added");
			}

			@Override
			public void onSuccess(@NonNull String t) {
				System.out.println("item found "+t);
			}

			@Override
			public void onError(@NonNull Throwable e) {
				System.out.println("error occured "+e);
			}

			@Override
			public void onComplete() {
				System.out.println("task completed");
			}
		};
		
		firstElement.subscribe(observer);
}
}
