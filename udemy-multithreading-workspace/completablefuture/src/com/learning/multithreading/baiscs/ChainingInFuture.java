package com.learning.multithreading.baiscs;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.learning.multithreading.util.ThreadUtil;

public class ChainingInFuture {
public static void main(String[] args) {
	
	runChainingInfuture();
	runChainingInCompletableFuture();
}

private static void runChainingInCompletableFuture() {
	long start = System.currentTimeMillis();
	CompletableFuture<String> chainedFuture = CompletableFuture.supplyAsync(() ->{
		ThreadUtil.sleep(2000);
		return "jai shree ram";
	})
	.thenApplyAsync((str) -> {
		ThreadUtil.sleep(2000);
		return str.toUpperCase();
	})
	;
	
	//completely non blocking
	//now we can do any other things
	System.out.println("both tasks submitted at once to future");
	
	try {
		System.out.println("final result :"+chainedFuture.get());
	} catch (InterruptedException | ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	long end = System.currentTimeMillis();
	System.out.println("total time taken "+(end-start));
}

private static void runChainingInfuture() {
	long start = System.currentTimeMillis();
	Callable<String> task1= () -> {
		ThreadUtil.sleep(2000);
		return "jai shree ram";
	};
	
	ExecutorService svc = Executors.newSingleThreadExecutor();
	Future<String> futureStr = svc.submit(task1);
	System.out.println("Task 1 submitted to executor service");
	try {
		//waiting infinite time, this is blocking even the main thread
		String result = futureStr.get();
		System.out.println("future: result found "+result);
		Callable<String> task2= () -> {
			ThreadUtil.sleep(2000);
			return result.toUpperCase();
		};
		Future<String> uppercaseFuture = svc.submit(task2);
		
		System.out.println("Task 2 submitted to executor service");
		//blockig call for main thread
		String finalResult = uppercaseFuture.get();
		System.out.println("future: final result found "+finalResult);
		svc.shutdown();
	} catch (InterruptedException | ExecutionException e) {
		e.printStackTrace();
	}
	long end = System.currentTimeMillis();
	System.out.println("total time taken "+(end-start));
}
}
