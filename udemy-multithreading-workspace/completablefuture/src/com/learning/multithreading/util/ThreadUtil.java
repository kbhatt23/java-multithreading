package com.learning.multithreading.util;

import java.util.concurrent.locks.Lock;

public class ThreadUtil {
	public static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void join(Thread t) {
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static boolean aquireReentrantLockUnBlocked(Lock lock1, Lock lock2) {
		// keep on trying to aquire both the locks
		while (true) {
			// this is blocked and hence not using it
			// lock1.lock();
			// adding try block jus tin case of exception -> try the thing again
			boolean aquireLock1 = false;
			boolean aquireLock2 = false;

			try {
				aquireLock1 = lock1.tryLock();
				aquireLock2 = lock2.tryLock();
				if (aquireLock1 && aquireLock2) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			finally {
				// handling when only pone lock was aquired
				if (aquireLock1 && !aquireLock2) {
					lock1.unlock();
				}
				if (aquireLock2 && !aquireLock1) {
					lock2.unlock();
				}
			}
			ThreadUtil.sleep(10);
		}

	}
	//user of this method need to call this in try and in finnally need to unlock this
	public static boolean aquireReentrantLockUnBlocked(Lock lock1) {
		while (true) {
			boolean tryLock = lock1.tryLock();
			if (tryLock) {
				return true;
			}
		}
	}

	public ThreadGroup findThreadGroup(Thread thread) {
		return thread.getThreadGroup();
	}
}


