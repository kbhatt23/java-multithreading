package com.learning.multithreading.parallelprogramming;

import java.util.concurrent.CompletableFuture;

//compeltaionstage is very intelligent
//it gives task to another thread only ins upplyasync method
//and any then method will run in main thread only if task was already done before main is executing the line
//if task is not done main thread leave this to the thread which executed supplyasyn task
//meaning in short main thread let other thread to do task just to make itself non blocking
public class WhenStageRunsOnSeeprateThread {
	static int generate() {
		System.out.println("generate method getting executed by " + Thread.currentThread().getName());
		//if we sleep then thenchained method can not be executed by main as it wont remain blocked
		//sleep(10);
		return 23;
	}

	private static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void consume(int number) {
		System.out.println("consume method getting executed by " + Thread.currentThread().getName()+" with value "+number);
	}

	public static void main(String[] args) {

		CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(WhenStageRunsOnSeeprateThread::generate);
		
		//forcing so that other task is done by the time main thread get to execute other chain method
		completableFuture.thenAccept(WhenStageRunsOnSeeprateThread:: consume);
	
		//force sleeping just to ensure other thread can do the task
		
		sleep(20);
	}
}
