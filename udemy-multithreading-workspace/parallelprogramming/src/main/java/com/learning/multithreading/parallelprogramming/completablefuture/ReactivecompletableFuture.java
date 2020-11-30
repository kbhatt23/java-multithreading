package com.learning.multithreading.parallelprogramming.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
//features of reactive programming
// non blocking responsive code
//if no subscriber is locked task will be ignored -> cold streams
public class ReactivecompletableFuture {
	public static void main(String[] args) {

		System.out.println("Starting main task");
		CompletableFuture<Void> reactiveChain = CompletableFuture.supplyAsync(() -> {
			System.out.println("task started by "+Thread.currentThread().getName());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "jai shree ram";
			//apply async may chose to use another thread if the thread to execute supply async was not free
		}).thenApplyAsync(str -> {System.out.println("upper case task started by "+Thread.currentThread().getName()); return str.toUpperCase();})
		.thenAccept(System.out::println)
		;
		
		System.out.println("completing main task");
		//if we do not join/get on chain it will be ignored jus tlike reactive programming
		
		try {
			reactiveChain.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("even reactive future completed the task");
	}
}
