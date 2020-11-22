package com.learning.multithreading.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SelfLearningExecutor {
public static void main(String[] args) {
		
		
	//4 threads will do the smae 8 task 
	//first batch will do 4 and then once any one of them is free will pick one of the remaing task and so on
	ExecutorService svc = Executors.newFixedThreadPool(4);
	//instead of creating n number of threads who are just created , taking meory but not actually doing nothing as processors are fixed
	for(int i =0 ; i<8 ; i++) {
		svc.execute(new Task(i+1));
	}
	System.out.println("all tasks are divided");
	
	
	//this also can throw interrupted exception if thread was interrupted
	try {
		svc.awaitTermination(10, TimeUnit.SECONDS);
		svc.shutdown();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private static class Task implements Runnable{
	private int id;
	
	private Task(int id) {
		this.id=id;
	}
	@Override
	public void run() {
		for(int i =0 ; i< 2 ; i++) {
			try {
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName()+ " Running the task with id "+id+ " with iteration "+i );
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName()+" got interrupted for task with id "+id);
				break;
			}
		}		
	}
	
}
}