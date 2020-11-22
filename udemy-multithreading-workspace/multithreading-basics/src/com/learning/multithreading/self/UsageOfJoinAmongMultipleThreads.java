package com.learning.multithreading.self;

import com.learning.multithreading.util.ThreadUtil;

public class UsageOfJoinAmongMultipleThreads {
	public static void main(String[] args) {
		Thread t1 = new Thread(() ->   {
			System.out.println("task started by thread "+Thread.currentThread().getName());
			ThreadUtil.sleep(1000);
			System.out.println("task completed by thread "+Thread.currentThread().getName());
		});
		//t1.setDaemon(true);
		t1.start();
		
		Thread t2 = new Thread(() ->   {
			//thread is waiting until thread 1 is completing the task
			ThreadUtil.join(t1);
			System.out.println("task started by thread "+Thread.currentThread().getName());
			ThreadUtil.sleep(1000);
			System.out.println("task completed by thread "+Thread.currentThread().getName());
		});
		//t2.setDaemon(true);
		t2.start();
		
		//if i wont join both the threads/or atleast the second thread, main thread will die

		System.out.println("All tasks compelted");
	}
}
