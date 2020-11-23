package com.learning.multithreading.concurrentutils;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

import com.learning.multithreading.util.ThreadUtil;

public class SemaphoreUsageConnectionPool {

	public static void main(String[] args) {
		//max siZe of threadpool to run is assumed to be 2
		Semaphore sempahore = new Semaphore(2);
		
		Runnable task = () -> {
			//each thread need to aquire lock first,run task and release the lock
			//this way max size of active threads in pool is maintained
			try {
				sempahore.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Task started by "+Thread.currentThread().getName());
			ThreadUtil.sleep(2000);
			System.out.println("Task completed by "+Thread.currentThread().getName());
			sempahore.release();
		};
		
		for(int i=0 ; i< 8 ; i++) {
			String name = "task:"+i;
			Thread t = new Thread(task, name);
			//start is non blocking
			//System.out.println("submitted thread with name "+name);
			t.start();
		}
	}
}
