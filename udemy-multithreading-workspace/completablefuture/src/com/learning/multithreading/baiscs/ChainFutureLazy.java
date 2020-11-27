package com.learning.multithreading.baiscs;

import java.util.concurrent.CompletableFuture;

import com.learning.multithreading.util.ThreadUtil;

//one challenge is if the pipeline is taking more time to consume and we are not using .get method
//then if main method is gone the accept method chain is ignored
public class ChainFutureLazy {
public static void main(String[] args) {
		long start = System.currentTimeMillis();
		CompletableFuture.supplyAsync(() ->{
			ThreadUtil.sleep(2000);
			return "jai shree ram";
		})
		.thenApplyAsync((str) -> {
			ThreadUtil.sleep(2000);
			return str.toUpperCase();
		})
.thenAcceptAsync(result -> System.out.println("final result "+result))
		
		;		
		//completely non blocking
		//now we can do any other things
		System.out.println("both tasks submitted at once to future");
		
		//ThreadUtil.sleep(5000);
		
		long end = System.currentTimeMillis();
		System.out.println("total time taken "+(end-start));
}
}
