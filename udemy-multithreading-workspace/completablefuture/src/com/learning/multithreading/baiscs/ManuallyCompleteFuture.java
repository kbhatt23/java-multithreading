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

public class ManuallyCompleteFuture {
public static void main(String[] args) {
	Callable<String> task= () -> {
		ThreadUtil.sleep(2000);
		return "jai shree ram";
	};
	runInfuture(task);
	
	runIncompletableFuture(task);
	
}

private static void runIncompletableFuture(Callable<String> task) {
	
	CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
		ThreadUtil.sleep(2000);
		return "jai shree ram";
	} );
	
	//returnign some manual value iof time excceds 500 ms
	ThreadUtil.sleep(500);
	future.complete("jai radha krishna");
	try {
		String result = future.get();
		System.out.println("completablefuture: result found "+result);
	} catch (InterruptedException | ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private static void runInfuture(Callable<String> task) {
	ExecutorService svc = Executors.newSingleThreadExecutor();
	Future<String> futureStr = svc.submit(task);
	//no way to complete it manually
	try {
		
		String result = futureStr.get(1, TimeUnit.SECONDS);
		System.out.println("future: result found "+result);
		svc.shutdown();
	} catch (InterruptedException | ExecutionException | TimeoutException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
