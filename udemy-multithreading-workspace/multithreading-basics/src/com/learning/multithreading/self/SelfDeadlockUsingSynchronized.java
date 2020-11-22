package com.learning.multithreading.self;

import com.learning.multithreading.util.ThreadUtil;

public class SelfDeadlockUsingSynchronized {
public static void main(String[] args) {
	Object lock1 = new Object();
	Object lock2 = new Object();
	Thread t1 = new Thread(new TaskOne(lock1, lock2),"Thread1");
	Thread t2 = new Thread(new TaskTwo(lock1, lock2),"Thread2");
	t1.start();t2.start();
}

private static class TaskOne implements Runnable{

	private Object lock1;
	private Object lock2;
	
	 private TaskOne(Object lock1,Object lock2) {
		this.lock1=lock1;
		this.lock2=lock2;
	}
	@Override
	public void run() {
		while(true) {
		synchronized (lock1) {
			System.out.println("lock1 aquired by "+Thread.currentThread().getName());
			synchronized (lock2) {
				System.out.println("lock2 aquired by "+Thread.currentThread().getName());
				System.out.println("Task completed by "+Thread.currentThread().getName());
			}
		}
		
		}
	}
	
}

private static class TaskTwo implements Runnable{

	private Object lock1;
	private Object lock2;
	
	 private TaskTwo(Object lock1,Object lock2) {
		this.lock1=lock1;
		this.lock2=lock2;
	}
	@Override
	public void run() {
		while(true) {
		synchronized (lock2) {
			System.out.println("lock2 aquired by "+Thread.currentThread().getName());
			synchronized (lock1) {
				System.out.println("lock1 aquired by "+Thread.currentThread().getName());
				System.out.println("Task completed by "+Thread.currentThread().getName());
			}
		}}
	}
	
}
}


