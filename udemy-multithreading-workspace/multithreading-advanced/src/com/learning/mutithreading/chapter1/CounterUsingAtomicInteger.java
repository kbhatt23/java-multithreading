package com.learning.mutithreading.chapter1;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterUsingAtomicInteger {
	private AtomicInteger counter = new AtomicInteger();

//	public synchronized void  increment() {
//		counter++;
//	}

	public  void increment() {
		counter.incrementAndGet();
	}

	public int getCount() {
		return counter.get();
	}

	public static void main(String[] args) {
		CounterUsingAtomicInteger obj = new CounterUsingAtomicInteger();

		Runnable r = () -> {
			for (int i = 0; i < 10000; i++) {
				obj.increment();
			}
		};

		Thread thread1 = new Thread(r);
		Thread thread2 = new Thread(r);

		Thread thread3 = new Thread(r);
		thread1.start();
		thread2.start();
		thread3.start();

		try {
			thread1.join();
			thread2.join();
			thread3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("final counter " + obj.getCount());
	}
}
