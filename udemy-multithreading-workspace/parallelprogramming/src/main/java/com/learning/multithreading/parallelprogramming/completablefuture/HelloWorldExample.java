package com.learning.multithreading.parallelprogramming.completablefuture;

import java.util.concurrent.CompletableFuture;

import com.learning.multithreading.parallelprogramming.completablefuture.service.HelloWorldService;

public class HelloWorldExample {
	private HelloWorldService service;

	public HelloWorldExample(HelloWorldService service) {
		super();
		this.service = service;
	}

	public CompletableFuture<String> fetchHelloWorld() {
		return CompletableFuture.supplyAsync(service::helloWorld).thenApply(service::updateHelloWorld);
	}
	
	public CompletableFuture<Integer> fetchHelloWorldLength() {
		return fetchHelloWorld()
				.thenApply(service::updateHelloWorldLength);
	}
	
	public CompletableFuture<Integer> fetchRadnomInteger() {
		return CompletableFuture.supplyAsync(() -> 19);
	}
	//then combined is used to integrate 2 independent futures/statges
	public CompletableFuture<String> combinedFuture(){
		return fetchHelloWorld().thenCombine(fetchRadnomInteger(), (str,length) -> str+":"+length);
	}

	public static void main(String[] args) {
		System.out.println("task started by main");
		HelloWorldExample eg = new HelloWorldExample(new HelloWorldService());
		CompletableFuture<Void> future = eg.fetchHelloWorld().thenAccept(eg.service::consumeHelloWorld);

		System.out.println("task submitted by main");

		future.join();
		System.out.println("everything done");
	}
}
