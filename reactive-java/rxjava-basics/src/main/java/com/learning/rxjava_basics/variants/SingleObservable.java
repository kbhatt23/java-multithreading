package com.learning.rxjava_basics.variants;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class SingleObservable {
public static void main(String[] args) {
	Single<String> singleObservable = Single.just("jai shree ram");
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
	singleObservable.subscribe(singleObserver);
}
}
