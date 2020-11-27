package com.learning.multithreading.baiscs;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ErrorChainPropagation {
	public static void main(String[] args) {
		CompletableFuture<Void> thenAccept = CompletableFuture.supplyAsync(ErrorChainPropagation::findItems)
				.thenApplyAsync(ErrorChainPropagation::printItems)
				.thenApplyAsync(ErrorChainPropagation::transformList)
				.exceptionally(throwable -> {
					System.out.println("exception occurred "+throwable);
					return Arrays.asList("sita-ram");
				})
				.thenApplyAsync(list -> list.stream().map(a -> a.split("-")).flatMap(Arrays::stream).collect(Collectors.toList()))
				.thenAccept(System.out::println)
				;
		
		try {
			thenAccept.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("tasks completed");
	}

	public static List<String> findItems() {
		return Arrays.asList("sita-ram", "radhe-krishna", "uma-shankar", null,"ramaduta-hanuman");
	}

	public static List<String> printItems(List<String> items) {
		items.forEach(System.out::println);
		return items;
	}

	public static List<String> transformList(List<String> items) {
		return items.stream()
					.map(String::toUpperCase)
					.collect(Collectors.toList());
	}
}
