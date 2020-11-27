package com.learning.mutithreading.chapter1;

public class ParallelMultipleRunners {
public static void main(String[] args) {
	ParallelMultipleRunners obj = new ParallelMultipleRunners();
	System.out.println("Task started");
	
	new Thread(obj::runner1).start();
	System.out.println("Task submitted to runner1");
	
	new Thread(obj::runner2).start();
	System.out.println("Task submitted to runner2");
	
	
	System.out.println("Task ended");
}

private  void runner1() {
	for(int i=0 ; i <10 ; i++) {
		System.out.println("Runner1 called for index "+(i+1));
	}
}

private  void runner2() {
	for(int i=0 ; i <10 ; i++) {
		System.out.println("Runner2 called for index "+(i+1));
	}
}
}
