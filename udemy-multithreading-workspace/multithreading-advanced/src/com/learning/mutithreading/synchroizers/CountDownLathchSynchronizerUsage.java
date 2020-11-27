package com.learning.mutithreading.synchroizers;

import java.util.concurrent.CountDownLatch;

public class CountDownLathchSynchronizerUsage {
public static void main(String[] args) {
	
	CountDownLatch latch = new CountDownLatch(2);
	
	Runnable r = () ->{
		System.out.println("Task started by "+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Task completed by "+Thread.currentThread().getName());
		latch.countDown();
	};
	
	new Thread(r).start();
	new Thread(r).start();
	
	//instead of joining we can use latch
	System.out.println("all threads started by main");
	
	//will keep on waiting until count is 0, menaing other 2 threads are done with theri task and did countdown
	try {
		latch.await();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	System.out.println("Everything completed");
	
}
}
