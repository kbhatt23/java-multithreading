package com.learning.rxjava_basics;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.learning.rxjava_basics.examples.ChannelUtil;
import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.core.Observable;

public class StockObserver {
	
	public static void main(String[] args) {
		Observable<Stock> observable = StockUtil.getStocks(StockUtil.STOCKS_KEYS);
		
		System.out.println("subscription done");
		observable
		//.filter(stock -> stock.getPrice()<15)//lets book if stock is cheap 
		.map(Stock::toString)
		.subscribe(ChannelUtil.dataChannel,ChannelUtil.errorChannel,ChannelUtil.completeChannel)
		;
	} 
	

}

class StockUtil{
	public static final List<String> STOCKS_KEYS = Arrays.asList("google","amazon","facebook","twitter");	
	private static Random random = new Random();
	public static Observable<Stock> getStocks(List<String> stocks){
		//default observable is cold, untill subscriber subscribes it do not start processing
		return Observable.create(emitter ->{
			System.out.println("Start processing stocks");
			//keep on pushing stocks actual price one by one
			int count=0;
			while(count++ < 5) {
				stocks.stream()
					  .map(stock -> new Stock(stock, random.nextInt(100)) )
					  .map(stock -> {
						  //if(stock.getPrice() < 0) {
						  if(stock.getPrice() < 50) {
							  throw new IllegalStateException("price can not be less than 50");
						  }
						  return stock;
					  })
					  .forEach(stockObject -> emitter.onNext(stockObject));
				ThreadUtil.sleep(100);
			}
			emitter.onComplete();
		});
	}

public static Observable<Stock> getStocksNoError(List<String> stocks){
	//default observable is cold, untill subscriber subscribes it do not start processing
	return Observable.create(emitter ->{
		System.out.println("Start processing stocks");
		//keep on pushing stocks actual price one by one
		int count=0;
		while(count++ < 5) {
			stocks.stream()
				  .map(stock -> new Stock(stock, random.nextInt(100)) )
				  .map(stock -> {
					  if(stock.getPrice() < 0) {
					  //if(stock.getPrice() < 50) {
						  throw new IllegalStateException("price can not be less than 0");
					  }
					  return stock;
				  })
				  .forEach(stockObject -> emitter.onNext(stockObject));
			ThreadUtil.sleep(100);
		}
		emitter.onComplete();
	});
}
}

class Stock{
	private String name;
	
	private double price;

	public Stock(String name, double price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Stock [name=" + name + ", price=" + price + "]";
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
	
	
}
