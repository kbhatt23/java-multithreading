package com.learning.multithreading.concurrentutils;

import java.util.concurrent.CountDownLatch;

public class SelfBasicCountdownLatch {
public static void main(String[] args) {
	//this is thread safe class
	//if we want to start one thread only when other 2 or three are done we cna use this
	//instead of wrintg code on handling thread safe class/method we can use this class
	
	//in this example we are considering 2 threads
	CountDownLatch latch = new CountDownLatch(2);
	latch.countDown();
	System.out.println("current count "+latch.getCount());
	latch.countDown();
	System.out.println("current count "+latch.getCount());
	
	try {
		latch.await();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("main ki kahani ki ssuruat");
}
}
