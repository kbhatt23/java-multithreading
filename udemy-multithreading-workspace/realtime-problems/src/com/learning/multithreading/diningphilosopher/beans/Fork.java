package com.learning.multithreading.diningphilosopher.beans;

import java.util.concurrent.locks.Lock;

public class Fork {

	private int id;
	private Lock lock;
	public Fork(int id, Lock lock) {
		super();
		this.id = id;
		this.lock = lock;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Lock getLock() {
		return lock;
	}


	public void setLock(Lock lock) {
		this.lock = lock;
	}


	@Override
	public String toString() {
		return "Fork [id=" + id + "]";
	}
	
	
}
