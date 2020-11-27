package com.learning.mutithreading.chapter1;

public class SequentialMultipleRunners {
public static void main(String[] args) {
	SequentialMultipleRunners obj = new SequentialMultipleRunners();
	System.out.println("Task started");
	obj.runner1();
	obj.runner2();
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
