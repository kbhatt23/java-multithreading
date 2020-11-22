package com.learning.multithreading.executors;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CallableUsage {
public static void main(String[] args) {
	Callable<String> callable = () -> {Thread.sleep(2000); return "jai shree ram";};

	ExecutorService svc = Executors.newFixedThreadPool(2);
	
	List<Future<String>> futures=IntStream.rangeClosed(1, 8)
	.mapToObj(number -> svc.submit(callable))
	.collect(Collectors.toList())
	;
	
	futures
		.stream()
		.map(future -> {
			try {
				return future.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
		}).forEach(System.out::println);
		;
		
		try {
			svc.awaitTermination(20, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		svc.shutdown();
}

}
