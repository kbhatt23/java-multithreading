package com.learning.mutithreading.chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemapphoreConnectionPool {
	public static void main(String[] args) {

		int poolSize = 2;
		//this way multiple threads can access and run simultaneously like a thread pool
		Semaphore sempaphore = new Semaphore(poolSize);

		Runnable task = () -> {
			try {
				sempaphore.acquire();
				System.out.println("Task started by Thread "+Thread.currentThread().getName());
				
				Thread.sleep(2000);
				System.out.println("Task completed by Thread "+Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				sempaphore.release();
			}
		};
		List<Thread> threads = new ArrayList<Thread>();
		for(int i=0;i<8;i++) {
			threads.add(new Thread(task));
		}
		
		threads.forEach(Thread::start);
	}

}
