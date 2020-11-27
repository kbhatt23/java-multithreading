package com.learning.multithreading.baiscs;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.learning.multithreading.util.ThreadUtil;

//then combine is used when two indepenednt completable future are running then we merge the result to create another object/pipleine
public class ThenCombineUsage {
public static void main(String[] args) {
	CompletableFuture<String> future1= getFuture1();
	System.out.println("submitted task 1");
	
	CompletableFuture<String> future2 = getFuture2();
	System.out.println("submitted task 2");
	
	//combined pipeline
	CompletableFuture<String> thenCombineAsync = future1.thenCombineAsync(future2, (result1,result2) -> {
		ThreadUtil.sleep(100);
		return result1+":"+result2;
	});
	
	System.out.println("submitted combied task ");
	try {
		System.out.println("final result "+thenCombineAsync.get());
	} catch (InterruptedException | ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private static CompletableFuture<String> getFuture2() {
	return CompletableFuture.supplyAsync(() -> {
		ThreadUtil.sleep(200);
		return "jai sita ram";
	});
}

private static CompletableFuture<String> getFuture1() {
	return CompletableFuture.supplyAsync(() -> {
		ThreadUtil.sleep(200);
		return "jai radhe krishna";
	});
}
}
