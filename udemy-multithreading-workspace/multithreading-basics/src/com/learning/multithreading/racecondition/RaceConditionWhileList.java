package com.learning.multithreading.racecondition;

import java.util.ArrayList;
import java.util.List;

import com.learning.multithreading.util.ThreadUtil;

public class RaceConditionWhileList {
public static void main(String[] args) {
	List<Integer> integers  =new ArrayList<Integer>();
	AdditionWorker r = new AdditionWorker(integers);
	
	//sharing same integer list to be populated by differnet threads for increaing perforance
	
	Thread t1 = new Thread(r);
	Thread t2  = new Thread(r);
	t1.start();
	t2.start();
	
	ThreadUtil.join(t1);
	ThreadUtil.join(t2);
	System.out.println("final list size: "+integers.size());
}

private static class AdditionWorker implements Runnable{
	List<Integer> integers ;
	public AdditionWorker(List<Integer> integers) {
		this.integers=integers;
	}
	
	public synchronized void run() {
		for(int i=0; i < 10000; i++) {
			integers.add(i);
		}
	}
}
}
