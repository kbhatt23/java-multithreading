package com.learning.multithreading.executors;

import java.util.concurrent.Callable;

public class FactorialCalucaltor implements Callable<Long>{
	private int number;
	
	public  FactorialCalucaltor(int number) {
	this.number=number;
	}

	@Override
	public Long call() throws Exception {
		long result =1;
		for(int i = 1 ; i<=number; i++) {
			result *= i;
			Thread.sleep(100);
		}
		return result;
	}

}
