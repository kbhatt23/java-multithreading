package com.learning.mutithreading.executors;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SingleThreadExecutorUSage {
public static void main(String[] args) {
	//single thread executor creates single thread and if we submit more than one runnable task
	//it executes one by one sequentially,
	//howeve main thread will get unblocked to do other tasks
	
	
	ExecutorService svc = Executors.newSingleThreadExecutor();
	
	List<Future<?>> futures = IntStream.rangeClosed(1, 5)
		.mapToObj(Worker::new)
		.map(worker -> svc.submit(worker))
		.collect(Collectors.toList())

		;
	
	System.out.println("All task submitted by "+Thread.currentThread().getName());
	
	svc.shutdown();
	
	//will interrupt the threads
	//even though tasks are not completed all threads will get interrupted
	//svc.shutdownNow();
	
}

private static class Worker implements Runnable{
	private int id;
	
	private Worker(int id) {
		this.id=id;
	}

	@Override
	public void run() {

		System.out.println("Task started for worked id "+this.id+" using thread "+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Task completed for worked id "+this.id+" using thread "+Thread.currentThread().getName()); 
	}
}
}
