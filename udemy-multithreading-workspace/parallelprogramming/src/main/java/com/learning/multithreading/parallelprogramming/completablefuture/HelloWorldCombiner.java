package com.learning.multithreading.parallelprogramming.completablefuture;

import java.util.concurrent.CompletableFuture;

import com.learning.multithreading.parallelprogramming.completablefuture.service.HelloWorldService;
import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class HelloWorldCombiner {
	private HelloWorldService service;

	public HelloWorldCombiner(HelloWorldService service) {
		super();
		this.service = service;
	}
	
	public CompletableFuture<String> combineHelloWorld(){
		CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() ->service.sayHello());
		CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> service.sayWorld());
		return helloFuture.thenCombine(worldFuture, (str1,str2) -> str1+" "+str2);
	}
	
	public CompletableFuture<String> combineThreeFutures(){
		CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() ->service.sayHello());
		CompletableFuture<String> worldFuture = CompletableFuture.supplyAsync(() -> service.sayWorld());
		CompletableFuture<String> thirdFuture = CompletableFuture.supplyAsync(() -> {
			System.out.println("Task for future 3 started by "+Thread.currentThread().getName());
			CommonUtil.sleep(1000);
			return "jai shree ram";
		});
		return helloFuture.thenCombine(worldFuture, (str1,str2) -> str1+" "+str2)
				.thenCombine(thirdFuture, (concatStr,str) -> {
					return concatStr+" : "+ str;
				})
				;
	}
	
	
	public static void main(String[] args) {
		HelloWorldCombiner obj = new HelloWorldCombiner(new HelloWorldService());
		obj.manipulateSequntial();
		obj.manipulateThenCombined();
	}

	public  String manipulateSequntial() {
		System.out.println("manipulateSequntial: task started by main");
		String hello= service.sayHello();
		
		String world= service.sayWorld();
		String x = hello+" "+world;
		System.out.println("manipulateSequntial:final combined output "+x);
		System.out.println("manipulateSequntial:everything done");
		return x;
	}

	public  String manipulateThenCombined() {
		System.out.println("manipulateThenCombined:task started by main");
		CompletableFuture<String> combineHelloWorld = combineHelloWorld();
		System.out.println("manipulateThenCombined:task assigned by main");
		
		String join = combineHelloWorld.join();
		System.out.println("manipulateThenCombined:combined result found "+join);
		System.out.println("manipulateThenCombined:everything done");
		return join;
	}
	
	public CombinedResponseBean combineMultiple() {
		CompletableFuture<String> property1Future = CompletableFuture.supplyAsync(() -> service.sayHello());
		CompletableFuture<String> property2Future = CompletableFuture.supplyAsync(() -> service.sayWorld());
		CompletableFuture<String> property3Future = CompletableFuture.supplyAsync(() -> service.property3());
		CompletableFuture<String> property4Future = CompletableFuture.supplyAsync(() -> service.property4());
		
		return property1Future.thenCombine(property2Future, (prop1,prop2) ->{
			CombinedResponseBean bean = new CombinedResponseBean();
			bean.setProperty1(prop1);
			bean.setProperty2(prop2);
				return bean;
		}).thenCombine(property3Future, (bean,prop3) -> {bean.setProperty3(prop3); return bean;})
		  .thenCombine(property4Future, (bean,prop4) -> {bean.setProperty4(prop4); return bean;})
		  .join()
		;
	}
	public CombinedResponseBean combineMultipleSequentially() {
		String property1= service.sayHello();
		String property2= service.sayWorld();
		String property3=service.property3();
		String property4=service.property4();
		
		return new CombinedResponseBean(property1, property2, property3, property4);
	}
	
	
	public CompletableFuture<String> thenComposeUsage(){
		return CompletableFuture.supplyAsync(service::sayHello)
						  .thenCompose(service::updateHelloWorldCompose)
		
		;
	}
	
	public CompletableFuture<CompletableFuture<String>> thenComposeUsageReason(){
		return CompletableFuture.supplyAsync(service::sayHello)
						  .thenApply(service::updateHelloWorldCompose)
		
		;
	}
	
}
