package com.learning.benchmarking;

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
public class IntegerArrayVsPRimitiveArray {

	public static void main(String[] args) {
		IntegerArrayVsPRimitiveArray obj = new IntegerArrayVsPRimitiveArray();
		obj.initialize();
		
		obj.resultUSingIntArray();
		obj.resultUSingIntegerArray();
	}
	
	@Param({"10000"})
	private int size/* =10000 */;
	
	private int[] intArray;
	
	private Integer[] integerArray;
	
	@Setup
	public void initialize() {
		intArray  =new int[size];
		integerArray  =new Integer[size];
		for(int i = 0 ; i < size ; i ++) {
			intArray[i]=i;
			integerArray[i]=i;
		}
	}
	
	@Benchmark
	public long resultUSingIntArray() {
		long sum = 0;
		for(int i = 0 ; i < size ; i ++) {
			sum+=intArray[i];
		}
		//System.out.println("resultUSingIntArray: final sum="+sum);
		return sum;
	}
	
	@Benchmark
	public long resultUSingIntegerArray() {
		long sum = 0;
		for(int i = 0 ; i < size ; i ++) {
			sum+=integerArray[i];
		}
		//System.out.println("resultUSingIntegerArray: final sum="+sum);
		return sum;
	}
}
