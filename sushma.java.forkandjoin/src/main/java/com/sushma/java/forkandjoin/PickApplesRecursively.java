package com.sushma.java.forkandjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class PickApplesRecursively {

	public static void main(String[] args) {

		AppleTree[] appleTrees = AppleTree.newAppleTreeGarden(12);
		ForkJoinPool pool = ForkJoinPool.commonPool();
		
		PickFruitTask task = new PickFruitTask(appleTrees, 0, appleTrees.length - 1);
		int result = pool.invoke(task);
		
		System.out.println("Total apples picked: " + result);
		
	}
	
	public static class PickFruitTask extends RecursiveTask<Integer>{
		
		private final AppleTree[] appleTrees;
		private final int startInclusive;
		private final int endInclusive;
		
		private final int taskThreshold = 4;
		
		public PickFruitTask(AppleTree[] array, int startInclusive, int endInclusive) {
			this.startInclusive = startInclusive;
			this.endInclusive = endInclusive;
			this.appleTrees = array;
		}

		@Override
		protected Integer compute() {
			if(endInclusive - startInclusive < taskThreshold) {
				return doCompute();
			}
			
			int midpoint = startInclusive + (endInclusive - startInclusive)/2;
			
			PickFruitTask leftSum = new PickFruitTask(appleTrees, startInclusive, midpoint);
			PickFruitTask rightSum = new PickFruitTask(appleTrees, midpoint + 1, endInclusive);
			
			rightSum.fork();
			return leftSum.compute() + rightSum.join();
		}
		
		protected Integer doCompute() {
			return IntStream.rangeClosed(startInclusive, endInclusive)
					.map(aNum -> {
						try {
							return appleTrees[aNum].pickApples();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return aNum;
					}).sum();
					
		}
		
	}

}
