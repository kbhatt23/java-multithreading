package com.learning.rxjava_basics.paralleization;

import java.util.concurrent.CompletableFuture;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.core.Observable;

public class BasicParallelization {
	public static void main(String[] args) {
		// sequentially();
		
		//thread is non daemon by default so all the task will get done even main thread is gone
		threadEmiiter();
		
		//completable future runs in daemon thread so if main thread goes tasks will be ignored
		//usingCompletablefuture();
	}

	private static void sequentially() {
		// rx java is non parallel by default
		Observable<Integer> intData = Observable.create(emitter -> {
			emitter.onNext(10);
			emitter.onNext(11);
		});

		intData.subscribe(item -> System.out
				.println("observer1 recieves " + item + " from thread " + Thread.currentThread().getName()));

		intData.subscribe(item -> System.out
				.println("observer2 recieves " + item + " from thread " + Thread.currentThread().getName()));
	}

	//same as scheudler new thread
	//for each subscriber creates nwe thread do the task and once done kill the thread
	private static void threadEmiiter() {
		// rx java is non parallel by default
		Observable<Integer> intData = Observable.create(emitter -> {
			// main thread delegates emission to new thread for each observer
			new Thread(() -> {
				emitter.onNext(10);
				ThreadUtil.sleep(1000);
				emitter.onNext(11);
			}).start();
		});

		//subscribe becomes unblcking but thread is non daemon so all task will be completed even though main thread is dumped
		intData.subscribe(item -> System.out
				.println("observer1 recieves " + item + " from thread " + Thread.currentThread().getName()));

		// thread creation in emission made is async and non blocking
		// observer runs in another thread
		System.out.println("first subsription done");

		intData.subscribe(item -> System.out
				.println("observer2 recieves " + item + " from thread " + Thread.currentThread().getName()));
		
		intData.subscribe(item -> System.out
				.println("observer3 recieves " + item + " from thread " + Thread.currentThread().getName()));
	}

	private static void usingCompletablefuture() {
		// rx java is non parallel by default
		Observable<Integer> intData = Observable.create(emitter -> {
			//main thread delegates emission to new thread for each observer
			//use daemon thread
			CompletableFuture.runAsync( () -> {
				emitter.onNext(10);
				ThreadUtil.sleep(1000);
				emitter.onNext(11);
				});
		});

		///non blocking
		intData.subscribe(item -> System.out
				.println("observer1 recieves " + item + " from thread " + Thread.currentThread().getName()));

		//thread creation in emission made is async and non blocking
		//observer runs in another thread
		System.out.println("first subsription done");
		
		intData.subscribe(item -> System.out
				.println("observer2 recieves " + item + " from thread " + Thread.currentThread().getName()));
	}
}
