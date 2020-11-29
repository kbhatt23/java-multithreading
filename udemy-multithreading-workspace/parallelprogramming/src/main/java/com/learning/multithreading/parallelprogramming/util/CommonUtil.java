package com.learning.multithreading.parallelprogramming.util;

import org.apache.commons.lang3.time.StopWatch;

public class CommonUtil {

	public static final int PROCESSOR_CORES = Runtime.getRuntime().availableProcessors();
	public static final StopWatch STOP_WATCH = new StopWatch();

	public static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String transform(String str) {
		sleep(500);
		return str.toUpperCase();
	}

	public static void startTimer() {
		STOP_WATCH.start();
	}

	public static void stopTimer() {
		STOP_WATCH.stop();
	}

	public static void resetTimer() {
		STOP_WATCH.reset();
	}

	public static void timeTaken() {
		System.out.println("Total time taken " + STOP_WATCH.getTime());
	}
	public static String concatLength(String str) {
		sleep(500);
		return str+":"+str.length();
	}
}
