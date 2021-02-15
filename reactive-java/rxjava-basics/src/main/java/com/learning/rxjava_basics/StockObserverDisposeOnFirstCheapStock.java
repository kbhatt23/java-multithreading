package com.learning.rxjava_basics;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.learning.rxjava_basics.examples.ChannelUtil;
import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class StockObserverDisposeOnFirstCheapStock {
	public static void main(String[] args) {
		Observable<Stock> observable = StockUtil.getStocks(Arrays.asList("google","amazon","facebook","twitter"));
		
		System.out.println("subscription done");

		Observer<Stock> subscriber = new Observer<Stock>() {

			private Disposable subscription;

			@Override
			public void onNext(Stock t) {
				System.out.println("Stock recieved "+t);
				if(t.getPrice()<15) {
					System.out.println("lets book the stock "+t);
					subscription.dispose();
				}
				
			}

			@Override
			public void onError(Throwable t) {
				System.out.println("Error while fetching stock "+t);
			}

			@Override
			public void onComplete() {
				System.out.println("stock ticking completed");
			}

			@Override
			public void onSubscribe(@NonNull Disposable d) {
				System.out.println("subscription done in the custom subscriber");
				subscription=d;				
			}
		};
		
		observable.subscribe(subscriber);
	} 
}
	
