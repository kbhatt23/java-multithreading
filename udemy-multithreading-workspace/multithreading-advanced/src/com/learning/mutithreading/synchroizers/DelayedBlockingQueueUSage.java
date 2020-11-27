package com.learning.mutithreading.synchroizers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedBlockingQueueUSage {
public static void main(String[] args) {
	//it acts same as queue, meaning first come first server, but it gets sorted based on delay time set
	BlockingQueue<Delayed> delayedQueue = new DelayQueue<>();
	
	Delayed one  = new DelayTask(2000, 1);
	Delayed two  = new DelayTask(1000, 2);
	Delayed three  = new DelayTask(7000, 3);
	Delayed four  = new DelayTask(4000, 4);
	try {
		delayedQueue.put(one);
		delayedQueue.put(two);
		delayedQueue.put(three);
		delayedQueue.put(four);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	while(!delayedQueue.isEmpty()) {
		DelayTask item;
		System.out.println("looping items");
		try {
			//blocking emthod, until delay time expires for the latest one to be expired it waits
			item = (DelayTask)delayedQueue.take();
			System.out.println("item found "+item.getValue());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

private static class DelayTask implements Delayed{

	private long duration;
	
	private int value;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private DelayTask(long duration,int value) {
		this.duration=System.currentTimeMillis()+duration;
		this.value=value;
	}
	
	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}


	
	@Override
	public int compareTo(Delayed o) {
		DelayTask otherTask = (DelayTask)o;
		return (this.duration < otherTask.duration) ? -1 : (this.duration == otherTask.duration ? 0:1 ) ;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return this.duration-System.currentTimeMillis();
	}
	
}

}
