package com.learning.mutithreading.chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerUsage {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		List<Integer> items = new ArrayList<>();

		Thread producer = new Thread(new Producer(lock, items, condition));
		Thread consumer = new Thread(new Consumer(lock, items, condition));
		producer.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		consumer.start();

	}

	private static class Producer implements Runnable {

		private Lock lock;
		private List<Integer> items;
		private static int MAX_LIMIT = 10;
		private Condition condition;

		private Producer(Lock lock, List<Integer> items, Condition condition) {
			this.lock = lock;
			this.items = items;
			this.condition = condition;
		}

		@Override
		public void run() {
			int index = 0;
			while (true) {
				// speed of production
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					lock.lock();
					while (items.size() == MAX_LIMIT) {
						try {
							condition.await();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println("Production started for " + (++index));
					items.add(index);
					condition.signalAll();

				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					lock.unlock();
				}
			}
		}

	}

	private static class Consumer implements Runnable {

		private Lock lock;
		private List<Integer> items;
		private Condition condition;

		private Consumer(Lock lock, List<Integer> items, Condition condition) {
			this.lock = lock;
			this.items = items;
			this.condition = condition;
		}

		int index = 0;

		@Override
		public void run() {
			while (true) {
				// speed of consumption
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					lock.lock();
					while (items.size() == 0) {
						try {
							condition.await();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					++index;
					Integer remove = items.remove(0);
					System.out.println("Consumption started for " + index + " with value " + remove);
					condition.signalAll();
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					lock.unlock();
				}

			}
		}

	}
}
