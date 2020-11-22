package com.learning.multithreading.racecondition;

import java.util.concurrent.atomic.AtomicInteger;

import com.learning.multithreading.util.ThreadUtil;

public class ReaceConditionWhileSumming {
	private  int sum = 0;
	
	private AtomicInteger sumAtomic = new AtomicInteger();

	private void increment() {
		sum++;
	}
	
	private void incrementWithNoRaceCondition() {
		sumAtomic.getAndIncrement();
	}

	public static void main(String[] args) {
		ReaceConditionWhileSumming obj = new ReaceConditionWhileSumming();
		
		Runnable r = () -> {for(int i =0 ; i < 10000; i++) {obj.increment();}};
		
		Thread thread1 = new Thread(r);
		thread1.start();
		Thread thread2 = new Thread(r);
		thread2.start();
		
		ThreadUtil.join(thread1);
		ThreadUtil.join(thread2);
		System.out.println("total sum "+obj.sum);
		System.out.println("atomic int way");
		Runnable r1 = () -> {for(int i =0 ; i < 10000; i++) {obj.incrementWithNoRaceCondition();}};
		
		Thread thread11 = new Thread(r1);
		thread11.start();
		Thread thread22 = new Thread(r1);
		thread22.start();
		
		ThreadUtil.join(thread11);
		ThreadUtil.join(thread22);
		System.out.println("total sum "+obj.sumAtomic.get());
		
	}

}