package com.learning.multithreading.parallelprogramming.completablefuture;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.learning.multithreading.parallelprogramming.completablefuture.service.HelloWorldService;
import com.learning.multithreading.parallelprogramming.util.CommonUtil;

class HelloWorldCombinerTest {
	
	HelloWorldCombiner eg = new HelloWorldCombiner(new HelloWorldService());
	
//	@BeforeAll
//	public void setup() {
//		CommonUtil.resetTimer();
//		CommonUtil.startTimer();
//	}
//	@AfterAll
//	public void calculateTime() {
//		CommonUtil.stopTimer();
//		CommonUtil.timeTaken();
//	}
	@Test
	void combineHelloWorld() {
		CompletableFuture<String> combineHelloWorld = eg.combineHelloWorld();
		
		combineHelloWorld.thenAccept(str -> {
			System.out.println("test combined started");
			assertEquals("hello world", str);
		}).join();
	}
	
	@RepeatedTest(value = 5)
	void manipulateSequntial() {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();
		String response = eg.manipulateSequntial();
		assertEquals("hello world", response);
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
	}
	@RepeatedTest(value = 5)
	void manipulateThenCombined() {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();
		String response = eg.manipulateThenCombined();
		assertEquals("hello world", response);
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
	}
	
	@RepeatedTest(value = 5)
	void combineMultiple() {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();
		CombinedResponseBean combineMultiple = eg.combineMultiple();
		assertEquals("hello", combineMultiple.getProperty1());
		assertEquals("world", combineMultiple.getProperty2());
		assertEquals("lakshmi", combineMultiple.getProperty3());
		assertEquals("narayan", combineMultiple.getProperty4());
		
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
	}
	
	@RepeatedTest(value = 5)
	void combineMultipleSequentially() {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();
		CombinedResponseBean combineMultiple = eg.combineMultipleSequentially();
		assertEquals("hello", combineMultiple.getProperty1());
		assertEquals("world", combineMultiple.getProperty2());
		assertEquals("lakshmi", combineMultiple.getProperty3());
		assertEquals("narayan", combineMultiple.getProperty4());
		
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
	}
	
	@Test
	public void combineThreeFutures() {
		CompletableFuture<String> combineThreeFutures = eg.combineThreeFutures();
		
		combineThreeFutures.thenAccept(str -> {
			System.out.println("assertion ofr 3 futures started");
			assertEquals("hello world : jai shree ram", str);
		}).join();
	}
	@Test
	void thenComposeUsage() {
		CompletableFuture<String> thenComposeUsage = eg.thenComposeUsage();
		thenComposeUsage.thenAccept(str -> assertEquals("HELLO", str)).join();
	}
	
	@Test
	void thenComposeUsageReason() {
		CompletableFuture<CompletableFuture<String>> thenComposeUsage = eg.thenComposeUsageReason();
		thenComposeUsage.thenAccept(str -> assertEquals("HELLO", str.join())).join();
	}

}
