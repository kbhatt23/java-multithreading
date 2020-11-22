package com.learning.multithreading.threadmethods;

import com.learning.multithreading.util.ThreadUtil;

public class CreateThreadGroup {
public static void main(String[] args) {
	//since main thread created this group, parent threadgroup of this thread group will be main's threadgroup
	ThreadGroup tg = new ThreadGroup("custom one");
	System.out.println("parent of threadgroup '"+tg.getName()+"' is '"+tg.getParent().getName()+"'");
	
	ThreadGroup another = new ThreadGroup(tg, "custom two");
	System.out.println("parent of threadgroup '"+another.getName()+"' is '"+another.getParent().getName()+"'");
	
	
	Runnable target= () ->  {
		
		while(true) {
			try {
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName()+ " Running the task");
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName()+" got interrupted");
				break;
			}
		}
	};
	Thread t1 = new Thread(tg, target, "part-one");
	Thread t2 = new Thread(tg, target, "part-two");
	Thread t3 = new Thread(tg, target, "part-three");
	
	t1.start();t2.start();t3.start();
	ThreadUtil.sleep(1000);
	//interrupt the whole group
	tg.interrupt();
	
	
}
}
