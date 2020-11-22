package com.learning.multithreading.self;

import com.learning.multithreading.util.ThreadUtil;

public class DaemonThreadpart {
public static void main(String[] args) {
	Thread t = new Thread(new DaemonThread());
	t.setDaemon(true);
	
	t.start();
	//waiting 2 second before main thread is killed
	ThreadUtil.sleep(2000);
	System.out.println("Main thread is killed");
} 


private static class DaemonThread implements Runnable{

	@Override
	public void run() {
		try {
		while(true) {
			System.out.println("Bulk opearion in progress");
			ThreadUtil.sleep(200);
		}}
		//finally wiull never print as thread is killed forcefully once main thread is killed
		
		 finally {
			System.out.println("Bulk opeartion completed");
		}
	}
	//even this is not called as the thread is completely ignored
	@Override
	protected void finalize() throws Throwable {
		System.out.println("JVM killed bulk opeartion");
	}
	
}
}
