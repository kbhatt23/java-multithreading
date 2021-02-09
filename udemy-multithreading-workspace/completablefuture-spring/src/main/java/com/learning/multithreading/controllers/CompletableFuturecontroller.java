package com.learning.multithreading.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/completable-future")
public class CompletableFuturecontroller {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CompletableFuture<String> completableFutureBasic() {
		System.out.println("CompletableFuturecontroller.completableFutureBasic started by thread "+Thread.currentThread().getName());
		
		 CompletableFuture<String> responsiveResponse = CompletableFuture.supplyAsync(() ->{
			System.out.println("Responsive task started by thread "+Thread.currentThread().getName());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Responsive task completed by thread "+Thread.currentThread().getName());
			return "jai sita ram";
		});
		 
		 
		 System.out.println("CompletableFuturecontroller returning responsive response by thread "+Thread.currentThread().getName());
		 return responsiveResponse;
	}
}
