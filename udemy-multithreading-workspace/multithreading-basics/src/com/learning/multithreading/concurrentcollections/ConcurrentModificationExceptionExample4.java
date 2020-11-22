package com.learning.multithreading.concurrentcollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.learning.multithreading.util.ThreadUtil;

public class ConcurrentModificationExceptionExample4 {
	public static void main(String[] args) {

		Map<Integer, String> numbers= new HashMap<Integer, String>();
		//Map<Integer, String> numbers= new ConcurrentHashMap<Integer, String>();
	
		numbers.put(1, "fake");
		numbers.put(2, "fake");
		numbers.put(3, "fake");
		numbers.put(4, "fake");
		Iterator<Entry<Integer, String>> iterator = numbers.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> next = iterator.next();
			System.out.println("entry found "+next);
			if(2 == next.getKey())
				//numbers.remove(2);
				numbers.put(5, "fake");
		}
	}
}
