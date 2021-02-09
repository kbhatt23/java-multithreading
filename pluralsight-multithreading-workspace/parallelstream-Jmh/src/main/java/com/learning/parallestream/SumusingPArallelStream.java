package com.learning.parallestream;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
public class SumusingPArallelStream {

	@Param({"100000000"})
	private int size;
	
	private List<Integer> numbers;
	
	@Setup
	public void initialze() {
		numbers=  IntStream.rangeClosed(1, size)
				.boxed().collect(Collectors.toList());
	}

	@Benchmark
	public long sumUsingLoop() {
		long sum=0;
		for(int number: numbers) {
			sum+=number;
		}
		return sum;
	}
	
	@Benchmark
	public long sumUsingStream() {
		return numbers.stream().mapToInt(i -> i)
					.sum();
	}
	
	@Benchmark
	public long sumUsingParallelStream() {
		return numbers.stream()
				.parallel()
				.mapToInt(i -> i)
					.sum();
	}
}
