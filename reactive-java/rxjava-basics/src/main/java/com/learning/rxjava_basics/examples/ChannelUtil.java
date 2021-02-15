package com.learning.rxjava_basics.examples;

import java.math.BigInteger;
import java.util.Random;

import com.learning.rxjava_basics.utils.ThreadUtil;

import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

public class ChannelUtil {
	public static final Consumer<String> dataChannel = data -> System.out.println("recieved data " + data);
	public static final Consumer<Throwable> errorChannel = error -> System.out.println("error occurred " + error);
	public static final Action completeChannel = () -> System.out.println("all task completed sucesfully");
	
	public static final Random radom = new Random();

	//this will be time consuming task
	public static BigInteger generateComplexBiInt() {
		return BigInteger.probablePrime(300, radom);
	}
	
	//time taking task
	public static int computationTask() {
		//processor never gets time to sleep/relax
		BigInteger big = null;
		for(int i = 0 ; i < 400 ; i++) {
			big = generateComplexBiInt();
		}
		return big.intValue();
	}
	
	public static int ioIntensiveTask() {
		//thread sleeps for lot of time hence more threads than cpu cores can be created to ensure processor are busy
		//web service/db call hence thread is in sleep for lot of time 
		//hence we create more threads than core
		ThreadUtil.sleep(1000);
		return generateComplexBiInt().intValue();
	}
	
//	public static void main(String[] args) {
//		long start = System.currentTimeMillis();
//		
//		computationTask();
//		long end = System.currentTimeMillis();
//		System.out.println("total time taken "+(end-start));
//	}
}
