package com.learning.multithreading.parallelprogramming.exceptionhandling;

import java.util.concurrent.CompletableFuture;

import com.learning.multithreading.parallelprogramming.completablefuture.service.HelloWorldService;

public class UsingWhenCompleteForCompletionstaePipeline {
public static void main(String[] args) {
	HelloWorldService service = new HelloWorldService();
	CompletableFuture<Void> finalchain = CompletableFuture.supplyAsync(service::helloWorld)
					 .thenApply(str ->{
						 System.out.println("first transformation starts for: "+str);
						 boolean isError=false;
						 if(isError)
							 throw new RuntimeException("Exception in first transformation");
						 return str+" first";
					 })
					 .whenComplete((result,ex) -> {
						 if(ex != null)
						 System.out.println("Exception in first transformation "+ex);
						 else
							 System.out.println("found first trasnforation result as "+result);
					 })
					 .thenApply(str ->{
						 System.out.println("second transformation starts for: "+str);
						 boolean isError=false;
						 if(isError)
							 throw new RuntimeException("Exception in second transformation");
						 return str+" second";
					 })
					 .whenComplete((result,ex) -> {
						 if(ex != null)
							 System.out.println("Exception in second transformation "+ex);
						 else
							 System.out.println("found second trasnforation result as "+result);
						 
					 })
					 .thenAccept(response -> System.out.println("final result "+response))
					 
	;
	//waiting thread just for sake 
	finalchain.join();
}
}
