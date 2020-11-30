package com.learning.multithreading.parallelprogramming.completablefuture;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;

import com.learning.multithreading.parallelprogramming.completablefuture.service.HelloWorldService;

class HelloWorldExampleTest {
	private final HelloWorldService SERVICE = new HelloWorldService();
	private HelloWorldExample eg = new HelloWorldExample(SERVICE);
			
	@Test
	void testHellowrold() {
	CompletableFuture<String> fetchHelloWorld = eg.fetchHelloWorld();
	//chaining but it will get ignored, meaning since main thread is gone, the accenpt method is not even called
	//default is succes in unit until exception is thrown and hence it is success
	fetchHelloWorld.thenAccept(str ->{
		System.out.println("test fetch hello world started");
		assertEquals("JAI SHREE RAM", str);
	}).join();
	}
	
	@Test
	void fetchHelloWorldLength() {
		CompletableFuture<Integer> fetchHelloWorldLength = eg.fetchHelloWorldLength();
		fetchHelloWorldLength.thenAccept(length -> {
			System.out.println("test length hello started");
			assertEquals(13, length);
		}).join();
	}
	
	@Test
	void combinedFuture() {
		CompletableFuture<String> combinedFuture = eg.combinedFuture();
		
		combinedFuture.thenAccept(output ->{
			System.out.println("test then combined started");
			assertEquals("JAI SHREE RAM:19", output);
			
		}).join();
	}
	

}
