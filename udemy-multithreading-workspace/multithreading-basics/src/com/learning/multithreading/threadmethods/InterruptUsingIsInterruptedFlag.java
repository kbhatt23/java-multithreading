package com.learning.multithreading.threadmethods;

import com.learning.multithreading.util.ThreadUtil;

public class InterruptUsingIsInterruptedFlag {
	public static void main(String[] args) {
		Runnable r = () -> {
			boolean isSucess = true;
			for (int i = 0; i < 100; i++) {
				//default behaviour is if thread is running do not kill on interrupt
				//but if it is sleeping/joining kill it
				//below impletmentation is to kill even if thread was not sleeping/joining
				if (Thread.interrupted()) {
					System.out.println("Thread was interrupted and hence quitting");
					isSucess = false;
					break;
				}

				System.out.println("Thread iteration " + i);
			}
			System.out.println("Thread completes its task succesfully " + isSucess);
		};
		Thread thread = new Thread(r);
		thread.start();
		ThreadUtil.sleep(1);
		// basic fundamental of interruption is that if thread is sleeping or
		// joining/blocked then we call interrupt to kill that thread so that process
		// can be given to another thread
		// thats why if we do not sleep or join in tht run method it do not actually
		// kill the thread's task, it kills only when it was sleeping or joining
		thread.interrupt();
	}
}
