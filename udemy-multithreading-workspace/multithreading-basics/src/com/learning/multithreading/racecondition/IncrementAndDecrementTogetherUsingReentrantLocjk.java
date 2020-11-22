package com.learning.multithreading.racecondition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.learning.multithreading.util.ThreadUtil;

public class IncrementAndDecrementTogetherUsingReentrantLocjk {

	private int i;
	
	//even using different locks will cause race condition
	//as one thread for incrmenet is waiting but decrment thread is not waiting during that incrment operation
	private Lock lockIncrement = new ReentrantLock();
	
	//one lock is needed
	//private Object lockDecrement = new Object();
	
	public /* synchronized */ void increment() {
		
			System.out.println("incrementing");
			boolean lockAquired =false;
			//enclosing onl critical section
			try {
				 lockAquired = ThreadUtil.aquireReentrantLockUnBlocked(lockIncrement);
				if(lockAquired)
					i++;
			}finally {
				//we should always unlcok even in case of exception
				//we should not unlock a lock if lock was not aquired to save from runtime exception
				if(lockAquired)
					lockIncrement.unlock();
			}
	}
	
	public /* synchronized */void decrement() {
			System.out.println("decrementing");
			boolean lockAquired =false;
			try {
				 lockAquired = ThreadUtil.aquireReentrantLockUnBlocked(lockIncrement);
					if(lockAquired)
						i--;
			}finally {
				if(lockAquired)
					lockIncrement.unlock();
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
		IncrementAndDecrementTogetherUsingReentrantLocjk obj = new IncrementAndDecrementTogetherUsingReentrantLocjk();
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
