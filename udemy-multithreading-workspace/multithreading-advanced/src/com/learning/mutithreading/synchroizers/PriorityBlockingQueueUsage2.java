package com.learning.mutithreading.synchroizers;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

//it is same as blocking queue in terms of take and put method as both are blocking
// however order is not bsed on insertion  like a queue but it is based on sorting order
public class PriorityBlockingQueueUsage2 {
public static void main(String[] args) {
	BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);
	AtomicInteger counter = new AtomicInteger(100);
	Runnable producer = () ->{
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				int andDecrement = counter.getAndDecrement();
				queue.put(andDecrement);
				System.out.println("Produced item "+andDecrement+" by thread "+Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	new Thread(producer).start();
	new Thread(producer).start();
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e1) {
		e1.printStackTrace();
	}
	
	Runnable consumer = () ->{
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				System.out.println("Consumed item "+queue.take()+" by thread "+Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	new Thread(consumer).start();
	new Thread(consumer).start();
}
}
