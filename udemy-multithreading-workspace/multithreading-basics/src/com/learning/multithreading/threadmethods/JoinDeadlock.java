package com.learning.multithreading.threadmethods;

import com.learning.multithreading.util.ThreadUtil;

public class JoinDeadlock {
public static void main(String[] args) {
	Thread mainThread  = Thread.currentThread();
	Thread t = new Thread(() -> {
		//both the abvove lines are same
		//ThreadUtil.sleep(100);
		try {
			mainThread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ThreadUtil.join(mainThread);
		System.out.println("Task completed by thread ");
	} ) ;
	
	t.start();
	ThreadUtil.join(t);
	System.out.println("all tasks completed");
}
}
