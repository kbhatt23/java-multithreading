package com.learning.multithreading.parallelprogramming;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolThreads {
public static void main(String[] args) {
	ForkJoinPool customForkJoinPool = new ForkJoinPool();
	//default paraleism is size of core
	System.out.println("customForkJoinPool Paralleism "+customForkJoinPool.getParallelism());
	
	
	System.out.println("common pool paralleis is cores -1 :"+ForkJoinPool.getCommonPoolParallelism());
}
}
