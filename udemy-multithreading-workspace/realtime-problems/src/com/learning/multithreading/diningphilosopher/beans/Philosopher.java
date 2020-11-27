package com.learning.multithreading.diningphilosopher.beans;

import com.learning.multithreading.diningphilosopher.util.PhilosopherUtil;
import com.learning.multithreading.diningphilosopher.util.ThreadUtil;

public class Philosopher {
	// assuming each of the philosopher have unique names
	private String name;

	private Fork leftFork;

	private Fork rightFork;
	
	private int eatingCount;

	public Philosopher(String name, Fork leftFork, Fork rightFork) {
		super();
		this.name = name;
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}

	public Fork getLeftFork() {
		return leftFork;
	}

	public void setLeftFork(Fork leftFork) {
		this.leftFork = leftFork;
	}

	public Fork getRightFork() {
		return rightFork;
	}

	public void setRightFork(Fork rightFork) {
		this.rightFork = rightFork;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Philosopher [name=" + name + ", leftFork=" + leftFork + ", rightFork=" + rightFork + "]";
	}

	public void eatAndThink() {
		while (true) {
			boolean aquireLock =false;
			try {
			 aquireLock = PhilosopherUtil.aquireLock(leftFork.getLock(), rightFork.getLock());
			if(aquireLock) {
				ThreadUtil.sleep(1000);
				System.out.println("Philosopher "+name+" is eating food using thread "+Thread.currentThread().getName());
				eatingCount++;
			}else {
				ThreadUtil.sleep(2000);
				System.out.println("Philosopher "+name+" is thinking");
			}
			}finally {
				if(aquireLock) {
					leftFork.getLock().unlock();
					rightFork.getLock().unlock();
				}
			}
		}
	}
	
	public int getEatingCount() {
		return eatingCount;
	}

}
