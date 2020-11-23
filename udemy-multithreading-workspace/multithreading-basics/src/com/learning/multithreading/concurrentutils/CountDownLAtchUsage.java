package com.learning.multithreading.concurrentutils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.learning.multithreading.util.ThreadUtil;

public class CountDownLAtchUsage {
public static void main(String[] args) {
	//count down latch is thread safe
	//so even if we ahre this among threads there will be no race condition
	
	CountDownLatch latch = new CountDownLatch(2);
	
	List<Runnable> dependentTasks = createDepenntTasks(latch);
	
	//we know that there are 2 thread pools
	ExecutorService svc = Executors.newFixedThreadPool(3);
	List<Future<?>> futures = dependentTasks.stream()
				  .map(task -> svc.submit(task))
				  .collect(Collectors.toList())
				  ;
	
	futures.stream()
			.map(future -> {
				try {
					return future.get();
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}).forEach(System.out::println);
			;
			
			System.out.println("Thanks countdownlatch by main method");
	
}

private static List<Runnable> createDepenntTasks(CountDownLatch latch) {
	Runnable dependentTask1 = () -> {
		System.out.println("Task started by dependent task1");
		ThreadUtil.sleep(1000);
		System.out.println("Task completed by dependent task1");
		//latch is shared but still wont cause in consistencey as it is thread safe
		latch.countDown();
	};
	
	Runnable dependentTask2 = () -> {
		System.out.println("Task started by dependent task2");
		ThreadUtil.sleep(2000);
		System.out.println("Task completed by dependent task2");
		//latch is shared but still wont cause in consistencey as it is thread safe
		latch.countDown();
	};
	
	Runnable taskThatDependensOnOthers = () -> {
		System.out.println("manager waiting for other taks to be completed");
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Task started by manager");
		ThreadUtil.sleep(1000);
		System.out.println("Task completed by manager");
		//latch is shared but still wont cause in consistencey as it is thread safe
	};
	
	
	List<Runnable> dependentTasks = Arrays.asList(dependentTask1,dependentTask2,taskThatDependensOnOthers);
	return dependentTasks;
}
}
