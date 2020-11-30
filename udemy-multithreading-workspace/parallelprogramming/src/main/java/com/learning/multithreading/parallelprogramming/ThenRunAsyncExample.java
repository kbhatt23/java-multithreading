package com.learning.multithreading.parallelprogramming;

import java.util.concurrent.CompletableFuture;

import com.learning.multithreading.parallelprogramming.completablefuture.service.HelloWorldService;

public class ThenRunAsyncExample {
public static void main(String[] args) {
	//we know then apply/then accept will be blocking
	//as it can only be executed when the rpevios step is done
	HelloWorldService svc =new HelloWorldService();
	CompletableFuture.supplyAsync(svc::helloWorld)
				.thenApplyAsync(svc::updateHelloWorld)
				.thenAccept(System.out::println)
				.thenRunAsync(() -> System.out.println("jai shree ram from run asyn by thread "+Thread.currentThread().getName()))
				.join();
	
	
}
}
