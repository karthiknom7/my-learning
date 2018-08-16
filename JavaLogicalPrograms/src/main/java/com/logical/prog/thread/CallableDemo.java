package com.logical.prog.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {

	public static void main(String[] args) {

		Callable<Integer> myCallable = () -> {

			return null;

		};

		ExecutorService executorService = Executors.newFixedThreadPool(1);

		Future<Integer> future = executorService.submit(() -> {
			Random random = new Random();
			System.out.println("Thread started....");

			int duration = random.nextInt(4000);
			Thread.sleep(random.nextInt(4000));
			System.out.println("Finished.....");
			return duration;
		});

		try {
			System.out.println("Result is : " + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();	
	}

}
