package com.learning.multithreading.concurrentcollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentModificationExceptionExample1 {
public static void main(String[] args) {
	//fixed size array and hence we can can not add/revmoe from viewed collection
	//List<String> names = Arrays.asList("one","two","three","four");
	List<String> names = new ArrayList<String>();
	
	//fix is below to use copy on write arraylist
	//List<String> names = new CopyOnWriteArrayList<String>();
	names.add("one");names.add("two");names.add("three");
	names.add("1");names.add("2");names.add("3");
	Iterator<String> iterator = names.iterator();
	
	//if concurrently we are doing write and read, iterator is open and we are adding itme to collection
	//collections is ail fast to throw concurrnetmodificationexception
	while(iterator.hasNext()) {
		String name = iterator.next();
		if("two".equals(name)) {
			//remove or add both gives exception
			//names.remove(1);
			names.add("fake");
		}
	}
	System.out.println(names);
}
}
