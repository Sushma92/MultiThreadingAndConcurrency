package com.sushma.java.forkandjoin;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AppleTree {

	public static AppleTree[] newAppleTreeGarden(int size) {
		AppleTree[] appleTrees = new AppleTree[size];
		for (int aNum = 0; aNum < appleTrees.length; aNum++) {
			appleTrees[aNum] = new AppleTree("#" + aNum);
		}
		return appleTrees;
	}

	public final String appleTreeName;
	public final int noOfApples;

	public AppleTree(String appleTreeName) {
		this.appleTreeName = appleTreeName;
		noOfApples = 3;
	}

	public int pickApples(String workerName) throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		System.out.printf("%s picked %d 🍏s from %s \n", workerName, noOfApples, appleTreeName);
		return noOfApples;
	}

	public int pickApples() throws InterruptedException {
		return pickApples(toLabel(Thread.currentThread().getName()));
	}

	private String toLabel(String threadName) {
		HashMap<String, String> threadNameToLabel = new HashMap<>();
		threadNameToLabel.put("ForkJoinPool.commonPool-worker-1", "Alice");
		threadNameToLabel.put("ForkJoinPool.commonPool-worker-2", "Bob");
		threadNameToLabel.put("ForkJoinPool.commonPool-worker-3", "Carol");
		threadNameToLabel.put("ForkJoinPool.commonPool-worker-4", "Dan");

		return threadNameToLabel.getOrDefault(threadName, threadName);
	}
}
