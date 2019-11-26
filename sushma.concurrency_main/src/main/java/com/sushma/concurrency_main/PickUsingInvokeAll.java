package com.sushma.concurrency_main;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;

public class PickUsingInvokeAll {

	public static void main(String[] args) {

		AppleTree[] appletrees = AppleTree.newAppleTreeGarden(6);
		
		Callable<Void> applePicker1 = createApplePicker(appletrees, 0, 2, "Bob");
		Callable<Void> applePicker2 = createApplePicker(appletrees, 2, 4, "Alex");
		Callable<Void> applePicker3 = createApplePicker(appletrees, 4, 6, "Carol");
		
		ForkJoinPool.commonPool().invokeAll(Arrays.asList(applePicker1, applePicker2, applePicker3));
		
		System.out.println("All fruits collected");
		
	}
	
	public static Callable<Void> createApplePicker(AppleTree[] appleTrees, int from, int to, String workerName){
		
		return () -> {
			for( int aNum = from; aNum < to; aNum++) {
				appleTrees[aNum].pickApples(workerName);
			}
			return null;
		};
	}

}
