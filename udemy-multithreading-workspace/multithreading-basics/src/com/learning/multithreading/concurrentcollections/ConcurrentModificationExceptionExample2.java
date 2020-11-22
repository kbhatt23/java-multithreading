package com.learning.multithreading.concurrentcollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.learning.multithreading.util.ThreadUtil;

public class ConcurrentModificationExceptionExample2 {
	public static void main(String[] args) {

		List<String> names = new ArrayList<String>();
		
		Thread write = new Thread( () ->  {
			while(true) {
				names.add("fake");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		write.start();
		Thread read = new Thread( () ->  {
			Iterator<String> iterator = names.iterator();
			while(true) {
			while(iterator.hasNext()) {
				System.out.println("item found "+iterator.next());
				ThreadUtil.sleep(100);
			}}
		});
		read.start();
	}
}
