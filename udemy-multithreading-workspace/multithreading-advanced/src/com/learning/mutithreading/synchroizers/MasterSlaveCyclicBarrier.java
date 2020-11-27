package com.learning.mutithreading.synchroizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MasterSlaveCyclicBarrier {
public static void main(String[] args) {
	Runnable master = () ->{
		System.out.println("Master says thanks to slaves");
	};
	//master will wait for another 3 threads to be completed
	CyclicBarrier barrier = new CyclicBarrier(3, master);
	
	
	Runnable slaveTask = () ->{
			//first do the task then await
			//same as in countdown latch we do the task and thenc ountdown
		System.out.println("Slave task started by thread "+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Slave task completed by thread "+Thread.currentThread().getName());
		try {
			//reduces the count of parties and also this method blocks slave thread
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		//adding cycle
		
		System.out.println("Slave task again started by thread "+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Slave task again completed by thread "+Thread.currentThread().getName());
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
		System.out.println("Slave task says welcome to master by thread "+Thread.currentThread().getName());
	};
	//thre slaves , see we need not to start master thread and synchronize /signal/waitnotify with slaves
	new Thread(slaveTask).start();
	new Thread(slaveTask).start();
	new Thread(slaveTask).start();
	
}
}
