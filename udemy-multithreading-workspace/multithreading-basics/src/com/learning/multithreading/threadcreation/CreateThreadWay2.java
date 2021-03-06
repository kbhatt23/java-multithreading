package com.learning.multithreading.threadcreation;

import com.learning.multithreading.util.ThreadUtil;

public class CreateThreadWay2 {
	public static void main(String[] args) {
		Thread t1 = new Thread(new ThreadOne());
		t1.setName("Thread-one");
		//t1.run();
		//we can call thread start once per instance
		//t1.start();
		
		System.out.println("Tasks submitted once");
		
		Thread t2 = new Thread(new ThreadTwo());
		t2.setName("Thread-two");
		t1.start();
		t2.start();
		//t2.run();
		System.out.println("Task submitted again");
	}


	private static class ThreadOne implements Runnable{
		
		@Override
		public void run() {
			System.out.println("Task started by thread "+Thread.currentThread().getName());
			//if method is interrupted that we wont be able to see here as catch block is already cpatrure there
			for(int i=1; i<= 10 ; i++) {
				System.out.println(Thread.currentThread().getName() + " completed iteration "+i );
			}
			ThreadUtil.sleep(1000);
			System.out.println("Task completed by thread "+Thread.currentThread().getName());
		}
	}
	
	private static class ThreadTwo implements Runnable{
		
		@Override
		public void run() {
			System.out.println("Task started by thread "+Thread.currentThread().getName());
			//if method is interrupted that we wont be able to see here as catch block is already cpatrure there
			for(int i=1; i<= 10 ; i++) {
				System.out.println(Thread.currentThread().getName() + " completed iteration "+i );
			}
			ThreadUtil.sleep(1000);
			System.out.println("Task completed by thread "+Thread.currentThread().getName());
		}
	}
	}


