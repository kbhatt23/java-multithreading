package com.learning.multithreading.parallelprogramming.forkjoin;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class LengthConcatTaskUsageTest {
	LengthConcatTaskUsage obj = new LengthConcatTaskUsage();
	@Test
	void test() {
		List<String> names = Arrays.asList("sita-ram", "radhe-krishna", "uma-shankar", " brahma deva",
				"ramduta-hanuman", "ganesh", "karthik", "yama deva", "surya deva");
		
		List<String> result = obj.processUginForkJoin(names);
		assertTrue(names.size()==result.size(), "Size of result list should be same, expected "+names.size()+ " , got "+result.size());
		for(int i=0 ; i<names.size();i++) {
			String resultItem = result.get(i);
			String originalIten =  names.get(i);
			String[] parts = resultItem.split(":");
			assertTrue(parts[0].equals(originalIten), "Messgae not populated correctly , expected "+originalIten+" ,got "+parts[0]);
			assertTrue(Integer.valueOf(parts[1]) == originalIten.length(), "Item not correct expected length "+originalIten.length()+" , got "+parts[1]);
		}
	}

}
