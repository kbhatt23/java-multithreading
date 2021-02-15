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

public class StockObserverErrorOnNext {

	public static void main(String[] args) {
	Observable<Stock> observable = StockUtil.getStocks(StockUtil.STOCKS_KEYS);
		
		System.out.println("subscription done");
		observable
		//.filter(stock -> stock.getPrice()<15)//lets book if stock is cheap 
		.onErrorResumeNext(error ->{
			System.out.println("error occurred "+error+" hence switching to another observable");
			return StockUtil.getStocksNoError(StockUtil.STOCKS_KEYS);
		})
		.map(Stock::toString)
		.subscribe(ChannelUtil.dataChannel,ChannelUtil.errorChannel,ChannelUtil.completeChannel)
		;
	} 
}
	
