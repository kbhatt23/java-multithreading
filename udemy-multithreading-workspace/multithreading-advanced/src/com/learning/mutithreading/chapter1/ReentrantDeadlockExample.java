package com.learning.mutithreading.chapter1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantDeadlockExample {

	private static Map<Lock, String> lockNames = new HashMap<Lock, String>();
	public static void main(String[] args) {
//		Lock lock1 = new ReentrantLock();
//		Lock lock2 = new ReentrantLock();
		//to remove starvation
		Lock lock1 = new ReentrantLock(true);
		Lock lock2 = new ReentrantLock(true);
		lockNames.put(lock1, "LOCK-1");
		lockNames.put(lock2, "LOCK-2");

		Runnable r1 = () -> {
			executeRunnable(lock1, lock2);
		};
		// locks are in reverse order
		Runnable r2 = () -> {
			//executeRunnable(lock2, lock1);
			
			//solution
			executeRunnable(lock1, lock2);
		};
		Thread t1 = new Thread(r1, "Thread-1");

		Thread t2 = new Thread(r2, "Thread-2");
		
		t1.start();
		t2.start();
	}

	private static void executeRunnable(Lock lock1, Lock lock2) {
		String lock1Name = lockNames.get(lock1);
		String lock2Name = lockNames.get(lock2);
		
		while (true) {
			try {
				lock1.lock();
				System.out.println(lock1Name+" aquired by Thread " + Thread.currentThread().getName());
				try {
					lock2.lock();
					System.out.println(lock2Name+" aquired by Thread " + Thread.currentThread().getName());

					// do the task
					System.out.println("Task started by thread " + Thread.currentThread().getName());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Task completed by thread " + Thread.currentThread().getName());
				} finally {
					lock2.unlock();
				}

			} finally {
				lock1.unlock();
				System.out.println("Lock 1 released by Thread " + Thread.currentThread().getName());
			}
		}
	}
}
