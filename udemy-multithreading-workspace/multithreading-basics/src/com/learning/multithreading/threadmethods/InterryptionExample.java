package com.learning.multithreading.threadmethods;

import com.learning.multithreading.util.ThreadUtil;

public class InterryptionExample {
	public static void main(String[] args) {
		// bad runnable even on interruption keep on conitnuing
		Runnable badRunnable = () -> {
			for (int i = 0; i < 10; i++) {
				try {
					System.out.println("Running iteration " + i);
					Thread.sleep(500);
				} catch (Exception e) {
					System.out.println("task was interrupted");
				}
			}
			System.out.println("task done by bad runnable");
		};

		Runnable goodRunnable = () -> {
			try {
				for (int i = 0; i < 10; i++) {
					System.out.println("Running iteration " + i);
					Thread.sleep(500);
				}
			} catch (Exception e) {
				System.out.println("task was interrupted");
			}
			System.out.println("task done by good runnable");
		};

		//Thread thread = new Thread(badRunnable);
		Thread thread = new Thread(goodRunnable);
		thread.start();
		ThreadUtil.sleep(1000);
		thread.interrupt();
	}
}
