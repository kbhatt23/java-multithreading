package com.learning.multithreading.studentlibrary.bean;

import java.util.concurrent.locks.Lock;

public class Book {

	private int id;
	
	private Lock lock;

	public Book(int id, Lock lock) {
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
		return "Book [id=" + id +  "]";
	}
	
	
}
