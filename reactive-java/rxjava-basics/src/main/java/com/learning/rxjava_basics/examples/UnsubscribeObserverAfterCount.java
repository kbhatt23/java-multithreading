package com.learning.rxjava_basics.examples;

import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class UnsubscribeObserverAfterCount {
public static void main(String[] args) {
	//using infinite async call
	@NonNull
	Observable<String> infiniteString = Observable.interval(1, TimeUnit.SECONDS).map(String::valueOf);
	
	Observer<String> observer1 = new Observer<String>() {

		private int count=0;
		private int maxCount= 3;
		private Disposable disposable;
		@Override
		public void onSubscribe(@NonNull Disposable d) {
			System.out.println("observer 1 subscription added");
			disposable= d;
		}

		@Override
		public void onNext(@NonNull String t) {
			//based on count instead of time
			if(count > maxCount)
				disposable.dispose();
			System.out.println("observer 1 recieves message "+t);
			count++;
		}

		@Override
		public void onError(@NonNull Throwable e) {
			System.out.println("observer 1 got error "+e);
		}

		@Override
		public void onComplete() {
			System.out.println("observer 1 task completed");
		}
	};
	
//	Disposable subscriber1Dispoable = infiniteString.subscribe(item -> System.out.println("subscriber 1 recieves message "+item));
	//disposable not returned
	infiniteString.subscribe(observer1);
	
	infiniteString.subscribe(item -> System.out.println("subscriber 2 recieves message "+item));
	
	
	ThreadUtil.sleep(8000);
}
}
