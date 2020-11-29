package com.learning.multithreading.parallelprogramming;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FindAverage {
public static void main(String[] args) {
	Path path = Paths.get("C:\\Kanishk\\files-generation\\calculateAverage.txt");
	try {
	double averageArrayList = Files.readAllLines(path)
			.stream()
			.limit(14)
			.map(line -> line.split(" ")[3])
			.mapToInt(Integer::valueOf)
			.average()
			.orElseThrow(RuntimeException::new);
	
	double averageLinkedList = Files.readAllLines(path)
			.stream()
			.skip(15)
			.limit(14)
			.map(line -> line.split(" ")[3])
			.mapToInt(Integer::valueOf)
			.average()
			.orElseThrow(RuntimeException::new);
	System.out.println(String.format("arraylist average %s and linkedlist average %s ", averageArrayList,averageLinkedList));
	
	}catch (Exception e) {
		// TODO: handle exception
	}
}
}
