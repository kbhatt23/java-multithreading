package com.learning.mutithreading.chapter1;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConcurrentMapRaceCondition {
public static void main(String[] args) {
	//showing demo 
	ConcurrentMap<Integer, String> map = IntStream.rangeClosed(1, 100000)
			.boxed()
			.collect(Collectors.toConcurrentMap(Function.identity(),  i -> "ram "+i));
	
	Runnable r = () ->{
		for(int i=1; i <= 100000;i++) {
			//fix to solve race condition as more than one thread may enter the if condition but only one of them will remove it
			//and hence for other thread remove will actually return null as there is no old value
			//synchronized (map) {
			if(map.containsKey(i)) {
				String value = map.remove(i);
				//item should never be null in single threaded
				//but one of the thread wud have already removed, in case 2 or more thread had entered the if condition
				if(value == null) {
					System.out.println("Same object was removed by another thread "+i);
				}
			}
			//}
		}
	};
	
	new Thread(r).start();
	new Thread(r).start();
	new Thread(r).start();
	new Thread(r).start();
	
}
}
