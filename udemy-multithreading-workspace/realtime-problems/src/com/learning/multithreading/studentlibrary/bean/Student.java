package com.learning.multithreading.studentlibrary.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import com.learning.multithreading.diningphilosopher.util.ThreadUtil;

public class Student {

	private int id;
	
	public Map<Integer, Integer> getBookCount() {
		return bookCount;
	}

	private Map<Integer, Integer> bookCount;

	public Student(int id) {
		this.id = id;
		bookCount=new HashMap<Integer, Integer>();
	}

	public void issueBook(Book book) throws InterruptedException {
		// we wont be waiting infinitely
		// we can come back later if book was isue to some one else
		Lock lock = book.getLock();
		boolean tryLock = false;
		try {
			tryLock = lock.tryLock(10, TimeUnit.MILLISECONDS);
			if (tryLock) {
				System.out.println("Student " + this + " got issue of book " + book);
				readBook(book);
			} else {
				System.out.println("Student " + this + " unable to issue book " + book);
			}
		} finally {
			if (tryLock) {
				System.out.println("Student " + this + " returning book " + book);
				lock.unlock();
			}
		}
	}

	public void readBook(Book book) throws InterruptedException {
		int bookId = book.getId();
		if(bookCount.containsKey(bookId)) {
			bookCount.put(bookId,1+bookCount.get(bookId));
		}else {
			bookCount.put(bookId,1);
		}
		System.out.println("Student " + this + " started reading book " + book);
		Thread.sleep(2000);
		System.out.println("Student " + this + " cfinished reading reading book " + book);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + "]";
	}
	

}
