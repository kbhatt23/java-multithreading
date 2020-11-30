package com.learning.multithreading.parallelprogramming;

import java.util.concurrent.CompletableFuture;

import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class AnyOfUsage {
	public static void main(String[] args) {
		CommonUtil.startTimer();
		AnyOfUsage obj = new AnyOfUsage();
		System.out.println(obj.findBest());
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
	}

	public String findBest() {
		return (String) CompletableFuture.anyOf(service1(), service2(), service3()).join();
	}

	public CompletableFuture<String> service1() {
		return CompletableFuture.supplyAsync(() -> {
			CommonUtil.sleep(2000);
			return "jai uma shankar";
		});
	}

	public CompletableFuture<String> service2() {
		return CompletableFuture.supplyAsync(() -> {
			CommonUtil.sleep(1000);
			return "jai lakshmi narayan";
		});

	}

	public CompletableFuture<String> service3() {
		return CompletableFuture.supplyAsync(() -> {
			CommonUtil.sleep(1500);
			return "jai sita ram";
		});
	}
}
