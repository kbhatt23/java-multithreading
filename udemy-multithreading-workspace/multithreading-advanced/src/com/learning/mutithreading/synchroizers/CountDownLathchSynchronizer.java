package com.learning.mutithreading.synchroizers;

import java.util.concurrent.CountDownLatch;

public class CountDownLathchSynchronizer {
public static void main(String[] args) {
	
	CountDownLatch latch = new CountDownLatch(2);
	
	latch.countDown();
	System.out.println("count down once");
	latch.countDown();
	System.out.println("count down twice");
	try {
		latch.await();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Task 1 started");
	
	
}
}
