package com.learning.rxjava_basics.backpressure;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

//in ase producer is fast and consumer is very slow
//in sequential above wont cause issue, however in case of paralle using observeon
	//consumer wont be able to handle such big data so we need backpressure to avoid out of memory error
public class WhyUSeBackPressure {

	public static void main(String[] args) {
		//backPressureChallenge();
		backPressureFix();
		
	}

	private static void backPressureFix() {

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
			.subscribe(item ->{
				//mimcing slowness
				ThreadUtil.sleep(300);
				System.out.println("Consumed item "+item +" using thread "+Thread.currentThread().getName());
			})
			
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
