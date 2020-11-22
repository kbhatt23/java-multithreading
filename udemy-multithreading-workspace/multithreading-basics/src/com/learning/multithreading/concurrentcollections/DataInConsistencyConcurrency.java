package com.learning.multithreading.concurrentcollections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.learning.multithreading.util.ThreadUtil;

public class DataInConsistencyConcurrency {
public static void main(String[] args) {
	//List<Integer> numbers= new ArrayList<Integer>();
	List<Integer> numbers= new CopyOnWriteArrayList<Integer>();
	Runnable target = () ->  {
		for(int i = 0; i < 100 ; i++) {
			numbers.add(i+1);
		}
	};
	Thread t1 = new Thread ( target);
	Thread t2 = new Thread ( target);
	Thread t3 = new Thread ( target);
	t1.start();t2.start();t3.start();
	ThreadUtil.join(t1);
	ThreadUtil.join(t2);ThreadUtil.join(t3); 
	System.out.println("finsal list size "+numbers.size());
	
}
}
