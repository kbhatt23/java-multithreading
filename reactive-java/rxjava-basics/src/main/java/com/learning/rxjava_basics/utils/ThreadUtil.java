package com.learning.rxjava_basics.utils;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
