package com.learning.multithreading.baiscs;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class LazyCompletableFutureUsage {
public static void main(String[] args) {
	CompletableFuture<Integer> future = new CompletableFuture<Integer>();
	CompletableFuture<Integer> thenAccept = future.thenApply(i -> i+1)
		  .thenApply(i -> i*2)
		  //.thenAccept(System.out::println)
	;
	startLazyPipeline(future, thenAccept);
}

private static void startLazyPipeline(CompletableFuture<Integer> future, CompletableFuture<Integer> thenAccept) {
	future.complete(9);
	//thenAccept.complete(9);
	try {
		System.out.println(thenAccept.get());
	} catch (InterruptedException | ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
