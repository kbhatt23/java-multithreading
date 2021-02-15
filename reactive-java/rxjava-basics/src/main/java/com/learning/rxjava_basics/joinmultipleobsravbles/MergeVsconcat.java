package com.learning.rxjava_basics.joinmultipleobsravbles;

import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class MergeVsconcat {
	public static void main(String[] args) {
		//Observable<Integer> observable1 = Observable.just(1,2,3,4,5).i;
//		Observable<Integer> observable2 = Observable.just(1,2,3,4,5);//keeping same
		
		Observable<Long> observable1 = Observable.interval(1, TimeUnit.SECONDS).take(5);
		Observable<Long> observable2 = Observable.interval(1, TimeUnit.SECONDS).take(5);
		
		
		//there is no gurantee that duplicates will be removed, all will come
		//merge will consider 1st index og ob1 htne 1st of ob2, 2nd of ob1 , 2nd of ob2 and so on
		@NonNull
		Observable<@NonNull Long> merge = Observable.merge(observable1,observable2);
		merge.subscribe(n -> System.out.println("found element after merge "+n));
		
		//merge.toList().subscribe(list -> System.out.println("merged items list "+list));
		
		System.out.println("==============");
		//concat with -> first all elements of ob1 and then all elements of ob2
		@NonNull
		Observable<@NonNull Long> concatWith = observable1.concatWith(observable2);
		concatWith.subscribe(n -> System.out.println("found element after concat "+n));
		
		//concatWith.toList().subscribe(list -> System.out.println("concat items list "+list));
		
		ThreadUtil.sleep(11000);
	}
}
