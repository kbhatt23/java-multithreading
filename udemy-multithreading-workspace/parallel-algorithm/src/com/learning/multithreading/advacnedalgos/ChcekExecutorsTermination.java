package com.learning.multithreading.advacnedalgos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ChcekExecutorsTermination {
public static void main(String[] args) {
	ExecutorService service = Executors.newFixedThreadPool(2);
	
	service.execute(() -> {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	});
	service.shutdown();
	try {
		service.awaitTermination(60, TimeUnit.SECONDS);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("all task completed");
	service.shutdownNow();
	
}
}
