package com.learning.multithreading.concurrentutils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.learning.multithreading.util.ThreadUtil;

public class ArrayBlockingQueueUsage {
public static void main(String[] args) {

	BlockingQueue<Integer> queue= new ArrayBlockingQueue<>(10);
	
	Runnable producer = () -> {
		int item = 0;
		while(true) {
			ThreadUtil.sleep(500);
			try {item++;
				queue.put(item);
				System.out.println("Producer adding item "+item);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	Runnable consumer = () -> {
		int item = 1;
		while(true) {
			ThreadUtil.sleep(100);
			try {
				System.out.println("Consumer fetching item "+queue.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			queue.offer(item);
			item++;
		}
	};
	
	new Thread(producer).start();
	new Thread(consumer).start();
}
}
