package com.learning.rxjava_basics.caching;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import io.reactivex.rxjava3.subjects.Subject;

public class PublishSubjectHot {
	public static void main(String[] args) {
		// publish is hot
		// we can do replay in case old data is lost
		oldWay();
		System.out.println("===========");
		replayableSubject();

	}

	private static void replayableSubject() {

		//replayable means even if subscriber comes late it can get data form begining
		Subject<String> create = ReplaySubject.create();
		
		//with publishsubject it is hot and hence subscriber will loose the data if data is snet and sbscriber ocmes late
		//Subject<String> create = PublishSubject.create();
		
		create.onNext("loose it with default subject");
		
		create.subscribe(item -> System.out.println("item recieved "+item));
		create.onNext("get it with default subject");
	}

	private static void oldWay() {
		// old way
		@NonNull
		PublishSubject<String> create = PublishSubject.create();

		// we know subject is hot so if observer is not subscribed still it will keep on
		// sending data
		// hence we will loose the data
		create.subscribe(item -> System.out.println("item recieved " + item));

		create.onNext("jai shree ram");
		create.onNext("jai uma shankar");
		create.onNext("jai radhe krishna");

		// there is no complete action on complete so skips it
		create.onComplete();
	}
}
