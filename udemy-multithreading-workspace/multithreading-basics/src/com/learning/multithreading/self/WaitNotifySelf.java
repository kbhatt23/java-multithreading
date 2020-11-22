package com.learning.multithreading.self;

import com.learning.multithreading.util.ThreadUtil;

public class WaitNotifySelf {
public static void main(String[] args) {
	Object lock = new Object();
	Thread t1 = new Thread( new ThreadOne(lock));
	Thread t2 = new Thread(new ThreadTwo(lock));
	
	t1.setName("first");
	t2.setName("second");
	t1.start();
	
	//forcing scheduler to start thread one , whihc will awuire the lock and will wait until some one else aquires it, completes the synchronization block and notify to reqruire back
	ThreadUtil.sleep(1);
	t2.start();
	
	ThreadUtil.join(t1);ThreadUtil.join(t2);
	System.out.println("Main thread completed");
	
}

private static class ThreadOne implements Runnable{

	private Object lock;
	private ThreadOne(Object lock) {
		this.lock=lock;
	}
	@Override
	public void run() {
		System.out.println("Task started by "+Thread.currentThread().getName());
		synchronized (lock) {
			System.out.println("lock aquired by "+Thread.currentThread().getName());
			try {
				//goes to sleep until some one else notify, other trhread will start and aquire this free lock now
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("lock reaquired by "+Thread.currentThread().getName());
		}
		System.out.println("Task completed by "+Thread.currentThread().getName());
	}
	
}

private static class ThreadTwo implements Runnable{

	private Object lock;
	private ThreadTwo(Object lock) {
		this.lock=lock;
	}
	@Override
	public void run() {
		System.out.println("Task started by "+Thread.currentThread().getName());
		synchronized (lock) {
			System.out.println("lock aquired by "+Thread.currentThread().getName());
			lock.notifyAll();
			System.out.println("lock released by "+Thread.currentThread().getName());
		}
		System.out.println("Task completed by "+Thread.currentThread().getName());
	}
	
}


}



