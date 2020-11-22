package com.learning.multithreading.racecondition;

import com.learning.multithreading.util.ThreadUtil;

public class StaticVsInstanceLock {

	private int sum = 0;
	
	private static int staticSum = 0;
	//lock is on object level and not on class level
	public synchronized void increment() {
		System.out.println("started incrmeent by thread "+Thread.currentThread().getName());
		ThreadUtil.sleep(3000);
		sum++;
	}
	
	//lock is on class level and even differnet object will get loocked and blocked
	public static synchronized void incrementStatic() {
		System.out.println("started static incrmeent by thread "+Thread.currentThread().getName());
		ThreadUtil.sleep(3000);
		staticSum++;
	}
	
	public static void main(String[] args) {
		StaticVsInstanceLock obj  = new StaticVsInstanceLock();
		StaticVsInstanceLock obj1  = new StaticVsInstanceLock();
		Runnable r1 = () -> {
			//among diffenre obje tlock wont occur as there i no race condition , differnet thread is consuming different instance
			obj.increment();
			
			//obj.incrementStatic();
		};
		
		//we are inrementing static variable for method that is staci locked
		//meaning even differn tinstance will have to wait until previous thread is done
		//object is not shared but since class level lock happens all gets blocked
		Runnable r2 = () -> {
			//obj1.increment();
			obj1.increment();
			//obj1.incrementStatic();
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.setName("t1");t2.setName("t2");
		t1.start();t2.start();
		ThreadUtil.join(t1);ThreadUtil.join(t2);
		
		System.out.println("final instance1 val "+obj.sum);
		System.out.println("final instance2 val "+obj1.sum);
		
		System.out.println("final static1 val "+obj.staticSum);
		System.out.println("final static2 val "+obj1.staticSum);
				
	}
}
