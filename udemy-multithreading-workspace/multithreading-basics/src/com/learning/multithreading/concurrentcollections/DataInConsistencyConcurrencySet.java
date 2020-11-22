package com.learning.multithreading.concurrentcollections;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.learning.multithreading.util.ThreadUtil;

public class DataInConsistencyConcurrencySet {
public static void main(String[] args) {
	//Set<Integer> numbers= new HashSet<Integer>();
	Set<Integer> numbers= new CopyOnWriteArraySet<Integer>();
	Runnable target = () ->  {
		for(int i = 0; i < 100 ; i++) {
			numbers.add(i+1);
		}
	};
	Runnable target1 = () ->  {
		for(int i = 100; i < 200 ; i++) {
			numbers.add(i+1);
		}
	};
	Runnable target2 = () ->  {
		for(int i = 200; i < 300 ; i++) {
			numbers.add(i+1);
		}
	};
	Thread t1 = new Thread ( target);
	Thread t2 = new Thread ( target1);
	Thread t3 = new Thread ( target2);
	t1.start();t2.start();t3.start();
	ThreadUtil.join(t1);
	ThreadUtil.join(t2);ThreadUtil.join(t3); 
	System.out.println("finsal list size "+numbers.size());
	
}
}
