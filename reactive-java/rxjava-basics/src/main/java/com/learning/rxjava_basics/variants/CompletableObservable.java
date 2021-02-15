package com.learning.rxjava_basics.variants;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class CompletableObservable {
public static void main(String[] args) {
	
	@NonNull
	Completable fromRunnable = Completable.fromRunnable(() -> {
		boolean showError = false;
		System.out.println("runnable task started");
		ThreadUtil.sleep(2000);
		if(showError) {
			throw new RuntimeException("could not handle task"); 
		}
		System.out.println("runnable task completed");
	});
	System.out.println("tasks assigned");
	fromRunnable.subscribe(() -> System.out.println("client task completed")
			
/*			error -> System.out.println("handling error here "+error)*/);
	
	System.out.println("==================");
	
	CompletableObserver observer = new CompletableObserver() {
		
		@Override
		public void onSubscribe(@NonNull Disposable d) {
			System.out.println("subscription added");
		}
		
		@Override
		public void onError(@NonNull Throwable e) {
			System.out.println("error channel "+e);
		}
		
		@Override
		public void onComplete() {
			System.out.println("all tasks completed");
		}
	};
	fromRunnable.subscribe(observer);

	
}
}
