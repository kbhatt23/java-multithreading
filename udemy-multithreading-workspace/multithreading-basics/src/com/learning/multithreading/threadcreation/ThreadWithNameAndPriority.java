package com.learning.multithreading.threadcreation;

public class ThreadWithNameAndPriority {
	public static void main(String[] args) {
		
		Thread.currentThread().setName("custom main thread");
		//defualt is 5
		//but if we update the priority for main thread , it will update that for threads whihc it creates until forcefully supplied
		Thread.currentThread().setPriority(6);
		Runnable r = () -> {
			try {
				Thread.sleep(1000);
				System.out.println("task complete by " + Thread.currentThread().getName());
				System.out.println("priority of "+ Thread.currentThread().getName()+"thread "+Thread.currentThread().getPriority());
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Thread thread = new Thread(r, "custom-thread");
		thread.start();
		System.out.println("task assigned to thread by "+Thread.currentThread().getName());
	//	System.out.println("lets interrupt");
		//thread.interrupt();
		System.out.println("priority of main thread "+Thread.currentThread().getPriority());
	}
}
