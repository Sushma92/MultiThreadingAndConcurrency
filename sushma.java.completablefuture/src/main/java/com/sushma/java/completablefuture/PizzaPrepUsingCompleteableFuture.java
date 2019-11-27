package com.sushma.java.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PizzaPrepUsingCompleteableFuture {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();

		CompletableFuture<String> tomatoSlices = CompletableFuture.supplyAsync(

				() -> {
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Slicing Tomatoes");
					return "Tomato Slices";
				}, executorService);
		
		CompletableFuture<String> prepIngredients = CompletableFuture.supplyAsync(

				() -> {
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Slicing Onions");
					String toppings = "Onions Tomatoes";
					return toppings;
				}, executorService);

		
		CompletableFuture<String> prepPizza = prepIngredients.thenApply(

				(toppings) -> {
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Prepping Pizza with " + toppings);
					return "Raw pizza with " + toppings;
				});

		CompletableFuture<String> bakePizza = prepPizza.thenApply(

				(rawPizza) -> {
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Baking Pizza " + rawPizza);
					return "Pizza";
				});
		
		bakePizza.thenAccept(pizza -> System.out.println("Eating " + pizza));
		
	executorService.shutdown();

	}

}
