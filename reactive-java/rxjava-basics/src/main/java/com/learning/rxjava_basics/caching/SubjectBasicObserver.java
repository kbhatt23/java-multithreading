package com.learning.rxjava_basics.caching;

import java.util.concurrent.TimeUnit;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class SubjectBasicObserver {
public static void main(String[] args) {
	//subject can act as both observable as well as observer
	//also it can handle multiple observers or obseravables
	
	//lets consider scenario when observables are multiple and observer is same
	
	//multipleObserversSameObserver();
	System.out.println("======================="); 
	
	useSubjectAsListnerOfMultipleSource();
	
	System.out.println("===============");
	//useSubjectAsListnerOfMultipleSource2();
}

//since subject is hot it gets data form first soruce and data form other source is lost
private static void useSubjectAsListnerOfMultipleSource() {
	Observable<Long> src1 = Observable.interval(1, TimeUnit.SECONDS).take(4);
	Observable<Long> src2 = Observable.interval(1, TimeUnit.SECONDS).skip(4).take(4);
	
	//created single consumer using subject
	Consumer<Long> printObserver = number -> System.out.println("recieved number "+number);
	Subject<Long> subject = PublishSubject.create();
	subject.subscribe(printObserver);
	
	
	src1.subscribe(subject);
	
	src2.subscribe(subject);
	
	ThreadUtil.sleep(9000);
}

//here we have made it to run in concurrent scheudler single lister cn handle data from both source without data loss
private static void useSubjectAsListnerOfMultipleSource2() {
	//concurrnet sources can be handled by single subject but not by single observer
	Observable<Long> src1 = Observable.interval(1, TimeUnit.SECONDS).take(4);
	Observable<Long> src2 = Observable.interval(1, TimeUnit.SECONDS).skip(4).take(4).subscribeOn(Schedulers.computation());
	
	//created single consumer using subject
	Consumer<Long> printObserver = number -> System.out.println("recieved number "+number);
	Subject<Long> subject = PublishSubject.create();
	subject.subscribe(printObserver);
	
	
	src1.subscribe(subject);
	
	src2.subscribe(subject);
	
	ThreadUtil.sleep(9000);
}


private static void multipleObserversSameObserver() {
	//even if we add concurrency form source
	//if listner is one and common it will still execute sequentially
	//for concurrnecy we need more than one listener
	Observable<Long> src1 = Observable.interval(1, TimeUnit.SECONDS).take(4).subscribeOn(Schedulers.computation());
	Observable<Long> src2 = Observable.interval(1, TimeUnit.SECONDS).skip(4).take(4).subscribeOn(Schedulers.computation());
	
	@NonNull
	Consumer<Long> printObserver = number -> System.out.println("recieved number "+number);
	//multiple sources and single obsever will always amke it sequential
	//even though we use threadpool schedulers
	src1.subscribe(printObserver );
	src2.subscribe(printObserver );
	
	//same observer for different thread scheulable observable also can nto handle concurry
	//will be sequential
	
	ThreadUtil.sleep(9000);
	
}
}
