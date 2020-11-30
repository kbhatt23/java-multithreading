package com.learning.multithreading.parallelprogramming.completablefuture.service;

import java.util.concurrent.CompletableFuture;

import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class HelloWorldService {
	// suplier usage
	public String helloWorld() {
		System.out.println("helloWorld invoked by " + Thread.currentThread().getName());
		CommonUtil.sleep(1000);
		return "jai shree ram";
	}

	// function usage
	public String updateHelloWorld(String input) {
		System.out.println("updateHelloWorld invoked by " + Thread.currentThread().getName());
		return input.toUpperCase();
	}

	// thencpompose usage
	public CompletableFuture<String> updateHelloWorldCompose(String input) {
		System.out.println("updateHelloWorldCompose invoked by " + Thread.currentThread().getName());
		return CompletableFuture.supplyAsync(() -> input.toUpperCase());
	}

	// consumption usage
	public void consumeHelloWorld(String input) {
		System.out.println("consumeHelloWorld invoked by " + Thread.currentThread().getName());
		System.out.println("output message " + input);
	}

	// function usage
	public int updateHelloWorldLength(String input) {
		return input.length();
	}

	public String sayHello() {
		System.out.println("sayHello invoked by " + Thread.currentThread().getName());
		CommonUtil.sleep(1000);
		return "hello";
	}

	public String sayWorld() {
		System.out.println("sayWorld invoked by " + Thread.currentThread().getName());
		CommonUtil.sleep(1000);
		return "world";
	}

	public String property3() {
		System.out.println("property3 invoked by " + Thread.currentThread().getName());
		CommonUtil.sleep(1000);
		return "lakshmi";
	}

	public String property4() {
		System.out.println("property4 invoked by " + Thread.currentThread().getName());
		CommonUtil.sleep(1000);
		return "narayan";
	}
}
