package com.learning.multithreading.parallelprogramming.forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;

import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class LengthConcatTaskUsage {
	private static ForkJoinPool pool=null;
	public static void main(String[] args) {
		List<String> names = Arrays.asList("sita-ram", "radhe-krishna", "uma-shankar", " brahma deva",
				"ramduta-hanuman", "ganesh", "karthik", "yama deva", "surya deva");

		processUginForkJoin(names);
		processUginSingleThread(names);
		namesAppaneded(names);
	}

	private static void processUginSingleThread(List<String> names) {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();

		List<String> namesAppaneded = new ArrayList<String>();
		for (int i = 0; i < names.size(); i++) {
			String name = names.get(i);
			namesAppaneded.add(CommonUtil.concatLength(name));
		}
		System.out.println(namesAppaneded);
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();

	}

	public static List<String> processUginForkJoin(List<String> names) {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		pool = new ForkJoinPool(availableProcessors + 1);

		int batchSize = names.size() / availableProcessors;
		// int batchSize = 10;
		ForkJoinTask<List<String>> submit = pool.submit(new LengthConcatTask(names, 0, names.size() - 1, batchSize));
		List<String> namesAppended =null;
		try {
			namesAppended = submit.get();
			System.out.println(namesAppended);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();
		return namesAppended;
	}

	private static void namesAppaneded(List<String> names) {
		CommonUtil.resetTimer();
		CommonUtil.startTimer();

//		List<String> namesAppaneded = names.parallelStream()
//			 .map(CommonUtil::concatLength)
//			 .collect(Collectors.toList());
		
		ForkJoinTask<List<String>> submit = pool.submit(() -> names.parallelStream()
				 .map(CommonUtil::concatLength)
				 .collect(Collectors.toList()));
		List<String> namesAppaneded = submit.join();
		System.out.println(namesAppaneded);
		CommonUtil.stopTimer();
		CommonUtil.timeTaken();

	}
}
