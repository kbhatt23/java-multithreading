package com.learning.multithreading.baiscs;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.learning.multithreading.util.ThreadUtil;

public class ThenComposeUsage {
public static void main(String[] args) {
	
	CompletableFuture<String> future1 = getFuture1();
	
	CompletableFuture<String> thenCompose = future1.thenCompose(ThenComposeUsage::getFuture2);
	
	CompletableFuture<CompletableFuture<String>> badAppraoach = future1.thenApply(ThenComposeUsage::getFuture2);
	
	try {
		System.out.println("final result "+thenCompose.get());
		System.out.println("bad final result "+badAppraoach.get().get());
	} catch (InterruptedException | ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private static CompletableFuture<String> getFuture2(String input) {
	return CompletableFuture.supplyAsync(() -> {
		ThreadUtil.sleep(200);
		return input+":"+"jai sita ram";
	});
}

private static CompletableFuture<String> getFuture1() {
	return CompletableFuture.supplyAsync(() -> {
		ThreadUtil.sleep(200);
		return "jai radhe krishna";
	});
}
}
