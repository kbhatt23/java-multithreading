package com.learning.multithreading.racecondition;

import com.learning.multithreading.util.ThreadUtil;

public class IncrementAndDecrementTogether {

	private int i;
	
	//even using different locks will cause race condition
	//as one thread for incrmenet is waiting but decrment thread is not waiting during that incrment operation
	private Object lockIncrement = new Object();
	
	//one lock is needed
	//private Object lockDecrement = new Object();
	
	public /* synchronized */ void increment() {
		
			System.out.println("incrementing");
			//enclosing onl critical section
			synchronized (lockIncrement) {
			i++;
		}
	}
	
	public /* synchronized */void decrement() {
			System.out.println("decrementing");
			synchronized (lockIncrement) {
			i--;
		}
	}
	
	public /* synchronized */ void incrementBad() {
		synchronized (lockIncrement) {
			for(int a =0 ; a<100000; a++) {
				System.out.println("incrementing");
				i++;
			}
			
		}
	}
	
	public  void decrementBad() {
		synchronized (lockIncrement) {
			for(int a =0 ; a<100000; a++) {
				System.out.println("decrementing");
				i--;
			}
		}
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		IncrementAndDecrementTogether obj = new IncrementAndDecrementTogether();
		Runnable r1 = () -> {for(int i=0; i< 100000 ; i++) {obj.increment();}};
		Runnable r2 = () -> {for(int i=0; i< 100000 ; i++) {obj.decrement();}};

		//Runnable r1 = () -> {obj.incrementBad();};
		//Runnable r2 = () -> {obj.decrementBad();};
		
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
		ThreadUtil.join(t1);
		ThreadUtil.join(t2);
		System.out.println("final value "+obj.i);
		long end = System.currentTimeMillis();
		System.out.println("total time taken "+(end-start));
	}
}
