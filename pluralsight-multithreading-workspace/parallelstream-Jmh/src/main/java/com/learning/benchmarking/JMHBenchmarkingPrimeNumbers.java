package com.learning.benchmarking;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 3)
@BenchmarkMode(org.openjdk.jmh.annotations.Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class JMHBenchmarkingPrimeNumbers {

	private static final Random RANDOM = new Random();

	@Param({"1000"})
	private int count;

	@Param({"128"})
	private int bits;

//	public static void main(String[] args) {
//		JMHBenchmarkingPrimeNumbers obj = new JMHBenchmarkingPrimeNumbers();
//		obj.count = 1000;
//		obj.bits = 128;
//		// after benchmarking we wont need these methods as they are error prone in
//		// bench marking
//		obj.usingloop();
//		obj.usingparallestream();
//		obj.usingloopWithoutResize();
//	}
	
	public static void main(String[] args) throws RunnerException{
		Options build = new OptionsBuilder()
					.include(JMHBenchmarkingPrimeNumbers.class.getName())
					.build();
					;
					new Runner(build).run();
	}

	public void usingparallestream() {
		long start = System.nanoTime();
		List<BigInteger> generatePrimeUsingLoop = generatePrimeUsingParallelStream();
		long end = System.nanoTime();

		System.out.println(String.format("stream took total time %d to generate %d primes", (end - start),
				generatePrimeUsingLoop.size()));
	}

	public void usingloop() {
		long start = System.nanoTime();
		List<BigInteger> generatePrimeUsingLoop = generatePrimeUsingLoop();
		long end = System.nanoTime();

		System.out.println(String.format("loop took total time %d to generate %d primes", (end - start),
				generatePrimeUsingLoop.size()));
	}

	public void usingloopWithoutResize() {
		long start = System.nanoTime();
		List<BigInteger> generatePrimeUsingLoop = generatePrimeUsingLoopWithNoResize();
		long end = System.nanoTime();

		System.out.println(String.format("loop took total time %d to generate %d primes", (end - start),
				generatePrimeUsingLoop.size()));
	}

	@Benchmark
	public List<BigInteger> generatePrimeUsingLoop() {
		List<BigInteger> primes = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			primes.add(probablePrime(bits));
		}
		return primes;
	}

	@Benchmark
	public List<BigInteger> generatePrimeUsingLoopWithNoResize() {
		List<BigInteger> primes = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			primes.add(probablePrime(bits));
		}
		return primes;
	}

	public BigInteger probablePrime(int bits) {
		return BigInteger.probablePrime(bits, RANDOM);
	}

	@Benchmark
	public List<BigInteger> generatePrimeUsingParallelStream() {
		return IntStream.rangeClosed(1, count).mapToObj(index -> probablePrime(bits)).parallel()
				.collect(Collectors.toList());
	}
	
	@Benchmark
	public List<BigInteger> generatePrimeUsingSequentialStream() {
		return IntStream.rangeClosed(1, count).mapToObj(index -> probablePrime(bits)).parallel()
				.collect(Collectors.toList());
	}

}
