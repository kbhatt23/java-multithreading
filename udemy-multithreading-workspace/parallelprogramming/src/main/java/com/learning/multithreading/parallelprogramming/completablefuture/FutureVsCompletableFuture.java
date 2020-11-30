package com.learning.multithreading.parallelprogramming.completablefuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
//features of reactive programming
// non blocking responsive code
//if no subscriber is locked task will be ignored -> cold streams
public class FutureVsCompletableFuture {
	public static void main(String[] args) {

		System.out.println("Starting main task");
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		Future<String> future1 = service.submit(() -> {
			System.out.println("task started by "+Thread.currentThread().getName());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "jai shree ram";
		});
		String future1response = null;
		try {
			future1response= future1.get();
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		service.shutdown();
		String finalRes = future1response.toUpperCase();
		System.out.println("finalRes "+finalRes);
		System.out.println("completing main task");
		//if we do not join/get on chain it will be ignored jus tlike reactive programming
		
		System.out.println("even reactive future completed the task");
	}
}
