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
		
		Callable<Pizza> task1 = () -> {
		
		System.out.println("Slicing");
		System.out.println("spread sauce on crust");
		System.out.println("spread the sliced veggies");
		System.out.println("spread cheese");
		System.out.println("Bake");
		TimeUnit.SECONDS.sleep(2);
		Pizza pizza = new Pizza();
		return pizza;
		};
		
		Future<Pizza> pickPizza = pool.submit(task1);
		
		System.out.println("ME: call my brother");
		TimeUnit.SECONDS.sleep(3);
		System.out.println("ME: walk the dog");
		
		Pizza  pizza = pickPizza.get();
		System.out.println("ME: Eat the pizza");
		pool.shutdown();
		
	}
	
	
	
}