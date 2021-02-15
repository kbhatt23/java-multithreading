package com.learning.rxjava_basics.backpressure;

import java.util.concurrent.atomic.AtomicInteger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

//in ase producer is fast and consumer is very slow
//in sequential above wont cause issue, however in case of paralle using observeon
	//consumer wont be able to handle such big data so we need backpressure to avoid out of memory error
public class BackPressureWithBatchCount {
	private static final int BATCH_COUNT = 100;

	public static void main(String[] args) {
		backPressureFix();
	}

	private static void backPressureFix() {
		
		//back pressure supported subscriber -> first step is to call only 100 elements
		//then we will update the case to call again and again and get 100 data and continue
		Subscriber<Integer> backPRessureSupportedSubscriber = new Subscriber<Integer>() {

			private volatile Subscription subscrition;
			private AtomicInteger count = new AtomicInteger();
			
			@Override
			public void onSubscribe(Subscription subscrition) {
				//in one batch i want 100 only
				if(subscrition == null) {
					this.subscrition=subscrition;
				}
				subscrition.request(BATCH_COUNT);
			}

			@Override
			public void onNext(Integer item) {
				int currentCount = count.getAndIncrement();
				if(currentCount >= BATCH_COUNT) {
					//ask again. back pressure
					subscrition.request(BATCH_COUNT);
				}
				//mimcing slowness
				ThreadUtil.sleep(300);
				System.out.println("Consumed item "+item +" using thread "+Thread.currentThread().getName());				
			}

			@Override
			public void onError(Throwable error) {
				System.out.println("error occurred "+error +" using thread "+Thread.currentThread().getName());				
			}

			@Override
			public void onComplete() {
				System.out.println("all task completed");
			}
		};

		//huge data running in parallel, just flowable fixes the issue
		Flowable.range(1, 1000000)
		//below map will run in sequential
		//since sequential it has to complete whole task then only it can allow consumer to start
			.map(item -> {
				System.out.println("Produced item "+item +" using thread "+Thread.currentThread().getName());
				return item;
			})
			.observeOn(Schedulers.io())
			//.subscribeOn(Schedulers.io())//with this whole pipelein runs in seprate thread paralleley
			//below will run in concurrent
			.subscribe(backPRessureSupportedSubscriber);
			;
		
		//since running in async we need to sleep
		ThreadUtil.sleep(10000000);
			
	}

	private static void backPressureChallenge() {
		//huge data running in parallel
		Observable.range(1, 1000000)
		//below map will run in sequential
		//since sequential it has to complete whole task then only it can allow consumer to start
			.map(item -> {
				System.out.println("Produced item "+item +" using thread "+Thread.currentThread().getName());
				return item;
			})
			.observeOn(Schedulers.io())
			//.subscribeOn(Schedulers.io())//with this whole pipelein runs in seprate thread paralleley
			//below will run in concurrent
			.subscribe(item ->{
				//mimcing slowness
				ThreadUtil.sleep(300);
				System.out.println("Consumed item "+item +" using thread "+Thread.currentThread().getName());
			})
			
			;
		
		//since running in async we need to sleep
		ThreadUtil.sleep(10000000);
	}
}
