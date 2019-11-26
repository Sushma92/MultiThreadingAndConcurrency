package com.sushma.concurrency_main;

import java.util.concurrent.TimeUnit;

public class AppleTree {
	
	public static AppleTree[] newAppleTreeGarden(int size) {
		AppleTree[] appleTrees = new AppleTree[size];
		for(int aNum = 1; aNum <= appleTrees.length; aNum++) {
			appleTrees[aNum] = new AppleTree("#" + aNum);
		}
		return appleTrees;
	}
	
	
	private final String appleTreeName;
	private final int noOfApples;
	
	public AppleTree(String appleTreeName) {
		this.appleTreeName = appleTreeName;
		noOfApples = 3;
	}

	public int pickApples(String workerName) throws InterruptedException {
		System.out.println("started picking the apples");
		TimeUnit.SECONDS.sleep(1000);
		return noOfApples;		
	}
	
	
}
