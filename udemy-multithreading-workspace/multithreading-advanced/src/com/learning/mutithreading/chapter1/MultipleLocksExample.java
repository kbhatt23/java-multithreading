package com.learning.mutithreading.chapter1;

public class MultipleLocksExample {
	
	private int counter1;
	private int counter2;
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	public /* synchronized */  void increment1() {
		synchronized (lock1) {
			counter1++;
		}
		
	}
	public  synchronized  void increment2() {
		synchronized (lock2) {
			counter2++;
		}
	}
public static void main(String[] args) {
	MultipleLocksExample obj = new MultipleLocksExample();
	
	Runnable incremnet1 = () -> {
		for(int i=0;i < 100000;i++) {
			obj.increment1();
		}
	};
	
	Runnable incremnet2 = () -> {
		for(int i=0;i < 100000;i++) {
			obj.increment2();
		}
	};
	
	long start = System.nanoTime();
	Thread thread1 = new Thread(incremnet1);
	Thread thread2 = new Thread(incremnet1);
	
	Thread thread3 = new Thread(incremnet2);
	Thread thread4 = new Thread(incremnet2);
	thread1.start();
	thread2.start();
	thread3.start();
	thread4.start();
	
	try {
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	System.out.println("final counter1 "+obj.counter1);
	System.out.println("final counter2 "+obj.counter2);
	long end = System.nanoTime();
	System.out.println("toatl time taken "+(end-start));
}
}
