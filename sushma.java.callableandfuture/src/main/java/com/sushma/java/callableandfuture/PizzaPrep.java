package com.sushma.java.callableandfuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class PizzaPrep {

	public static class Pizza{

		public String toString() {
			return "cheese pizza";
		}
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		pizzaPrepUsingCallableAndFuture();
		
	}
	
	public static void pizzaPrepUsingCallableAndFuture() throws InterruptedException, ExecutionException {
		System.out.println("Using Callable and Future");
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		Runnable task1 = () -> {
		
		System.out.println("Slicing");
		System.out.println("spread sauce on crust");
		System.out.println("spread the sliced veggies");
		System.out.println("spread cheese");
		System.out.println("Bake");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Pizza pizza = new Pizza();
		//return pizza;
		};
		
		Future<?> pickPizza = pool.submit(task1);
		
		System.out.println("ME: call my brother");
		TimeUnit.SECONDS.sleep(3);
		System.out.println("ME: walk the dog");
		
		pickPizza.cancel(true);
		if(pickPizza.isCancelled()) {
			System.out.println("ME: pizza is cancelled, order something else");
			System.out.println("pickPizza.isDone(): " + pickPizza.isDone());
		}else if(!pickPizza.isDone()) {
			System.out.println("Me: watch tv");
		}
		
		Object pizza = pickPizza.get();
		System.out.println("ME: Eat the pizza");
		pool.shutdown();
		
	}
	
	
	
}