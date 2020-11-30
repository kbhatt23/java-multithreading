package com.learning.multithreading.parallelprogramming.exceptionhandling;

import java.util.concurrent.CompletableFuture;

import com.learning.multithreading.parallelprogramming.completablefuture.service.HelloWorldService;

public class UsingExceptionallyForCompletionstaePipeline {
public static void main(String[] args) {
	HelloWorldService service = new HelloWorldService();
	CompletableFuture<Void> finalchain = CompletableFuture.supplyAsync(service::helloWorld)
					 .thenApply(str ->{
						 System.out.println("first transformation starts for: "+str);
						 boolean isError=true;
						 if(isError)
							 throw new RuntimeException("Exception in first transformation");
						 return str+" first";
					 })
					 .exceptionally(ex -> {
						 System.out.println("Exception in first transformation "+ex);
						 return "";
					 })
					 .thenApply(str ->{
						 System.out.println("second transformation starts for: "+str);
						 boolean isError=true;
						 if(isError)
							 throw new RuntimeException("Exception in second transformation");
						 return str+" second";
					 })
					 .exceptionally(ex -> {
							 System.out.println("Exception in second transformation "+ex);
							 return "";
						 
					 })
					 .thenAccept(response -> System.out.println("final result "+response))
					 
	;
	//waiting thread just for sake 
	finalchain.join();
}
}
