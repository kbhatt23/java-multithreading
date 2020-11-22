package com.learning.multithreading.concurrentcollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

import com.learning.multithreading.util.ThreadUtil;

public class ConcurrentModificationExceptionExample3 {
	public static void main(String[] args) {

		Map<Integer, String> numbers= new HashMap<Integer, String>();
		
		Thread write = new Thread( () ->  {
			int i=0;
			while(true) {
				numbers.put(i, "fake");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
		});
		write.start();
		Thread read = new Thread( () ->  {
			Iterator<Entry<Integer, String>> iterator = numbers.entrySet().iterator();
			while(true) {
			while(iterator.hasNext()) {
				System.out.println("item found "+iterator.next());
				ThreadUtil.sleep(100);
			}}
		});
		read.start();
	}
}
