package com.learning.rxjava_basics;

import java.util.stream.IntStream;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableCreate;

public class VerboseObservables {

	public static void main(String[] args) {

		// data source pushes unlike iterator whihc pulls the data from source
		// in p[roject reactor the data source contenrtn is not worked by dev
		// as D.B/ webservice/rabbitmq,kafka procides the data source observable/flux
		// instance
		Observable<String> dataSource = new ObservableCreate<>(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
				boolean isSucess = false;
				try {
					emitter.onNext("jai shree ram");
					emitter.onNext("jai radhe krishna");
					if (!isSucess)
						throw new IllegalStateException("unable to find next data source");
					emitter.onNext("fake message might not get delivered");
					emitter.onComplete();
				} catch (Exception e) {
					emitter.onError(e);
				}
			}
		});
		
		Observer<String> observer = new Observer<String>() {

			//called only once subscriber subscribes to observaable
			@Override
			public void onSubscribe(@NonNull Disposable d) {
				System.out.println("Subscription success");
			}

			@Override
			public void onNext(@NonNull String t) {
				System.out.println("Recieved message: "+t);
			}

			@Override
			public void onError(@NonNull Throwable e) {
				System.out.println("Error message recieved: "+e);
			}

			@Override
			public void onComplete() {
				System.out.println("All messages recieved succesfully");
			}
		};

		dataSource.subscribe(observer);
	}
}
