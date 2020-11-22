package com.learning.multithreading.self;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.learning.multithreading.util.ThreadUtil;

public class SelfDeadlockFixUsingReentrantLock {
	public static void main(String[] args) {
		Lock lock1 = new ReentrantLock();
		Lock lock2 = new ReentrantLock();
		Thread t1 = new Thread(new TaskOne(lock1, lock2), "Thread1");
		Thread t2 = new Thread(new TaskTwo(lock1, lock2), "Thread2");
		t1.start();
		t2.start();
	}

	private static class TaskOne implements Runnable {

		private Lock lock1;
		private Lock lock2;

		private TaskOne(Lock lock1, Lock lock2) {
			this.lock1 = lock1;
			this.lock2 = lock2;
		}

		@Override
		public void run() {
			while (true) {
				boolean aquiredBothLocks = false;
				try {
					aquiredBothLocks = ThreadUtil.aquireReentrantLockUnBlocked(lock1, lock2);
					if (aquiredBothLocks) {
						System.out.println("Both locks aquired by " + Thread.currentThread().getName());
						System.out.println("Task completed by " + Thread.currentThread().getName());
					}
				} finally {
					if (aquiredBothLocks) {
						lock2.unlock();
						lock1.unlock();
					}
				}
			}
		}
	}

	private static class TaskTwo implements Runnable {

		private Lock lock1;
		private Lock lock2;

		private TaskTwo(Lock lock1, Lock lock2) {
			this.lock1 = lock1;
			this.lock2 = lock2;
		}

		@Override
		public void run() {

			while (true) {
				boolean aquiredBothLocks = false;
				try {
					aquiredBothLocks = ThreadUtil.aquireReentrantLockUnBlocked(lock1, lock2);
					if (aquiredBothLocks) {
						System.out.println("Both locks aquired by " + Thread.currentThread().getName());
						System.out.println("Task completed by " + Thread.currentThread().getName());
					}
				} finally {
					if (aquiredBothLocks) {
						lock2.unlock();
						lock1.unlock();
					}
				}
			}
		
		}

	}
}
