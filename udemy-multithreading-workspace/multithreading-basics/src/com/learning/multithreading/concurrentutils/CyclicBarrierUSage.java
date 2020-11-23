package com.learning.multithreading.concurrentutils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.learning.multithreading.util.ThreadUtil;

public class CyclicBarrierUSage {
	//it represent master-slave system
	//where big tasks are divided into samller tasks and each slave thread do the task in slave thread and await
	//once all threads are done flow goes to master , master completes its job and salve await method gets unblocked
	//we can do some more task after first await in slave that goes thorught ehs above steps again
	public static void main(String[] args) {
		
		Runnable masterTask = () -> {
			System.out.println("Master task started");
			ThreadUtil.sleep(1000);
			System.out.println("Master task completed");
		};
		//assuming we will have 4 threads for slaves/parties
		int parties = 4;
		CyclicBarrier cyclicBarrier = new CyclicBarrier(parties, masterTask);
		SlaveTask slaveTask = new SlaveTask(cyclicBarrier);
		for(int i=1 ; i<=parties; i++) {
			String name = "slave:"+i;
			 new Thread(slaveTask, name).start();
		}
		
		
	}
	
	private static class SlaveTask implements Runnable{
		
		private CyclicBarrier cyclicBarrier;
		private SlaveTask(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier=cyclicBarrier;
		}
		public void run() {
			System.out.println("Slave task started by thread "+Thread.currentThread().getName());
			ThreadUtil.sleep(2000);
			System.out.println("Slave task completed by thread "+Thread.currentThread().getName());
			//slave completes task and await until master completes its tasl
			//if we add more code below , once master completes its task flow again comes back to all slaves
			//and once slaves complet task again master task will be reevaluated, thats why cyclic barrier
			
			//slave waiting
			try {
				cyclicBarrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Slave another task started by thread "+Thread.currentThread().getName());
			ThreadUtil.sleep(2000);
			System.out.println("Slave another task completed by thread "+Thread.currentThread().getName());
			
			//slave waiting again 
			try {
				cyclicBarrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Slave no need to wait now for thread "+Thread.currentThread().getName());
		}
	}
}
