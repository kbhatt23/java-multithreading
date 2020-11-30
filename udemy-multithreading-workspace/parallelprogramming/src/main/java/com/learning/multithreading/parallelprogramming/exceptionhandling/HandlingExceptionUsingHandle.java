package com.learning.multithreading.parallelprogramming.exceptionhandling;

import java.util.concurrent.CompletableFuture;

import com.learning.multithreading.parallelprogramming.completablefuture.service.HelloWorldService;

public class HandlingExceptionUsingHandle {
	
	private static HelloWorldService service = new HelloWorldService();
	public static void main(String[] args) {

		CompletableFuture<Void> thenAccept = CompletableFuture.supplyAsync(service::helloWorld)
						.thenApply(service::updateHelloWorldLength)
						.thenApply(size -> size/0)
						//.thenApply(size -> size)
						.handle((size,ex) ->{
							if (ex != null) {
								System.out.println("exception occured " + ex);
								return -1;
							} else {
								return size;
							}
						})
						.thenAccept(System.out::println)
						;
		
		thenAccept.join();
	}
}
