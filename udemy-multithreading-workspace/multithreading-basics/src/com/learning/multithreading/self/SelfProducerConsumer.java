package com.learning.multithreading.self;

import java.util.ArrayList;
import java.util.List;

import com.learning.multithreading.util.ThreadUtil;

public class SelfProducerConsumer {
	public static void main(String[] args) {
		Object lock = new Object();
		List<Integer> items = new ArrayList<>();

		Thread producer = new Thread(new Producer(lock, items));
		Thread consumer = new Thread(new Consumer(lock, items));
		producer.start();
		ThreadUtil.sleep(10);
		consumer.start();

	}

	private static class Producer implements Runnable {

		private Object lock;
		private List<Integer> items;
		private static int MAX_LIMIT = 10;

		private Producer(Object lock, List<Integer> items) {
			this.lock = lock;
			this.items = items;
		}

		@Override
		public void run() {
			int index = 0;
			while (true) {
				//speed of production
				ThreadUtil.sleep(100);
				synchronized (lock) {
					while (items.size() == MAX_LIMIT) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println("Production started for " + (++index));
					items.add(index);
					lock.notify();
				}
			}
		}

	}

	private static class Consumer implements Runnable {

		private Object lock;
		private List<Integer> items;

		private Consumer(Object lock, List<Integer> items) {
			this.lock = lock;
			this.items = items;
		}

		int index = 0;

		@Override
		public void run() {
			while (true) {
				//speed of consumption
				ThreadUtil.sleep(50);
				synchronized (lock) {
					while (items.size() == 0) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					++index;
					Integer remove = items.remove(0);
					System.out.println("Consumption started for " + index  + " with value "+remove);
					lock.notify();
				}
			}
		}

	}
}
