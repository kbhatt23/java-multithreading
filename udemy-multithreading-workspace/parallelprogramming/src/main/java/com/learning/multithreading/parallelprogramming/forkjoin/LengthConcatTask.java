package com.learning.multithreading.parallelprogramming.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import com.learning.multithreading.parallelprogramming.util.CommonUtil;

public class LengthConcatTask extends RecursiveTask<List<String>> {
	private List<String> names;
	private int startIndex;
	private int endIndex;
	private int batchSize;

	public LengthConcatTask(List<String> names, int startIndex, int endIndex, int batchSize) {
		super();
		this.names = names;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.batchSize = batchSize;
	}

	@Override
	protected List<String> compute() {
		if ((endIndex - startIndex) >= (batchSize)) {
			//System.out.println("breaking the task");
			int mid = (startIndex + endIndex) / 2;
			LengthConcatTask firstHalf = new LengthConcatTask(names, startIndex, mid, batchSize);
			LengthConcatTask secondHalf = new LengthConcatTask(names, mid + 1, endIndex, batchSize);
			invokeAll(firstHalf, secondHalf);

			List<String> firstList = firstHalf.join();
			List<String> secondList = secondHalf.join();

			firstList.addAll(secondList);
			return firstList;
		} else {
			List<String> namesAppaneded = new ArrayList<String>();
			for (int i = startIndex; i <= endIndex; i++) {
				String name = names.get(i);
				namesAppaneded.add(CommonUtil.concatLength(name));
			}
			return namesAppaneded;
		}
	}

}
