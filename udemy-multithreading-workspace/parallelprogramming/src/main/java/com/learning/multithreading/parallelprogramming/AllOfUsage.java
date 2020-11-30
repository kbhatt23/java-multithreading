package com.learning.multithreading.parallelprogramming;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class AllOfUsage {
public static void main(String[] args) {
	AnyOfUsage service= new AnyOfUsage();
	AllOfUsage obj =new AllOfUsage();
	obj.usingStreams(service);
	System.out.println("==============");
	
	obj.usingCompletableFuture(service);
	
}
	private void usingCompletableFuture(AnyOfUsage service) {
		System.out.println("future way started");
		List<CompletableFuture<String>> listOffutures = Arrays.asList(service.service1(),service.service2(),service.service3());
		CompletableFuture<Void> allOf = CompletableFuture.allOf(listOffutures.toArray(new CompletableFuture[listOffutures.size()]));
		//creates non blocking pipeline
		CompletableFuture<List<String>> thenAccept = allOf.thenApply(future ->{
			return listOffutures.stream()
						 .map(CompletableFuture::join)
						 .collect(Collectors.toList());
		});
		System.out.println("pipline chaining done, now doing something else");
		
		List<String> outputs = thenAccept.join();
		System.out.println("output of all the futures: "+outputs);
		
	}
	public void usingStreams(AnyOfUsage service) {
		System.out.println("Traditional way started");
		List<CompletableFuture<String>> listOffutures = Arrays.asList(service.service1(),service.service2(),service.service3());
		
		//this is blocking, until final result is done we cna not do anything else by using main thread
		List<String> outputs = listOffutures.stream()
					 .map(CompletableFuture::join)
					 .collect(Collectors.toList());
		
		System.out.println("output of all the futures: "+outputs);
	
	}
}
