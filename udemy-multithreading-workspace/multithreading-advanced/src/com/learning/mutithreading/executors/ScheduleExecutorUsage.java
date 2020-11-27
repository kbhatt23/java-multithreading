package com.learning.mutithreading.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorUsage {
public static void main(String[] args) {
	//assuming that single thread will do the job
	ScheduledExecutorService svc = Executors.newScheduledThreadPool(1);
	Runnable command = () ->{
		System.out.println("Task started by thread "+Thread.currentThread().getName());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Task completed by thread "+Thread.currentThread().getName());
	};
	
	svc.scheduleAtFixedRate(command, 1000, 3000, TimeUnit.MILLISECONDS);
	
	
}
}
