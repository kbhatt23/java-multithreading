package com.learning.benchmarking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 3)
@BenchmarkMode(org.openjdk.jmh.annotations.Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class LinkedlistVsArrayList {

	private List<Integer> arrayList;
	
	private List<Integer> linkedList;
	
	@Param({"10000"})
	private int size/* = 10000 */;
	
//	public static void main(String[] args) {
//		LinkedlistVsArrayList obj = new LinkedlistVsArrayList();
//		obj.initialze();
//		long pointerChasingArrayList = obj.pointerChasingArrayList();
//		long pointerChasingLinkedList = obj.pointerChasingLinkedList();
//		System.out.println("are suym equals "+(pointerChasingLinkedList==pointerChasingArrayList));
//	}
	
	@Setup
	public void initialze() {
		arrayList = new ArrayList<>(size);
		linkedList = new LinkedList<>();
		for(int i =0 ; i < size ; i++) {
			arrayList.add(i);
			linkedList.add(i);
		}
	}
	
	@Benchmark
	public long pointerChasingArrayList() {
		return arrayList.stream()
						.mapToInt(i -> i)
						.sum();
	}
	
	@Benchmark
	public long pointerChasingLinkedList() {
		return linkedList.stream()
						.mapToInt(i -> i)
						.sum();
	}
}
