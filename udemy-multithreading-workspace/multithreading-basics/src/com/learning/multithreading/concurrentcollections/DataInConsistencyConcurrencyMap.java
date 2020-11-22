package com.learning.multithreading.concurrentcollections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import com.learning.multithreading.util.ThreadUtil;

public class DataInConsistencyConcurrencyMap {
public static void main(String[] args) {
	//Set<Integer> numbers= new HashSet<Integer>();
	Map<Integer, String> numbers= new HashMap<Integer, String>();
	//Map<Integer, String> numbers= new ConcurrentHashMap<Integer, String>();
	Runnable target = () ->  {
		for(int i = 0; i < 100 ; i++) {
			numbers.put(i, "fake");
		}
	};
	Runnable target1 = () ->  {
		for(int i = 100; i < 200 ; i++) {
			numbers.put(i, "fake");
		}
	};
	Runnable target2 = () ->  {
		for(int i = 200; i < 300 ; i++) {
			numbers.put(i, "fake");
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
