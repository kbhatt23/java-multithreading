package com.learning.multithreading.threadmethods;

import com.learning.multithreading.util.ThreadUtil;

public class YieldUsage {
	public static void main(String[] args) {
		//considering the priority same as main
		Thread backGrundProcess  = new Thread(new Worker1(), "bulk-process");
		backGrundProcess.start();
		
		//showcasting so that scehduler picks buld process first
		ThreadUtil.sleep(1000);
		Runnable r = () -> {
			System.out.println("Task started by "+Thread.currentThread().getName());
			for(int i=0 ; i< 10 ; i++) {
				System.out.println("running iteration "+i);
			}
			ThreadUtil.sleep(100);
		};
		//same priority as backgroundprocess
		Thread newProcess1  = new Thread(r, "new-process1");
		
		//lower priority process
		Thread newProcess2  = new Thread(r, "new-process2");
		newProcess2.setPriority(4);
		
		newProcess2.start();
		newProcess1.start();
	}
	
	//class represent long running task that yeilds periodically, so that other thread gets chance to execute
	//this thread can get its taks to be exceuted when scheudler picks it from runnable state
	private static class Worker1 implements Runnable{

		@Override
		public void run() {
			Thread currentThread = Thread.currentThread();
			String nickName = currentThread.getName() + " : "+ currentThread.getPriority();
			System.out.println("Task started by " + nickName);
			
			int yieldIndexCount = 0;
			//time consuming task
			for(int i= 0 ; i < 20; i ++,yieldIndexCount++) {
				if(yieldIndexCount == 5) {
					yieldIndexCount=0;
					Thread.yield();
				}
				System.out.println(nickName+" is iterating for index "+i);
				ThreadUtil.sleep(500);
			}
		}
		
	}
}
