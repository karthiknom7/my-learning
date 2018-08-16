package com.logical.prog.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(3);
		CountDownLatch latch = new CountDownLatch(3);
		
		for(int i=1; i<=3; i++){
			executorService.submit(new Worker(i*1000, "T"+i, latch));
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Main thread started it's work...");
		executorService.shutdown();
		
		try {
			if(!executorService.awaitTermination(3, TimeUnit.MINUTES)){
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static class Worker implements Runnable{

		private long delay;
		private String name;
		private CountDownLatch latch;
		
		
		public Worker(long delay, String name, CountDownLatch latch) {
			this.delay = delay;
			this.name = name;
			this.latch = latch;
		}


		@Override
		public void run() {

			System.out.println(name + " Started...");
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name + " Complted");
			latch.countDown();
		}
		
	}
}
