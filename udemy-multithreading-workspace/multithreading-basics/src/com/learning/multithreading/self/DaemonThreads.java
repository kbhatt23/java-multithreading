package com.learning.multithreading.self;

import com.learning.multithreading.util.ThreadUtil;

public class DaemonThreads {
public static void main(String[] args) {
	//Thread t1 = new Thread(() ->   {ThreadUtil.sleep(2000); System.out.println("Task completed by thread one");});
	
	Thread t2 = new Thread(() ->   {ThreadUtil.sleep(2000); System.out.println("Task completed by thread two");});
	
	//t1.setDaemon(true);
	//t1.start();
	t2.start();
	System.out.println("Main thread is over");
	//no bock meanns main thread will be completed
	//damemon thread rmains live until its creator is live
}
}
