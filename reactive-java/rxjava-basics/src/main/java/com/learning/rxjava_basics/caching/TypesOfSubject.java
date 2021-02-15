package com.learning.rxjava_basics.caching;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import io.reactivex.rxjava3.subjects.Subject;
import io.reactivex.rxjava3.subjects.UnicastSubject;

public class TypesOfSubject {
	public static void main(String[] args) {
		//Publishsubject is hot observable 
		
		Subject<String> subject = PublishSubject.create();
		
		//replayable means second subscriber starts data from begining
		//Subject<String> subject = ReplaySubject.create();
		
		
		//behavioral means replayable with replay size 1
		//Subject<String> subject = BehaviorSubject.create();
		
		//async means directly last element will be recieved by both
		//Subject<String> subject = AsyncSubject.create();
		
		//unicast means only one observer cna subscribe to the observable
		//gives exception in other case
		//Subject<String> subject = UnicastSubject.create();
		learnSubject(subject);
	}

	public static void learnSubject(Subject<String> subject) {
		Consumer<String> observer1 = str -> {ThreadUtil.sleep(1000);System.out.println("observer1 recieves " + str); };
		Consumer<String> observer2 = str -> {ThreadUtil.sleep(1000);System.out.println("observer2 recieves " + str);};

		// if hot comes only observer 1 will recieve the data
		subject.subscribe(observer1);

		subject.onNext("jai shree ram");
		subject.onNext("jai radhe shyam");
		subject.onNext("jai lekshmi narayan");

		//if we do not subscribe unicast works normally
		subject.subscribe(observer2);

		subject.onNext("jai uma shankar");
		subject.onNext("jai gauri shankar");
		
		subject.onComplete();
	}
}
