package com.learning.multithreading.concurrentutils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

public class ForkjoinpoolBasic {
public static void main(String[] args) {
	//default numbe rof threads are equels to number of cores-1
	//1 is left for main thread
	ForkJoinPool pool = ForkJoinPool.commonPool();
	System.out.println("default pool: "+pool);
	System.out.println("default common pool size "+pool.getParallelism());
	//1 processor is left for usage of main thread
	System.out.println("toatl avaiable processors "+Runtime.getRuntime().availableProcessors());

	ForkJoinTask<Integer> submit = pool.submit(() -> IntStream.range(1, 20).
				parallel().reduce(1, (result,item) -> result*item));
	
	//withour for each ordered print items will be random
	ForkJoinTask<?> VoidFuture = pool.submit(() -> IntStream.range(1, 20).parallel().forEachOrdered(System.out::println));

	try {
		Integer integer = submit.get();
		System.out.println("multiplication result: " +integer);
		VoidFuture.get();
		pool.shutdown();
	} catch (InterruptedException | ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
