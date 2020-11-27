package com.learning.mutithreading.chapter1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitAndNotifyExample1 {
	public static void main(String[] args) {

		Lock lock = new ReentrantLock();
		Condition taskCondition = lock.newCondition();
		Runnable t1 = new Task1(lock,taskCondition);
		Runnable t2 = new Task2(lock,taskCondition);
		
		//second pair wit other condition
		Condition secondCondition = lock.newCondition();
		Runnable t3 = new Task1(lock,secondCondition);
		Runnable t4 = new Task2(lock,secondCondition);
		
		new Thread(t1,"pair1.1").start();
		new Thread(t3,"pair2.1").start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(t2,"pair1.2").start();
		new Thread(t4,"pair2.2").start();
	}

	private static class Task1 implements Runnable {
		private Lock lock;
		private Condition taskCondition;
		private Task1(Lock lock, Condition taskCondition) {
			this.lock = lock;
			this.taskCondition=taskCondition;
		}
		@Override
		public void run() {

			lock.lock();
			
			System.out.println("Task1 started working by thread "+Thread.currentThread().getName());
			
			try {
				Thread.sleep(500);
				taskCondition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Task1 continued working by thread "+Thread.currentThread().getName());
			System.out.println("Task1 completed working by thread "+Thread.currentThread().getName());
			lock.unlock();
			
		}
	}
	
	private static class Task2 implements Runnable {
		private Lock lock;
		private Condition taskCondition;
		private Task2(Lock lock, Condition taskCondition) {
			this.lock = lock;
			this.taskCondition=taskCondition;
		}
		@Override
		public void run() {
			lock.lock();
			System.out.println("Task2 started working by thread "+Thread.currentThread().getName());
			
			
			taskCondition.signalAll();
			//below will sginal to a condition that is never connected by any other thread
			//lock.newCondition().signal();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Task 2 completed working by thread "+Thread.currentThread().getName());
			lock.unlock();
			
		}
	}
}
