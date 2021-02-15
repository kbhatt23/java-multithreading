package com.learning.rxjava_basics.paralleization;

import com.learning.rxjava_basics.examples.ChannelUtil;
import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ComputationIntenstiveUSage {
	public static void main(String[] args) {
		//demoForBasic();
		demoComputationTask();
	}

	//number of thread will be <= number of cores
	//i have 8 cores in this machine
	private static void demoComputationTask() {
		Observable<Integer> data = Observable.just(1,2,3,4,5)
											.map(i -> ChannelUtil.computationTask())
											.subscribeOn(Schedulers.io())
											.subscribeOn(Schedulers.computation());
		data.subscribe(item -> System.out.println("observer1 found "+item+" from thread "+Thread.currentThread().getName()));
		data.subscribe(item -> System.out.println("observer2 found "+item+" from thread "+Thread.currentThread().getName()));
		
		data.subscribe(item -> System.out.println("observer3 found "+item+" from thread "+Thread.currentThread().getName()));
		
		data.subscribe(item -> System.out.println("observer4 found "+item+" from thread "+Thread.currentThread().getName()));
	
		data.subscribe(item -> System.out.println("observer5 found "+item+" from thread "+Thread.currentThread().getName()));
		
		data.subscribe(item -> System.out.println("observer6 found "+item+" from thread "+Thread.currentThread().getName()));
		
		data.subscribe(item -> System.out.println("observer7 found "+item+" from thread "+Thread.currentThread().getName()));
		
		data.subscribe(item -> System.out.println("observer8 found "+item+" from thread "+Thread.currentThread().getName()));
		
		data.subscribe(item -> System.out.println("observer9 found "+item+" from thread "+Thread.currentThread().getName()));
	
		data.subscribe(item -> System.out.println("observer10 found "+item+" from thread "+Thread.currentThread().getName()));
		data.subscribe(item -> System.out.println("observer11 found "+item+" from thread "+Thread.currentThread().getName()));
		data.subscribe(item -> System.out.println("observer12 found "+item+" from thread "+Thread.currentThread().getName()));
		data.subscribe(item -> System.out.println("observer13 found "+item+" from thread "+Thread.currentThread().getName()));
		data.subscribe(item -> System.out.println("observer14 found "+item+" from thread "+Thread.currentThread().getName()));
		data.subscribe(item -> System.out.println("observer15 found "+item+" from thread "+Thread.currentThread().getName()));
		data.subscribe(item -> System.out.println("observer16 found "+item+" from thread "+Thread.currentThread().getName()));
		ThreadUtil.sleep(20000);
	}

	private static void demoForBasic() {
		@NonNull
		Observable<String> just = Observable.just("sita-ram","radhe-shyam","uma-shankar","lekshmi-narayan",
				"shiva-parvati","namo-narayan"
				)
		.subscribeOn(Schedulers.computation())//using scheuler computation intenstive lets use pool threads
			//threads=  number of cores
		;
		//the pool threads will push data to observers , not neccessary that thread will be different for each subscriber
		//not after task threads will be removed
		just.subscribe(item -> System.out.println("observer1 found "+item+" from thread "+Thread.currentThread().getName()));
		
		just.subscribe(item -> System.out.println("observer2 found "+item+" from thread "+Thread.currentThread().getName()));
		
		just.subscribe(item -> System.out.println("observer3 found "+item+" from thread "+Thread.currentThread().getName()));
		
		just.subscribe(item -> System.out.println("observer4 found "+item+" from thread "+Thread.currentThread().getName()));
	
		just.subscribe(item -> System.out.println("observer5 found "+item+" from thread "+Thread.currentThread().getName()));
		
		just.subscribe(item -> System.out.println("observer6 found "+item+" from thread "+Thread.currentThread().getName()));
		
		ThreadUtil.sleep(10000);
	}
}
