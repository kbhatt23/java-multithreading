package com.learning.rxjava_basics.operators;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class RepeatVsRetry {
	public static void main(String[] args) {

		
		@NonNull
		Observable<Integer> firstPart = Observable.just(1,2,3,4);
		
			;
		
		@NonNull
		Observable<@NonNull Integer> dataWithError = firstPart
													//.concatWith(Observable.error(RuntimeException::new))
													.concatWith(Observable.error(RuntimeException::new))
													.concatWith(Observable.just(5,6,7))
													;
		
													dataWithError .retry(2) 
					.subscribe(item -> System.out.println("item found "+item), 
							error -> System.out.println("error occured "+error),
							() -> System.out.println("all task completed"));
		
	}
}
