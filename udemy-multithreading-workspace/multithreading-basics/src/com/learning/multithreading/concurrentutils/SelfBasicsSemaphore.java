package com.learning.multithreading.concurrentutils;

import java.util.concurrent.Semaphore;

public class SelfBasicsSemaphore {
public static void main(String[] args) {
	//acts like connection pool of threads
	//if we create 4 threads and sempathore size is 2 at max only 2 threads can get executed
	//once the 2 are done , it will open the chance for other 2 threads
	//the class is also thread safe and hence can be safely shared among threads
	Semaphore sempathore = new Semaphore(2);
	//Semaphore sempathore = new Semaphore(3);
	System.out.println("inital permit size "+sempathore.availablePermits());
	
	try {
		sempathore.acquire();
		sempathore.acquire();
		//once we aquire available permits reduces by 1
		System.out.println("updated permit size "+sempathore.availablePermits());
		//this quire wont happen as all the locks are aquired
		//one of the other thread must deallocate the lock to increase the count back to 1 to allow this aquire
		if(sempathore.availablePermits() == 0) {
			sempathore.release();
		}
		sempathore.acquire();
		System.out.println("updated permit size "+sempathore.availablePermits());
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
