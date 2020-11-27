package com.learning.multithreading.baiscs;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.learning.multithreading.util.ThreadUtil;

public class AnyOfUsage {
public static void main(String[] args) {

	CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {ThreadUtil.sleep(1000);System.out.println("returning future1");return "jai shree ram";});
	
	CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {ThreadUtil.sleep(500);System.out.println("returning future2");return "jai radhe krishna";});
	
	CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {ThreadUtil.sleep(1500);System.out.println("returning future3");return "jai uma shankar";});
	
	CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1,future2,future3);
	
	try {
		System.out.println("real result found "+anyOf.get());
	} catch (InterruptedException | ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
