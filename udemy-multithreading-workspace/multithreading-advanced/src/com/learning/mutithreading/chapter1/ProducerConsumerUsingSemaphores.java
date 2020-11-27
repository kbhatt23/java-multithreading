package com.learning.mutithreading.chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
//aka mutex
public class ProducerConsumerUsingSemaphores {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(1, true);
		ArrayList<Integer> items = new ArrayList<Integer>();
		new Thread(new Producer(semaphore, items)).start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(new Consumer(semaphore, items)).start();
		
		//ading another ocnsumer instance
		new Thread(new Consumer(semaphore, items)).start();
	}
	
	private static class Producer implements Runnable{
		private Semaphore semaphore;
		private List<Integer> items;
		private Producer(Semaphore semaphore,List<Integer> items) {
			this.semaphore=semaphore;
			this.items=items;
		}
		@Override
		public void run() {
			int item=1;
			while(true) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				try {
					semaphore.acquire();
					System.out.println("Producer adding item "+item);
					items.add(item);
					item++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					semaphore.release();
				}
			}
		}
		
		
	}
	
	private static class Consumer implements Runnable{
		private Semaphore semaphore;
		private List<Integer> items;
		private Consumer(Semaphore semaphore,List<Integer> items) {
			this.semaphore=semaphore;
			this.items=items;
		}
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				try {
					semaphore.acquire();
					System.out.println("Consumer consuming item "+items.remove(0));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					semaphore.release();
				}
			}
		}
		
		
	}
}
