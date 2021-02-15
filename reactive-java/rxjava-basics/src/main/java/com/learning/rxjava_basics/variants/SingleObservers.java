package com.learning.rxjava_basics.variants;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

//mandatory is that one item can be ahdnled not 0 r more
public class SingleObservers {
public static void main(String[] args) {
	
	//multiple data enties
	Observable<String> data = Observable.just("sita-ram","radhe-shyma","uma-shankar");
	//in case there was no data then default value will be taken
	
	
	//lets say we want to get only first/single element from data source
	//Observable<String> data = Observable.fromIterable(new ArrayList<>());
	data.first("default value")
		.subscribe(item -> System.out.println("item found "+item));
	
	System.out.println("======");
	data.first("default entry")
	.subscribe((item,error) -> {
		if(error != null) {
			System.out.println("error found "+error);
		}else {
			System.out.println("success item found "+item);
		}
	});
	System.out.println("======");
	//manually crreate singleobserver
	//anonymous inner class type not needed after java 9
	SingleObserver<String> singleObserver = new SingleObserver<String>() {

		@Override
		public void onSubscribe(@NonNull Disposable d) {
			System.out.println("subscription added for single item");
		}

		@Override
		public void onSuccess(@NonNull String t) {
			System.out.println("success item found "+t);
		}

		@Override
		public void onError(@NonNull Throwable e) {
			System.out.println("error found "+e);
		}
	};
	data.first("default entry")
	.subscribe(singleObserver);
	
	
	
}
}
